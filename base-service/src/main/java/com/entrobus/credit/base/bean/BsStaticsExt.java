package com.entrobus.credit.base.bean;

import com.entrobus.credit.pojo.base.BsStatics;

public class BsStaticsExt extends BsStatics {
    private String typeName;
    private String statusName;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
