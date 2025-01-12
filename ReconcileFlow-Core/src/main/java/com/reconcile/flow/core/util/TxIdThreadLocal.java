package com.reconcile.flow.core.util;

import com.alibaba.ttl.TransmittableThreadLocal;
import lombok.experimental.UtilityClass;

/**
 * @className: threadlocal
 * @Description: TODO
 * @version:
 * @author: red_sun
 * @date: 2024/12/24 23:10
 */
@UtilityClass
public class TxIdThreadLocal {
    public TransmittableThreadLocal<Long> TX_ID = new TransmittableThreadLocal<>();
}