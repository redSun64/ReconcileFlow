package com.reconcile.flow.core.aspect;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.reconcile.flow.core.annotation.NeedReconcile;
import com.reconcile.flow.core.constants.ReconcileFlowConstants;
import com.reconcile.flow.core.domain.dto.ReconcileFlowTxDTO;
import com.reconcile.flow.core.domain.service.SchedulerDomainService;
import com.reconcile.flow.core.util.TxIdGenerator;
import com.reconcile.flow.core.util.TxIdThreadLocal;
import com.xxl.rpc.core.remoting.invoker.annotation.XxlRpcReference;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * @className: RegisterReconcileFlowRPC
 * @Description: register need reconcile flow rpc
 * @version:
 * @author: red_sun
 * @date: 2024/12/23 23:07
 */
@Aspect
@Component
public class ReconcileFlowAspect {

    @Value("${service.name:}")
    private String serviceName;

    @XxlRpcReference
    private SchedulerDomainService schedulerDomainService;

    @Pointcut("@annotation(com.reconcile.flow.core.annotation.NeedReconcile)")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object before(ProceedingJoinPoint joinPoint) throws Throwable {
        // generate or get TxID
        String txId = TxIdThreadLocal.TX_ID.get();
        if (txId == null) {
            txId = TxIdGenerator.generateTxId();
            TxIdThreadLocal.TX_ID.set(txId);
        }
        String iface = joinPoint.getTarget().getClass().toString();
        String param = argsTransParamJson(joinPoint.getArgs());
        String methodName = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(NeedReconcile.class).value();
        ReconcileFlowTxDTO reconcileFlowTxDTO = ReconcileFlowTxDTO.builder()
                .txId(txId)
                .className(iface)
                .param(param)
                .methodName(methodName)
                .serviceName(serviceName)
                .status(ReconcileFlowConstants.WAIT)
                .expectedFinishDuration(10000L) //todo 时间参数配置
                .build();
        schedulerDomainService.addOrUpdateTransaction(reconcileFlowTxDTO);
        Object result = joinPoint.proceed();
        if (TransactionSynchronizationManager.isSynchronizationActive()) {
            TransactionSynchronizationManager.registerSynchronization(
                    new TransactionSynchronization() {
                        @Override
                        public void afterCommit() {
                            reconcileFlowTxDTO.setStatus(ReconcileFlowConstants.SUCCESS);
                            schedulerDomainService.addOrUpdateTransaction(reconcileFlowTxDTO);
                        }
                    });
        }
        return result;
    }


    private String argsTransParamJson(Object[] args) {
        JSONArray jsonArray = new JSONArray();
        for (Object arg : args) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.set(arg.getClass().toString(), arg);
            jsonArray.add(jsonObject);
        }
        return jsonArray.toString();
    }

}