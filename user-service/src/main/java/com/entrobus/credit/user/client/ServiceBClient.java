package com.entrobus.credit.user.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "order-service", fallback = ServiceBClient.ServiceBClientFallback.class)
public interface ServiceBClient {

    @GetMapping(value = "/")
    String printServiceB();

    @GetMapping(value = "/test")
    public String test() ;

    @Component
    class ServiceBClientFallback implements ServiceBClient {

        @Override
        public String printServiceB() {
            LOGGER.info("异常发生，进入fallback方法");
            return "SERVICE B FAILED! - FALLING BACK";
        }

        @Override
        public String test() {
            LOGGER.info("异常发生，进入fallback方法");
            return "SERVICE B FAILED! - FALLING BACK";
        }

        private static final Logger LOGGER = LoggerFactory.getLogger(ServiceBClientFallback.class);
    }
}