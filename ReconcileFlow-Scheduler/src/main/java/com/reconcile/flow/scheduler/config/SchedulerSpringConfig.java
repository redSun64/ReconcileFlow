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

}
