package com.onebooming.item;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Onebooming
 * @version 1.0
 * @date 2020-03-12 17:51
 * @ApiNote
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.onebooming.goods.feign")
public class ItemApplication {
    public static void main(String[] args) {
        SpringApplication.run(ItemApplication.class,args);
    }
}
