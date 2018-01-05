package com.entrobus.credit.msg.services;

import com.entrobus.credit.vo.user.MsgVo;

/**
 * Created by zhouzf on 2017/12/28.
 */
public interface MsgService {

    String sendVerificationCode(MsgVo msg)throws Exception;

    String sendMessage(MsgVo msg)throws Exception;
}
