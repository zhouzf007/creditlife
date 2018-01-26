package com.entrobus.credit.order.client;

import com.entrobus.credit.pojo.order.CreditReport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by zhouzf on 2017/12/28.
 */
@FeignClient(name = "user-service", fallback = UserClient.UserClientFallback.class)
public interface UserClient {

    @GetMapping(value = "/api/userCreditReport")
    CreditReport getCreditReport(@RequestParam("userId") String userId);

    @GetMapping(value = "/user/creditReport")
    CreditReport getCrediReport(@RequestParam("id") String id);


    @Component
    class UserClientFallback implements UserClient {

        private static final Logger LOGGER = LoggerFactory.getLogger(UserClientFallback.class);

        @Override
        public CreditReport getCreditReport(String userId) {
            return null;
        }

        @Override
        public CreditReport getCrediReport(String id) {
            return null;
        }

    }
}
