package com.entrobus.credit.msg.services.impl;

import com.entrobus.credit.msg.services.MsgService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhouzf on 2017/12/28.
 */
@Service
@RestController
public class MsgServiceImpl implements MsgService {
    @Override
    public String sendVerificationCode(String mobile, String content) {
        return content;
    }

    @Override
    public String sendMessage(String mobile, String content) {
        return content;
    }
}
