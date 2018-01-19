package com.entrobus.credit.vo.common;

import java.io.Serializable;

/**
 * 接口公共参数vo
 */
public class CommonParameter implements Serializable {
    private String client;//客户端类型
    private String datetime;//时间戳
    private String deviceId;//设备id
    private String platform;//平台
    private String version;//客户端版本

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
