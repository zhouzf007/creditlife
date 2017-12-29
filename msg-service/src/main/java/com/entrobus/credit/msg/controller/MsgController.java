package com.entrobus.credit.msg.controller;

import com.entrobus.credit.msg.services.MsgService;
import com.entrobus.credit.msg.services.impl.MsgServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

@RestController
@RefreshScope
public class MsgController {

    private final static Logger logger = LoggerFactory.getLogger(MsgServiceImpl.class);

    @Autowired
    private MsgService msgService;

    @RequestMapping(value = "/message/{mobile}", method = RequestMethod.POST)
    public String sendVerificationCode(@PathVariable String mobile, @RequestParam String content) {
        return msgService.sendVerificationCode(mobile,content);
    }

    @RequestMapping(value = "/message/{mobile}", method = RequestMethod.POST)
    public String sendMessage(@PathVariable String mobile, @RequestParam String content) {
        return msgService.sendMessage(mobile,content);
    }
}
