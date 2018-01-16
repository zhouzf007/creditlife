package com.entrobus.credit.payment;

import com.entrobus.credit.payment.channel.GenSubOrderSubscribeChannel;
import org.mybatis.spring.annotation.MapperScan;
import com.entrobus.credit.payment.channel.GenSubOrderSubscribeChannel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
@EnableCircuitBreaker
@EnableTransactionManagement//开启事务
//@EnableOAuth2Client
@MapperScan(basePackages="com.entrobus.credit.payment.dao")
@EnableBinding(GenSubOrderSubscribeChannel.class)
public class PaymentServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaymentServiceApplication.class, args);
    }
}
