package com.onebooming.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Onebooming
 * @version 1.0
 * @date 2020-03-09 23:05
 * @ApiNote
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.onebooming.search.feign")
public class WebSearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebSearchApplication.class,args);
    }
}
