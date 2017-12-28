package com.entrobus.credit.msg.services;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by zhouzf on 2017/12/28.
 */
public interface MsgService {

    @RequestMapping(value = "/verificationCode/{mobile}", method = RequestMethod.POST)
    String sendVerificationCode(String mobile, String content);

    @RequestMapping(value = "/message/{mobile}", method = RequestMethod.POST)
    String sendMessage(String mobile, String content);
}
