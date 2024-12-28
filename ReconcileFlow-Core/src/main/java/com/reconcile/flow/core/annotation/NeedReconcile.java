package com.reconcile.flow.core.annotation;

import com.xxl.rpc.core.remoting.provider.annotation.XxlRpcService;

/**
 * 表示需要补偿的方法，当出现异常导致分布式问题时，需要补偿执行
 */
@XxlRpcService
public @interface NeedReconcile {
}
