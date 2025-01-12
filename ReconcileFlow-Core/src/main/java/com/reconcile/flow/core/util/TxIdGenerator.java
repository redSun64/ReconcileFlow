package com.reconcile.flow.core.util;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;
import jakarta.annotation.PostConstruct;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

/**
 * @className: TxIdGenerator
 * @Description: TODO
 * @version:
 * @author: red_sun
 * @date: 2024/12/24 23:15
 */
@Slf4j
@UtilityClass
public class TxIdGenerator {
    private final static Snowflake snowflake = IdUtil.getSnowflake(1, 1);

    public Long generateTxId() {
        return snowflake.nextId();
    }
}