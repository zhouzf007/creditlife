package com.entrobus.credit.vo.loan;

import java.io.Serializable;
import java.util.List;

public class LoanProductVo implements Serializable{

    //主键
    private String id;
    //资金方ID
    private String orgId;
    //还款日期类型 0=每月借钱日
    private Integer loanDateType;
    //描述
    private String remark;
    //期数列表
    private List<LoanPeriodsRateVo> loanPeriodsRateVoList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public Integer getLoanDateType() {
        return loanDateType;
    }

    public void setLoanDateType(Integer loanDateType) {
        this.loanDateType = loanDateType;
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
}
