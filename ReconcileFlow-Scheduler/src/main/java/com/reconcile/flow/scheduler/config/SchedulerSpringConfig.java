package com.reconcile.flow.scheduler.config;

import com.xxl.rpc.core.registry.impl.LocalRegister;
import com.xxl.rpc.core.remoting.net.impl.netty.server.NettyServer;
import com.xxl.rpc.core.remoting.provider.impl.XxlRpcSpringProviderFactory;
import com.xxl.rpc.core.serialize.impl.HessianSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class SchedulerSpringConfig {

    @Value("${xxl-rpc.remoting.port}")
    private int port;



    @Bean
    public XxlRpcSpringProviderFactory xxlRpcSpringProviderFactory() {
        XxlRpcSpringProviderFactory providerFactory = new XxlRpcSpringProviderFactory();
        providerFactory.setServer(NettyServer.class);
        providerFactory.setSerializer(HessianSerializer.class);
        providerFactory.setCorePoolSize(-1);
        providerFactory.setMaxPoolSize(-1);
        providerFactory.setPort(port);
        providerFactory.setAccessToken(null);
        providerFactory.setServiceRegistry(LocalRegister.class);

        log.info(">>>>>>>>>>> xxl-rpc provider config init finish.");
        return providerFactory;
    }
}
