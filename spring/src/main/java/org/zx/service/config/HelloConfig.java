package org.zx.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zx.service.HelloService;
import org.zx.service.impl.HelloServiceImpl;

/**
 * @author xiang.zhang
 */

public class HelloConfig {
    @Bean
    public HelloService helloService(){
        return new HelloServiceImpl();
    }
}
