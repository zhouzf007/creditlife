package com.entrobus.credit.vo.log;

import java.io.Serializable;
import java.util.List;

public class LogQueryVo implements Serializable {
    private String operatorId;
    private List<String> operatorIdList;
    private Integer operationState;
    private Integer platform;
    private String orgId;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public List<String> getOperatorIdList() {
        return operatorIdList;
    }

    public void setOperatorIdList(List<String> operatorIdList) {
        this.operatorIdList = operatorIdList;
    }

    public Integer getOperationState() {
        return operationState;
    }

    public void setOperationState(Integer operationState) {
        this.operationState = operationState;
    }

    public Integer getPlatform() {
        return platform;
    }

    public void setPlatform(Integer platform) {
        this.platform = platform;
    }
}
