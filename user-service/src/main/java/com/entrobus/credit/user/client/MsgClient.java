package com.entrobus.credit.user.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by zhouzf on 2017/12/28.
 */
@FeignClient(name = "msg-service", fallback = MsgClient.MsgClientFallback.class)
public interface MsgClient {

    @RequestMapping(value = "/verificationCode/{mobile}", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String sendVerificationCode(@PathVariable("mobile") String mobile,@RequestParam("content") String content);

    @RequestMapping(value = "/message/{mobile}", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String sendMessage(@PathVariable("mobile") String mobile,@RequestParam("content") String content);

    @Component
    class MsgClientFallback implements MsgClient {

        @Override
        public String sendVerificationCode(String mobile, String content) {
            LOGGER.info("异常发生，进入fallback方法");
            return "SEND VERIFICATIONCODE FAILED! - FALLING BACK";
        }

        @Override
        public String sendMessage(String mobile, String content) {
            LOGGER.info("异常发生，进入fallback方法");
            return "SEND MESSAGE FAILED! - FALLING BACK";
        }

        private static final Logger LOGGER = LoggerFactory.getLogger(MsgClientFallback.class);
    }
}
