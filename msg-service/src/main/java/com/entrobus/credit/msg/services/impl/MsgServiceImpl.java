package com.entrobus.credit.msg.services.impl;

import com.alibaba.fastjson.JSON;
import com.entrobus.credit.common.Constants;
import com.entrobus.credit.common.util.FreemarkerUtil;
import com.entrobus.credit.msg.Util.SMSUtil;
import com.entrobus.credit.msg.services.MsgService;
import com.entrobus.credit.msg.services.SmsLogService;
import com.entrobus.credit.pojo.msg.SmsLog;
import com.entrobus.credit.vo.user.MsgVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public String sendVerificationCode(MsgVo msg) throws Exception {
        String mobile = msg.getMobile();
        String areaCode = msg.getAreaCode();
        Random rnd = new Random();
        //生成4位数随机数字验证码
        Integer verifyCode = rnd.nextInt(8999) + 1000;
        Map<String, String> map = new HashMap<>();
        map.put("verifyCode", String.valueOf(verifyCode));
        String content = FreemarkerUtil.getTemplateContent("validateCode.ftl", map);
        String result = SMSUtil.smsSend(areaCode, mobile, content);
        SmsLog log = new SmsLog();
        log.setTargets(mobile);
        log.setTitle("短信验证码");
        log.setContent(content);
        log.setPushType(Constants.SMS_TYPE.VERIFICATION);
        log.setResult(result);
        smsLogService.insertSelective(log);
        return String.valueOf(verifyCode);
    }

    @Override
    public String sendMessage(MsgVo msg) throws Exception {
        String rsMsg = new String();
        SmsLog log = new SmsLog();
        String mobile = msg.getMobile();
        String areaCode = msg.getAreaCode();
        log.setTargets(msg.getMobile());
        log.setTitle(msg.getTitle());
        log.setContent(msg.getContent());
        log.setPushType(Constants.SMS_TYPE.VERIFICATION);
        String result = SMSUtil.smsSend(areaCode, mobile, msg.getContent());
        log.setResult(result);
        smsLogService.insertSelective(log);
        if (StringUtils.isNotEmpty(result)) {
            Map rsMap = JSON.parseObject(result);
            if (!rsMap.isEmpty() && rsMap.containsKey("msg"))
                rsMsg = rsMap.get("msg").toString();
        }
        return rsMsg;
    }
}
