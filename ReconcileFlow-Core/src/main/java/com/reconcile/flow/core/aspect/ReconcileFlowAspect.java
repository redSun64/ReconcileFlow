package com.reconcile.flow.core.aspect;

import com.reconcile.flow.core.domain.service.SchedulerDomainService;
import com.reconcile.flow.core.util.TxIdGenerator;
import com.reconcile.flow.core.util.TxIdThreadLocal;
import com.xxl.rpc.core.remoting.invoker.annotation.XxlRpcReference;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
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

    @XxlRpcReference
    private SchedulerDomainService schedulerDomainService;

    @Pointcut("@target(com.reconcile.flow.core.annotation.ReconcileFlowService)" +
            " && @annotation(org.springframework.transaction.annotation.Transactional)")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void before() {
        // generate or get TxID
        String txId = TxIdThreadLocal.TX_ID.get();
        if (txId == null) {
            TxIdThreadLocal.TX_ID.set(TxIdGenerator.generateTxId());
            //todo add new reconcile flow tx
        }

    }

    @After("pointCut()")
    public void after() {
        // register tx call back
        if (TransactionSynchronizationManager.isSynchronizationActive()) {

        }

    }
}