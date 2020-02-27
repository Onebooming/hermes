package com.onebooming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author Onebooming
 * @version 1.0
 * @date 2020-02-20 15:37
 * @ApiNote
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = {"com.onebooming.dao"})
public class ContentApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContentApplication.class);
    }
}
