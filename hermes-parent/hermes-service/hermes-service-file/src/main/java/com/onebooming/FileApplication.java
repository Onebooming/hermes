package com.onebooming;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
/**
 * @author Onebooming
 * @version 1.0
 * @date 2020-02-09 10:26
 * @ApiNote
 */
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})//排除数据库自动加载
@EnableEurekaClient
public class FileApplication {
    public static void main(String[] args) {
        SpringApplication.run(FileApplication.class, args);
    }
}
