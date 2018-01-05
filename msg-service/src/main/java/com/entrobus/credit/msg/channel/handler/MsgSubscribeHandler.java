package com.entrobus.credit.msg.channel.handler;

import com.entrobus.credit.common.Constants;
import com.entrobus.credit.msg.channel.MsgSubscribeChannel;
import com.entrobus.credit.msg.services.MsgService;
import com.entrobus.credit.vo.user.MsgVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

/**
 * @Author: zhouzf
 * @CreateTime: 2018/01/04 11:35
 * @Description:
 */
@Component
public class MsgSubscribeHandler {
    @Autowired
    private MsgService msgService;

    @StreamListener(MsgSubscribeChannel.SMS_SUBSCRIBE)
    public void sendMsg(MsgVo msg) throws Exception {
        if (msg == null) return;
        if (StringUtils.isEmpty(msg.getMobile())) return;
        if (StringUtils.isEmpty(msg.getAreaCode())) msg.setAreaCode("86");
        if (Constants.SMS_TYPE.VERIFICATION == msg.getType()) {
            msgService.sendVerificationCode(msg);
        } else {
            msgService.sendMessage(msg);
        }
    }

}
