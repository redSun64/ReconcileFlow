package com.reconcile.flow.core.config.rpc;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.Maps;
import com.xxl.rpc.core.remoting.invoker.impl.XxlRpcSpringInvokerFactory;
import com.xxl.rpc.core.remoting.net.impl.netty.server.NettyServer;
import com.xxl.rpc.core.serialize.impl.HessianSerializer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * xxl-rpc provider config
 *
 * @author xuxueli 2018-10-19
 */
@Configuration
@Slf4j
public class XxlRpcConfig {
    private Logger logger = LoggerFactory.getLogger(XxlRpcConfig.class);

    @Value("${xxl-rpc.remoting.port}")
    private int port;

    @Value("${reconcile.flow.service.map}")
    private String reconcileFlowServiceMap;

    @Bean
    public ReconcileFlowRpcProviderFactory reconcileFlowRpcProviderFactory() {

        ReconcileFlowRpcProviderFactory providerFactory = new ReconcileFlowRpcProviderFactory();
        providerFactory.setServer(NettyServer.class);
        providerFactory.setSerializer(HessianSerializer.class);
        providerFactory.setCorePoolSize(-1);
        providerFactory.setMaxPoolSize(-1);
        providerFactory.setPort(port);
        providerFactory.setAccessToken(null);
        providerFactory.setServiceRegistry(ReconcileFlowServiceRegister.class);
        providerFactory.setServiceRegistryParam(getRegistryParam());
        logger.info(">>>>>>>>>>> xxl-rpc provider config init finish.");
        return providerFactory;
    }

    @Bean
    public XxlRpcSpringInvokerFactory xxlRpcSpringInvokerFactory() {

        XxlRpcSpringInvokerFactory invokerFactory = new XxlRpcSpringInvokerFactory();
        invokerFactory.setServiceRegistryClass(ReconcileFlowServiceRegister.class);
        invokerFactory.setServiceRegistryParam(getRegistryParam());

        logger.info(">>>>>>>>>>> xxl-rpc invoker config init finish.");
        return invokerFactory;
    }

    private Map<String, String> getRegistryParam() {
        if (StringUtils.isBlank(reconcileFlowServiceMap)) {
            log.warn("xxl-rpc registry param is blank");
        }
        Map<String, String> paramMap = Maps.newHashMap();
        try {
            paramMap = JSON.parseObject(reconcileFlowServiceMap, new TypeReference<>() {
            });
        } catch (Exception e) {
            log.error("xxl-rpc registry param parse error", e);
        }
        return paramMap;
    }

}