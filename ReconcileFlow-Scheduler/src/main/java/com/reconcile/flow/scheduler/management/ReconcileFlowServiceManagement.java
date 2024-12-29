package com.reconcile.flow.scheduler.management;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @className: ReconcileFlowServiceManagement
 * @Description: manage reconcile flow service
 * @version:
 * @author: red_sun
 * @date: 2024/12/29 16:53
 */
public class ReconcileFlowServiceManagement {

    private Map<String, String> serviceMap;

    public ReconcileFlowServiceManagement() {
        serviceMap = new ConcurrentHashMap<>();
    }

    public void addService(String serviceName, String serviceUrl) {
        serviceMap.put(serviceName, serviceUrl);
    }
}