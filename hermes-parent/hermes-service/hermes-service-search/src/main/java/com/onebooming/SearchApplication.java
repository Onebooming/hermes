package com.onebooming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author Onebooming
 * @version 1.0
 * @date 2020-02-26 22:26
 * @ApiNote
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})//排斥禁用数据库加载
@EnableEurekaClient
public class SearchApplication {

    public static void main(String[] args) {
        /**
         * Springboot整合Elasticsearch 在项目启动前设置一下的属性，防止报错
         * 解决netty冲突后初始化client时还会抛出异常
         * availableProcessors is already set to [12], rejecting [12]
         ***/
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        SpringApplication.run(SearchApplication.class,args);
    }
}

/**
 * 实现过程
 * 1-创建一个JavaBean，在JavaBean(SkuInfo)中添加索引库映射配置
 * 2-创建Feign，实现查询所有Sku集合
 * 3-在搜索微服务中调用Feign，查询所有Sku集合，并将Sku集合转化成SKUInfo集合
 * 4-Controller->Service->调用Dao（继承ElasticSearchRepository接口）实现数据导入到ES中
 */
