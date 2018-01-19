package com.entrobus.credit.user.services.impl;

import com.alibaba.fastjson.JSONArray;
import com.entrobus.credit.cache.CacheService;
import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.common.util.HttpClientUtil;
import com.entrobus.credit.pojo.user.UserAccount;
import com.entrobus.credit.user.services.BsBankService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mozl on 2018/1/18.
 */
@Service
public class BsBankServiceImpl implements BsBankService {

    @Autowired
    private RedisTemplate redisTemplate;

    private final String TOKENKEY = "bank:bsapi:login:token";
    private final String LOGINURL = "http://dig.entrobus.com/api/user/login?userName=creditlife&password=creditlife2018";
    private final String DIGURL = "http://dig.entrobus.com/api/query/haina";

    @Override
    public WebResult verify(Map<String, String> map) {
        String token = CacheService.getString(redisTemplate, TOKENKEY);
        if(StringUtils.isBlank(token)){
            token = login();
        }
        map.put("meal", "BankFourPro");
        map.put("token", token);
        String json = HttpClientUtil.doPost(DIGURL, map);
        if(StringUtils.isNotBlank(json)){
            System.out.println(json);
            Map rmap = (Map) JSONArray.parse(json);
            if(rmap!= null){
                if(rmap.get("code").equals("00")){
                    return WebResult.ok();
                }else if(rmap.get("code").equals("9000")){
                    token = login();
                    if(StringUtils.isNotBlank(token)){
                        verify(map);
                    }
                }
            }
        }
        return WebResult.fail(WebResult.CODE_OPERATION);
    }

    private String login(){
        String json = HttpClientUtil.doGet(LOGINURL);
        if(StringUtils.isNotBlank(json)){
            Map map = (Map) JSONArray.parse(json);
            if(map!= null && map.get("code").equals("00")){
                Map dataMap = (Map) map.get("data");
                String token = (String) dataMap.get("token");
                CacheService.setCacheObj(redisTemplate, TOKENKEY, token);
                return token;
            }
        }
        return "";
    }
}
