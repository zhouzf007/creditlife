package com.entrobus.credit.vo.loan;

import java.io.Serializable;
import java.util.List;

public class LoanConfigureVo implements Serializable{

    private String id;
    private String remark;
    private List<LoanPeriodsRateVo> loanPeriodsRateVoList;
    private String strLoanPeriodsRateVoList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<LoanPeriodsRateVo> getLoanPeriodsRateVoList() {
        return loanPeriodsRateVoList;
    }

    public void setLoanPeriodsRateVoList(List<LoanPeriodsRateVo> loanPeriodsRateVoList) {
        this.loanPeriodsRateVoList = loanPeriodsRateVoList;
    }

    public String getStrLoanPeriodsRateVoList() {
        return strLoanPeriodsRateVoList;
    }

    public void setStrLoanPeriodsRateVoList(String strLoanPeriodsRateVoList) {
        this.strLoanPeriodsRateVoList = strLoanPeriodsRateVoList;
    }
}
