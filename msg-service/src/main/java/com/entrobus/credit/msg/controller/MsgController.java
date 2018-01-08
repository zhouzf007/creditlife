package com.entrobus.credit.msg.controller;

import com.entrobus.credit.msg.services.MsgService;
import com.entrobus.credit.msg.services.impl.MsgServiceImpl;
import com.entrobus.credit.vo.user.MsgVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RefreshScope
public class MsgController {

    private final static Logger logger = LoggerFactory.getLogger(MsgServiceImpl.class);


    @Autowired
    private MsgService msgService;


    /**
     *  only for testing , use channel instead
     * @param mobile
     * @param areaCode
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/verificationCode/{mobile}", method = RequestMethod.POST)
    public String sendVerificationCode(@PathVariable String mobile, @RequestParam(defaultValue = "86")  String areaCode) throws Exception {
        MsgVo vo=new MsgVo();
        vo.setMobile(mobile);
        vo.setAreaCode(areaCode);
        return msgService.sendVerificationCode(vo);
    }

    /**
     * only for testing , use channel instead
     * @param mobile
     * @param areaCode
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/message/{mobile}", method = RequestMethod.POST)
    public String sendMessage(@PathVariable String mobile, @RequestParam(defaultValue = "86")  String areaCode,@RequestParam String title,@RequestParam String content) throws Exception {
        MsgVo vo=new MsgVo();
        vo.setMobile(mobile);
        vo.setAreaCode(areaCode);
        vo.setTitle(title);
        vo.setContent(content);
        return msgService.sendMessage(vo);
    }

}
