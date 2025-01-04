package com.reconcile.flow.core.config.rpc;

import com.xxl.rpc.core.registry.impl.LocalRegister;
import org.springframework.stereotype.Component;

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


    @Override
    public void start(Map<String, String> param) {
        super.start(param);
        param.forEach((k, v) -> registry(Set.of(k), v));
    }
}