package com.onebooming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author Onebooming
 * @version 1.0
 * @date 2020-02-08 09:30
 * @ApiNote
 */
@SpringBootApplication
@EnableEurekaServer //开启Eureka服务
public class EurekaApplication {

    /**
     * 加载启动类，以启动类为当前SpringBoot的配置标准
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication.class,args);
    }
}
