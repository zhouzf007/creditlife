package com.entrobus.credit.msg.services.impl;

import com.entrobus.credit.msg.services.MsgService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

/**
 * Created by zhouzf on 2017/12/28.
 */
@Service
@RestController
public class MsgServiceImpl implements MsgService {

    private final static Logger logger = LoggerFactory.getLogger(MsgServiceImpl.class);

    @Override
    public String sendVerificationCode(String mobile, String content) {
        logger.info("mobile:"+mobile);
        logger.info("content:"+content);
        return content;
    }

    @Override
    public String sendMessage(String mobile,String content) {
        logger.info("mobile:"+mobile);
        logger.info("content:"+content);
        return content;
    }
}
