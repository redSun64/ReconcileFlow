package com.reconcile.flow.core.aspect;

import com.reconcile.flow.core.util.TxIdGenerator;
import com.reconcile.flow.core.util.TxIdThreadLocal;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

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

    @Pointcut("@target(com.reconcile.flow.core.annotation.ReconcileFlowService)" +
            " && @annotation(org.springframework.transaction.annotation.Transactional)")
    public void pointCut() {
    }

    /**
     * generate or get TxID
     */
    @Before("pointCut()")
    public void before() {
        ArrayList<Object> objects = new ArrayList<>();
        objects.remove()
        Long txId = TxIdThreadLocal.TX_ID.get();
        if (txId == null) {
            TxIdThreadLocal.TX_ID.set(TxIdGenerator.generateTxId());
        }
    }
}