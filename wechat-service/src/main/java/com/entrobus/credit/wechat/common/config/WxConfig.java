package com.entrobus.credit.wechat.common.config;

import org.springframework.beans.factory.annotation.Value;

/**
 * 微信公众号基本配置信息
 */
public class WxConfig {

    @Value("${wechat.appId}")
    private String appId;

    @Value("${wechat.appSecret}")
    private String appSecret;

    @Value("${wechat.token}")
    private String token;

    @Value("${wechat.aesKey}")
    private String aesKey;

    @Value("${wechat.partenerId}")
    private String partenerId;

    @Value("${wechat.partenerKey}")
    private String partenerKey;


    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAesKey() {
        return aesKey;
    }

    public void setAesKey(String aesKey) {
        this.aesKey = aesKey;
    }

    public String getPartenerId() {
        return partenerId;
    }

    public void setPartenerId(String partenerId) {
        this.partenerId = partenerId;
    }

    public String getPartenerKey() {
        return partenerKey;
    }

    public void setPartenerKey(String partenerKey) {
        this.partenerKey = partenerKey;
    }
}
