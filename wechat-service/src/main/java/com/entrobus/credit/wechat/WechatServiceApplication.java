package com.entrobus.credit.wechat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
@EnableCircuitBreaker
@EnableTransactionManagement//开启事务
//@EnableOAuth2Client
@MapperScan(basePackages="com.entrobus.credit.wechat.dao")
public class WechatServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WechatServiceApplication.class, args);
    }
}
