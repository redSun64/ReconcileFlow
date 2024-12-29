package com.reconcile.flow.core.annotation;

public @interface ReconcileFlowService {
    String methodName() default "";

    long expectedFinishDuration() default 60 * 1000L;
}
