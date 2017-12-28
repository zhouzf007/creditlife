package com.entrobus.credit.user.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by zhouzf on 2017/12/28.
 */
@FeignClient(name = "msg-service", fallback = MsgClient.MsgClientFallback.class)
public interface MsgClient {

    String sendVerificationCode(String mobile, String content);

    String sendMessage(String mobile, String content);

    @Component
    class MsgClientFallback implements MsgClient {

        @Override
        public String sendVerificationCode(String mobile, String content) {
            LOGGER.info("异常发生，进入fallback方法");
            return "SEND VERIFICATIONCODE FAILED! - FALLING BACK";
        }

        @Override
        public String sendMessage(@PathVariable String mobile, String content) {
            LOGGER.info("异常发生，进入fallback方法");
            return "SEND MESSAGE FAILED! - FALLING BACK";
        }

        private static final Logger LOGGER = LoggerFactory.getLogger(MsgClientFallback.class);
    }
}
