package org.zx;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.zx.service.HelloService;

import javax.annotation.Resource;

/**
 * @author xiang.zhang
 */
@ComponentScan
public class SpringApp {

    @Resource
    private HelloService helloService;

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringApp.class);
        SpringApp app = applicationContext.getBean(SpringApp.class);
        app.getHelloService().echo();
    }

    public HelloService getHelloService() {
        return helloService;
    }
}
