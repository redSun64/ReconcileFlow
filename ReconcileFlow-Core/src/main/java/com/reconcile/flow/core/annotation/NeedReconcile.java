package com.reconcile.flow.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 表示需要补偿的方法，当出现异常导致分布式问题时，需要补偿执行
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NeedReconcile {
    String value() default "";
}
