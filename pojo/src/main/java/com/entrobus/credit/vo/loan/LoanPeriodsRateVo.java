package com.entrobus.credit.vo.loan;

import java.io.Serializable;

public class LoanPeriodsRateVo implements Serializable{

    private Integer periods;
    private String interestCapitalRate;
    private String monthEqualRate;

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
}
