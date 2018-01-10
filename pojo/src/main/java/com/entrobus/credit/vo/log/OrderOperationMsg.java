package com.entrobus.credit.vo.log;

import java.io.Serializable;

public class OrderOperationMsg implements Serializable{
    /**
     * 订单id
     */
    private String orderId;
    /**
     * 操作完成后的订单状态
     */
    private Integer orderState;
    /**
     * 操作说明：自定义,如 提交申请（创建订单）、审核 等
     */
    private String desc;
    /**
     * 操作时间
     */
    private Long time;
    /**
     * 操作人id
     */
    private String operatorId;
    /**
     * 操作参数
     */
    private Object operationData;
    /**
     * 备注（1024）：自定义，如：超时、定时操作等
     */
    private String remark;
    /**
     * 操作状态：0-成功，1-失败，2-异常
     */
    private Integer operationState;

    public Integer getOperationState() {
        return operationState;
    }

    public void setOperationState(Integer operationState) {
        this.operationState = operationState;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderState() {
        return orderState;
    }

    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public Object getOperationData() {
        return operationData;
    }

    public void setOperationData(Object operationData) {
        this.operationData = operationData;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
