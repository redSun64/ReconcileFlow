package com.reconcile.flow.scheduler.management;

import com.xxl.rpc.core.remoting.invoker.annotation.XxlRpcReference;
import com.xxl.rpc.core.remoting.invoker.generic.XxlRpcGenericService;

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

    @XxlRpcReference
    private XxlRpcGenericService xxlRpcGenericService;

    private Map<String, String> serviceMap;

    public ReconcileFlowServiceManagement() {
        serviceMap = new ConcurrentHashMap<>();
    }

    public void addService(String serviceName, String serviceUrl) {
        serviceMap.put(serviceName, serviceUrl);
    }

    public void executeMethod(String className, String serviceName, String methodName, String params) {

        // 执行对应的服务方法
    }
}