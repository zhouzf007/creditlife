package com.entrobus.credit.wechat.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 微信公众号基本配置信息
 */
@ConfigurationProperties
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

    /**
     * 微信服务器
     */
    @Value("${wechat.server}")
    private String server;

    /**
     * 微信前端UI服务器地址
     */
    @Value("${wechat.frontServer}")
    private String frontServer;

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

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getFrontServer() {
        return frontServer;
    }

    public void setFrontServer(String frontServer) {
        this.frontServer = frontServer;
    }
}
