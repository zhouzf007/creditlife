package com.entrobus.credit.msg.services;

/**
 * Created by zhouzf on 2017/12/28.
 */
public interface MsgService {

    String sendVerificationCode(String areaCode,String mobile)throws Exception;

    String sendMessage(String areaCode,String mobile, String content)throws Exception;
}
