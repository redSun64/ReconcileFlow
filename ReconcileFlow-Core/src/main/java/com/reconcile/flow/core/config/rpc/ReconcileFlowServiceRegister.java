package com.reconcile.flow.core.config.rpc;

import com.xxl.rpc.core.registry.impl.LocalRegister;
import org.springframework.beans.factory.annotation.Value;

import java.util.Map;
import java.util.Set;

/**
 * @className: ReconcileFlowServiceRegister
 * @Description: TODO
 * @version:
 * @author: red_sun
 * @date: 2025/1/1 23:51
 */
public class ReconcileFlowServiceRegister extends LocalRegister {

    @Value("${reconcile.flow.service.name:}")
    private String serviceName;

    @Value("${reconcile.flow.service.address:}")
    private String serviceAddress;

    @Override
    public void start(Map<String, String> param) {
        super.start(param);
        registry(Set.of(serviceName), serviceAddress);
    }

}