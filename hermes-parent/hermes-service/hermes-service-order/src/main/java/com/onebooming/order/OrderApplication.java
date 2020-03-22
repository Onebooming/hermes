package com.onebooming.order;


import entity.FeignInterceptor;
import entity.IdWorker;
import entity.TokenDecode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author Onebooming
 * @version 1.0
 * @date 2020-03-17 23:03
 * @ApiNote
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = {"com.onebooming.goods.feign","com.onebooming.user.feign"})
@MapperScan(basePackages = {"com.onebooming.order.dao"})
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class,args);
    }

    /***
     * 创建拦截器Bean对象
     * 将Feign调用拦截器注入到容器中
     * @return
     */
    @Bean
    public FeignInterceptor feignInterceptor(){
        return new FeignInterceptor();
    }

    /**
     * 解析令牌对象
     */
    @Bean
    public TokenDecode tokenDecode(){
        return new TokenDecode();
    }

    @Bean
    public IdWorker idWorker(){
        return new IdWorker(1,1);
    }
}
