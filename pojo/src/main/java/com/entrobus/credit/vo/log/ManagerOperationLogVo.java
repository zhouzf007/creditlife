package com.entrobus.credit.vo.log;

/**
 * 管理后台（熵商）操作日志
 */
public class ManagerOperationLogVo {
    private String id;
    private String desc;
    private String time;
    private String operatorId;
    private String remark;
    private Integer operationState;
    private String operationStateName;
    private String result;
    private String operatorName;
    private String relId;

    public String getRelId() {
        return relId;
    }

    public void setRelId(String relId) {
        this.relId = relId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getOperationState() {
        return operationState;
    }

    public void setOperationState(Integer operationState) {
        this.operationState = operationState;
    }

    public String getOperationStateName() {
        return operationStateName;
    }

    public void setOperationStateName(String operationStateName) {
        this.operationStateName = operationStateName;
    }

}
