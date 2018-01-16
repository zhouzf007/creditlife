package com.entrobus.credit.vo.loan;

import java.io.Serializable;
import java.util.List;

public class LoanPeriodsRateVo implements Serializable{

    //期数
    private Integer periods;
    //先息后本利率
    private String interestCapitalRate;
    //每月等额利率
    private String monthEqualRate;
    //还款方式 0=先息后本 1=等额还款
    private List<Integer> repaymentTypeList;

    public Integer getPeriods() {
        return periods;
    }

    public void setPeriods(Integer periods) {
        this.periods = periods;
    }

    public String getInterestCapitalRate() {
        return interestCapitalRate;
    }

    public void setInterestCapitalRate(String interestCapitalRate) {
        this.interestCapitalRate = interestCapitalRate;
    }

    public String getMonthEqualRate() {
        return monthEqualRate;
    }

    public void setMonthEqualRate(String monthEqualRate) {
        this.monthEqualRate = monthEqualRate;
    }

    public List<Integer> getRepaymentTypeList() {
        return repaymentTypeList;
    }

    public void setRepaymentTypeList(List<Integer> repaymentTypeList) {
        this.repaymentTypeList = repaymentTypeList;
    }
}
