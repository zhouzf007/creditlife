package com.entrobus.credit.order.client;

import com.entrobus.credit.pojo.order.Contract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * Created by zhouzf on 2017/12/28.
 */
@FeignClient(name = "credit-service", fallback = CreditClient.CreditClientFallback.class)
public interface CreditClient {

    @PostMapping(value = "/api/contract", consumes = MediaType.APPLICATION_JSON_VALUE)
    Contract saveContract(@RequestBody Contract contract, @RequestBody Map<String, String> vo);


    @Component
    class CreditClientFallback implements CreditClient {

        private static final Logger LOGGER = LoggerFactory.getLogger(CreditClientFallback.class);

        @Override
        public Contract saveContract(Contract contract, Map<String, String> vo) {
            return null;
        }

    }
}
