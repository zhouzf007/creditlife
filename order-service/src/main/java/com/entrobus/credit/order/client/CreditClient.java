package com.entrobus.credit.order.client;

import com.entrobus.credit.pojo.order.CreditReport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by zhouzf on 2017/12/28.
 */
@FeignClient(name = "credit-service", fallback = CreditClient.CreditClientFallback.class)
public interface CreditClient {

    @GetMapping(value = "/api/userCreditReport")
    CreditReport getCreditReport(@RequestParam("userId") String userId);


    @Component
    class CreditClientFallback implements CreditClient {

        private static final Logger LOGGER = LoggerFactory.getLogger(CreditClientFallback.class);

        @Override
        public CreditReport getCreditReport(String userId) {
            return null;
        }

    }
}
