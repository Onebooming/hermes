package com.onebooming.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author Onebooming
 * @version 1.0
 * @date 2020-03-13 19:26
 * @ApiNote
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.onebooming.user.dao")
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class,args);
    }
}