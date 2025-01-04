package com.reconcile.flow.core.annotation;

import com.xxl.rpc.core.remoting.provider.annotation.XxlRpcService;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@XxlRpcService
public @interface ReconcileFlowService {
    String methodName() default "";

    long expectedFinishDuration() default 60 * 1000L;
}
