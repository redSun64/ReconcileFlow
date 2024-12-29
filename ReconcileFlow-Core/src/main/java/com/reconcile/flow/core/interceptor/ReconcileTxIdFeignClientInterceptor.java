package com.reconcile.flow.core.interceptor;

import com.reconcile.flow.core.util.TxIdThreadLocal;
import feign.RequestInterceptor;
import feign.RequestTemplate;

import static com.reconcile.flow.core.constants.ReconcileFlowConstants.RECONCILE_FLOW_HEADER_KEY;

/**
 * @className: ReconcileTxIdFeignClientInterceptor
 * @Description: TODO
 * @version:
 * @author: red_sun
 * @date: 2024/12/28 17:55
 */
public class ReconcileTxIdFeignClientInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        if (TxIdThreadLocal.TX_ID.get() != null) {
            requestTemplate.header(RECONCILE_FLOW_HEADER_KEY, TxIdThreadLocal.TX_ID.get());
        }
    }
}