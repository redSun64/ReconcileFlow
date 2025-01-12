package com.reconcile.flow.core.aspect;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.reconcile.flow.core.annotation.NeedReconcile;
import com.reconcile.flow.core.constants.ReconcileFlowConstants;
import com.reconcile.flow.core.domain.dto.ReconcileFlowTxDTO;
import com.reconcile.flow.core.domain.service.SchedulerDomainService;
import com.reconcile.flow.core.util.TxIdGenerator;
import com.reconcile.flow.core.util.TxIdThreadLocal;
import com.xxl.rpc.core.remoting.invoker.annotation.XxlRpcReference;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.time.Clock;
import java.time.Instant;
import java.util.Date;

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
        Long txId = TxIdThreadLocal.TX_ID.get();
        if (txId == null) {
            txId = TxIdGenerator.generateTxId();
            TxIdThreadLocal.TX_ID.set(txId);
        }
        String iface = joinPoint.getTarget().getClass().getName();
        String param = argsTransParamJson(joinPoint.getArgs());
        String methodName = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(NeedReconcile.class).value();
        ReconcileFlowTxDTO reconcileFlowTxDTO = ReconcileFlowTxDTO.builder()
                .txId(txId)
                .className(iface)
                .param(param)
                .methodName(methodName)
                .serviceName(serviceName)
                .status(ReconcileFlowConstants.WAIT)
                //todo localDateTime 框架不兼容问题
                .expectedFinishDuration(Date.from(Instant.now(Clock.systemDefaultZone()).plusSeconds(10))) //todo 时间参数配置，暂且写死 10s
                .build();
        Long id = schedulerDomainService.addOrUpdateTransaction(reconcileFlowTxDTO);
        Object result = joinPoint.proceed();
        if (TransactionSynchronizationManager.isSynchronizationActive()) {
            TransactionSynchronizationManager.registerSynchronization(
                    new TransactionSynchronization() {
                        @Override
                        public void afterCommit() {
                            reconcileFlowTxDTO.setId(id);
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
            jsonObject.put(arg.getClass().getName(), arg);
            jsonArray.add(jsonObject);
        }
        return jsonArray.toString();
    }

}