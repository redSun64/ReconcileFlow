package com.reconcile.flow.core.interceptor;

import com.reconcile.flow.core.util.TxIdThreadLocal;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import static com.reconcile.flow.core.constants.ReconcileFlowConstants.RECONCILE_FLOW_HEADER_KEY;

/**
 * @className: ReconcileTxIdFilter
 * @Description: TODO
 * @version:
 * @author: red_sun
 * @date: 2024/12/24 23:26
 */
@Component
@ConditionalOnClass
public class ReconcileTxIdServerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        TxIdThreadLocal.TX_ID.set(request.getHeader(RECONCILE_FLOW_HEADER_KEY));
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}