package com.entrobus.credit.order;

import com.entrobus.credit.order.channel.GenSubOrderPublishChannel;
import com.entrobus.credit.order.channel.LogPublishChannel;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
@EnableCircuitBreaker
//@EnableOAuth2Client
@EnableTransactionManagement//开启事务
@MapperScan(basePackages = "com.entrobus.credit.order.dao")
@EnableBinding({GenSubOrderPublishChannel.class,LogPublishChannel.class})
public class OrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }

}