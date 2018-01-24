package com.entrobus.credit.vo.order;

import com.entrobus.credit.pojo.order.Orders;

public class OrderExt extends Orders{
    private String loanTimeStr;

    public String getLoanTimeStr() {
        return loanTimeStr;
    }
    public void setLoanTimeStr(String loanTimeStr) {
        this.loanTimeStr = loanTimeStr;
    }
}
