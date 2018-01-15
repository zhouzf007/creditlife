package com.entrobus.credit.manager.common.bean;


import com.entrobus.credit.pojo.manager.Organization;

public class OrganizationExt extends Organization {

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
