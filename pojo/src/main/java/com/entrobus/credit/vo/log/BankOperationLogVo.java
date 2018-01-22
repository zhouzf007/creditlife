package com.entrobus.credit.vo.log;

import java.util.Date;

/**
 * 资金方操作日志
 */
public class BankOperationLogVo {
    private String id;
    private String desc;
    private Date time;
    private String operatorId;
    private String remark;
    private Integer operationState;
    private Integer operationStateName;
    private String resultMsg;

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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
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

    public Integer getOperationStateName() {
        return operationStateName;
    }

    public void setOperationStateName(Integer operationStateName) {
        this.operationStateName = operationStateName;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }
}
