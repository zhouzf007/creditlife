package com.entrobus.credit.msg.services;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by zhouzf on 2017/12/28.
 */
public interface MsgService {

    String sendVerificationCode(String mobile, String content);

    String sendMessage(String mobile, String content);
}
