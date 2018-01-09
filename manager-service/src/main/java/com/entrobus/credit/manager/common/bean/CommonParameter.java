package com.entrobus.credit.manager.common.bean;

import java.io.Serializable;

/**
 * 公共请求参数
 */
public class CommonParameter implements Serializable {

    private String token;
    private Integer platform;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getPlatform() {
        return platform;
    }

    public void setPlatform(Integer platform) {
        this.platform = platform;
    }
}
