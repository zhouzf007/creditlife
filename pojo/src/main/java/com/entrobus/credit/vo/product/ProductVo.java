package com.entrobus.credit.vo.product;

import java.util.List;
import java.util.Map;

public class ProductVo {

    private String prodId;
    private String orgId;
    private Long min;//限额
    private Long max;//限额
    private String desc;
    private List<String> usages;//用途
    private List<String> terms;//期数
    private List<Map> repayType;//还款方式

    public List<Map> getRepayType() {
        return repayType;
    }

    public void setRepayType(List<Map> repayType) {
        this.repayType = repayType;
    }

    public List<String> getTerms() {

        return terms;
    }

    public void setTerms(List<String> terms) {
        this.terms = terms;
    }

    private List<Map> repaymentTerm;//期数

    public String getProdId() {
        return prodId;
    }

    public List<Map> getRepaymentTerm() {
        return repaymentTerm;
    }

    public void setRepaymentTerm(List<Map> repaymentTerm) {
        this.repaymentTerm = repaymentTerm;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;

    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public Long getMin() {
        return min;
    }

    public void setMin(Long min) {
        this.min = min;
    }

    public Long getMax() {
        return max;
    }

    public void setMax(Long max) {
        this.max = max;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<String> getUsages() {
        return usages;
    }

    public void setUsages(List<String> usages) {
        this.usages = usages;
    }

}
