package com.entrobus.credit.msg.services.impl;

import com.entrobus.credit.common.Constants;
import com.entrobus.credit.common.util.FreemarkerUtil;
import com.entrobus.credit.msg.Util.SMSUtil;
import com.entrobus.credit.msg.services.MsgService;
import com.entrobus.credit.msg.services.SmsLogService;
import com.entrobus.credit.pojo.msg.SmsLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by zhouzf on 2017/12/28.
 */
@Service
public class MsgServiceImpl implements MsgService {

    private final static Logger logger = LoggerFactory.getLogger(MsgServiceImpl.class);

    @Autowired
    private SmsLogService smsLogService;

    @Override
    public String sendVerificationCode(String areaCode, String mobile) throws Exception {
        Random rnd = new Random();
        //生成4位数随机数字验证码
        Integer verifyCode = rnd.nextInt(8999) + 1000;
        Map<String, String> map = new HashMap<>();
        map.put("verifyCode", String.valueOf(verifyCode));
        String content = FreemarkerUtil.getTemplateContent("validateCode.ftl", map);
        Map result=SMSUtil.smsSend(areaCode, mobile, content);
        SmsLog log=new SmsLog();
        log.setTargets(mobile);
        log.setTitle("短信验证码");
        log.setContent(content);
//        log.setPushType(Constants.);
//        log.setResult(result);
//        smsLogService.saveSmsLog(mobile, 0, "短信验证码", content, result.get());
        return "";
    }

    @Override
    public String sendMessage(String areaCode, String mobile, String content) throws Exception {
        return content;
    }
}
