package com.reconcile.flow.core.config.rpc;

import com.reconcile.flow.core.annotation.ReconcileFlowService;
import com.xxl.rpc.core.remoting.provider.annotation.XxlRpcService;
import com.xxl.rpc.core.remoting.provider.impl.XxlRpcSpringProviderFactory;
import com.xxl.rpc.core.util.XxlRpcException;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

import java.util.Map;

/**
 * @className: ReconcileFlowRpcProviderFactory
 * @Description: TODO
 * @version:
 * @author: red_sun
 * @date: 2025/1/4 21:58
 */
public class ReconcileFlowRpcProviderFactory extends XxlRpcSpringProviderFactory {

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        Map<String, Object> serviceBeanMap = applicationContext.getBeansWithAnnotation(ReconcileFlowService.class);
        if (serviceBeanMap != null && serviceBeanMap.size() > 0) {
            for (Object serviceBean : serviceBeanMap.values()) {
                // valid
                if (serviceBean.getClass().getInterfaces().length == 0) {
                    throw new XxlRpcException("xxl-rpc, service(XxlRpcService) must inherit interface.");
                }
                // add service
                ReconcileFlowService reconcileFlowService = serviceBean.getClass().getAnnotation(ReconcileFlowService.class);
                XxlRpcService xxlRpcService = reconcileFlowService.annotationType().getAnnotation(XxlRpcService.class);
                Class<?> targetClass = AopProxyUtils.ultimateTargetClass(serviceBean);
                String iface = targetClass.getInterfaces().length == 0 ? targetClass.getName() : targetClass.getInterfaces()[0].getName();
                String version = xxlRpcService.version();

                super.addService(iface, version, serviceBean);
            }
        }

        // TODO，addServices by api + prop

    }
}