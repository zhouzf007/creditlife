package com.entrobus.credit.msg;

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
//@EnableTransactionManagement//开启事务
//@MapperScan(basePackages="com.entrobus.credit.user.dao",sqlSessionFactoryRef="sqlSessionFactory")
public class MsgServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsgServiceApplication.class, args);
    }


}