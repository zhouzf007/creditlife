package com.entrobus.credit.user.services.impl;

import com.entrobus.credit.cache.CacheService;
import com.entrobus.credit.cache.Cachekey;
import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.user.client.BankFourClient;
import com.entrobus.credit.user.services.BsBankService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

/**
 * Created by mozl on 2018/1/18.
 */
@RefreshScope
@Service
public class BsBankServiceImpl implements BsBankService {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private BankFourClient bankFourClient;

//    private final String TOKENKEY = "bank:bsapi:login:token";
    @Value("${bsApi.server.userName}")
    private String userName ;
    @Value("${bsApi.server.password}")
    private String password ;
    @Override
    public WebResult verify(Map<String, String> map) {
        String token = CacheService.getString(redisTemplate, Cachekey.BSBANK_TOKEN);
        if(StringUtils.isBlank(token)){
            token = login();
        }
        map.put("meal", "BankFourPro");
        map.put("token", token);
        Map<String,Object> rmap = bankFourClient.verify(map);
        if(rmap!= null){
            if(Objects.equals(rmap.get("code"),"00")){
                if (rmap.get("data") != null){
                    Map<String,Object> data = (Map<String, Object>) rmap.get("data");
                    Map<String, Object> product = data == null ? null : (Map<String, Object>)data.get("product");
                    String result = product == null ? null : (String) product.get("result");
                    if (Objects.equals(result,"00")){
                        return WebResult.ok("验证成功");
                    }else if (Objects.equals(result,"10")){
                        return WebResult.fail("验证失败，可能原因：信息输入格式错误。");
                    }else if (Objects.equals(result,"11")){
                        return WebResult.fail("验证失败，可能原因：卡挂失、卡冻结或银行卡未开通相关支付功能，请咨询发卡行确认。");
                    }else {
                        return WebResult.fail("验证失败，可能原因：您输入的信息与银行卡预留信息不符。");
                    }

                }
            }else if(rmap.get("code").equals("9000")){
                token = login();
                if(StringUtils.isNotBlank(token)){
                    verify(map);
                }
            }
        }
        return WebResult.fail(WebResult.CODE_OPERATION);
    }

    private String login(){
        Map<String,Object> result = bankFourClient.login(userName,password);
        if (result != null && Objects.equals(result.get("code"),"00")) {
            Map dataMap = (Map) result.get("data");
            String token = (String) dataMap.get("token");
            CacheService.setCacheObj(redisTemplate, Cachekey.BSBANK_TOKEN, token);
            return token;
        }
        return "";
    }
}
