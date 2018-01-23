package com.entrobus.credit.base;

import com.entrobus.credit.cache.CacheService;
import com.entrobus.credit.cache.Cachekey;
import com.entrobus.credit.common.Constants;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
@EnableCircuitBreaker
@EnableTransactionManagement//开启事务
@MapperScan(basePackages = "com.entrobus.credit.base.dao")
public class BaseServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaseServiceApplication.class, args);
    }

//    @Autowired
//    RedisTemplate redisTemplate;
        //-----------迁移到TranslationCacheServiceImpl中
//    @Bean
//    protected Integer redisInit() {
//        CacheService.setString(redisTemplate, Cachekey.Translation.ORDER_STATE + Constants.ORDER_STATE.NOT_LOAN, "未申请");
//        CacheService.setString(redisTemplate, Cachekey.Translation.ORDER_STATE + Constants.ORDER_STATE.AUIDT_PENGDING, "待审核");
//        CacheService.setString(redisTemplate, Cachekey.Translation.ORDER_STATE + Constants.ORDER_STATE.LOAN_PENGDING, "待放款");
//        CacheService.setString(redisTemplate, Cachekey.Translation.ORDER_STATE + Constants.ORDER_STATE.REJECTION, "已驳回");
//        CacheService.setString(redisTemplate, Cachekey.Translation.ORDER_STATE + Constants.ORDER_STATE.PASS, "使用中");
//        CacheService.setString(redisTemplate, Cachekey.Translation.ORDER_STATE + Constants.ORDER_STATE.OVERDUE, "已逾期");
//        CacheService.setString(redisTemplate, Cachekey.Translation.ORDER_STATE + Constants.ORDER_STATE.FINISHED, "已完成");
//        CacheService.setString(redisTemplate, Cachekey.Translation.REPAYMENT_STATE + Constants.REPAYMENT_ORDER_STATE.PASS, "使用中");
//        CacheService.setString(redisTemplate, Cachekey.Translation.REPAYMENT_STATE + Constants.REPAYMENT_ORDER_STATE.OVERDUE, "已逾期");
//        CacheService.setString(redisTemplate, Cachekey.Translation.REPAYMENT_STATE + Constants.REPAYMENT_ORDER_STATE.FINISHED, "已结清");
//        return 1;
//    }
}