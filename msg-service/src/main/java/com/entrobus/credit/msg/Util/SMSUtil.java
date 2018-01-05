package com.entrobus.credit.msg.Util;

import com.alibaba.fastjson.JSON;
import com.entrobus.credit.common.util.HttpClientUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@RefreshScope
public class SMSUtil {

    @Value("${sms:apikey}")
    private static String apikey;

    @Value("${sms:postUrl}")
    private static String postUrl;
    //http://sms.yunpian.com/v2/sms/single_send.json
    /*{
        "code": 0,
            "msg": "发送成功",
            "count": 1,
            "fee": 0.05,
            "unit": "RMB",
            "mobile": "13200000000",
            "sid": 3310228982
    }
    */
    public static String smsSend(String areaCode, String mobile, String text) throws UnsupportedEncodingException {
        Map<String, String> params = new HashMap<>();//请求参数集合
        params.put("apikey", apikey);
        params.put("text", text);
        params.put("mobile", "+" + areaCode + mobile);
        String result = HttpClientUtil.doPost(postUrl, params);
//        Map smMap = JSON.parseObject(result);
        return result;
    }
}
