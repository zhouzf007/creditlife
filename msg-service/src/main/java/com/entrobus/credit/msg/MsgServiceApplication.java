package com.entrobus.credit.msg;

import com.entrobus.credit.msg.channel.MsgSubscribeChannel;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;


@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
@EnableCircuitBreaker
@EnableBinding(MsgSubscribeChannel.class)
@MapperScan(basePackages="com.entrobus.credit.msg.dao",sqlSessionFactoryRef="sqlSessionFactory")
public class MsgServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsgServiceApplication.class, args);
    }

}