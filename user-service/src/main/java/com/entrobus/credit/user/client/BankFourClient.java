package com.entrobus.credit.user.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
@RefreshScope
@FeignClient(url = "${bsApi.server.url}",name = "BankFour",fallback = BankFourClient.BankFourClientFallBack.class)
public interface BankFourClient {
    @RequestMapping("/api/user/login")
    Map<String,Object> login(@RequestParam("userName") String userName,@RequestParam("password") String password);
    @RequestMapping(value = "/api/query/TrinityForce")
    Map<String,Object> verify(@RequestParam Map<String,String> map) ;
    class BankFourClientFallBack implements BankFourClient{
        private static final Logger LOGGER = LoggerFactory.getLogger(BankFourClient.BankFourClientFallBack.class);

        @Override
        public Map<String, Object> login(String userName, String password) {
            LOGGER.info("login异常发生，进入fallback方法");
            return null;
        }

        @Override
        public Map<String, Object> verify(Map<String, String> map) {
            return null;
        }
    }
}
