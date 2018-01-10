package com.entrobus.credit.vo.log;

import java.io.Serializable;
import java.util.Date;

public class SysLoginMsg implements Serializable{
    private String sysUserId;
    private String address;
    private String browser;
    private String operationSystem;
    private Date loginTime;
    private Integer platform;

    public String getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(String sysUserId) {
        this.sysUserId = sysUserId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getOperationSystem() {
        return operationSystem;
    }

    public void setOperationSystem(String operationSystem) {
        this.operationSystem = operationSystem;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Integer getPlatform() {
        return platform;
    }

    public void setPlatform(Integer platform) {
        this.platform = platform;
    }
}
