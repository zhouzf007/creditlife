package com.entrobus.credit.vo.log;
@Deprecated//暂时废弃，请使用OperationLogMsg
public class OrderOperationMsg extends OperationLogMsg{
    /**
     * 订单id
     */
    private String orderId;
    /**
     * 操作完成后的订单状态
     */
    private Integer orderState;



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

}
