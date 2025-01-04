package com.reconcile.flow.core.annotation;

import org.springframework.context.annotation.ComponentScan;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ComponentScan(basePackages = "com.reconcile.flow")
public @interface EnableReconcileFlow {
}
