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
    private Snowflake snowflake;

    @PostConstruct
    void init() {
        long workerId = 0;
        try {
            workerId = NetUtil.ipv4ToLong(NetUtil.getLocalhostStr());
            log.info("当前机器 workerId: {}", workerId);
        } catch (Exception e) {
            log.warn("获取机器 ID 失败", e);
            workerId = NetUtil.getLocalhost().hashCode();
            log.info("当前机器 workerId: {}", workerId);
        }
        snowflake = IdUtil.getSnowflake(workerId, 1);
    }

    public long generateTxId() {
        return snowflake.nextId();
    }
}