package com.entrobus.credit.vo.user;

import java.io.Serializable;

public class SearchUserInfoVo implements Serializable{
    private String id;
//    private String name;//昵称
    private String realName;//真实姓名
    private String cellphone;//手机号
//    private String portrait;//头像


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }
}
