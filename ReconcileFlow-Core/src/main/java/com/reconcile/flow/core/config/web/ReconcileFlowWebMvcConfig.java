package com.reconcile.flow.core.config.web;

import com.reconcile.flow.core.interceptor.ReconcileTxIdServerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @className: ReconcileFlowWebMvcConfig
 * @Description: TODO
 * @version:
 * @author: red_sun
 * @date: 2025/1/4 23:08
 */
@Configuration
public class ReconcileFlowWebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ReconcileTxIdServerInterceptor());
    }
}