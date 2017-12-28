package com.entrobus.credit.msg.services;

/**
 * Created by zhouzf on 2017/12/28.
 */
public interface MsgService {

    String sendVerificationCode(String mobile, String content);

    String sendMessage(String mobile, String content);
}
