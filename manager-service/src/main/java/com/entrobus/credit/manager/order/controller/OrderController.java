package com.entrobus.credit.manager.order.controller;

import com.entrobus.credit.common.annotation.RecordLog;
import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.manager.client.OrderClient;
import com.entrobus.credit.manager.client.PaymentClient;
import com.entrobus.credit.manager.common.bean.SysLoginUserInfo;
import com.entrobus.credit.manager.common.controller.ManagerBaseController;
import com.entrobus.credit.vo.order.OrderDtlVo;
import com.entrobus.credit.vo.order.OrderUpdateVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RefreshScope
@RestController
@RequestMapping("/order")
public class OrderController extends ManagerBaseController {

    @Autowired
    private OrderClient orderClient;

    @Autowired
    private PaymentClient paymentClient;

    /**
     * 订单列表
     */
    @GetMapping("/orderList")
    public WebResult getOrderList(Integer offset, Integer limit, String states,String key) {
        SysLoginUserInfo sys=getCurrLoginUser();
        return orderClient.getOrderList(states,key, sys.getOrgId(), offset, limit);
    }

    /**
     * 订单详情
     */
    @GetMapping("/orderDtl")
    public WebResult getOrderDtl(String id) {
        OrderDtlVo vo = orderClient.getOrderDtl(id);
        return WebResult.ok(vo);
    }

    /**
     * 订单审核
     * 驳回，通过，放款
     */
    @PutMapping("/orderState")
    @RecordLog(desc = "订单审核")
    public WebResult updateOrderState(String id, Integer state, String reason, Integer rejectType, String loanTime, String money) throws ParseException {
        if (StringUtils.isEmpty(id) || state == null) {
            return WebResult.fail(WebResult.CODE_PARAMETERS, "参数有误");
        }
        SysLoginUserInfo sys = getCurrLoginUser();
        OrderUpdateVo order = new OrderUpdateVo();
        order.setId(id);
        order.setState(state);
        order.setReason(reason);
        order.setRejectType(rejectType);
        order.setLoanTimeStr(loanTime);
        order.setActualMoney(StringUtils.isNotEmpty(money)?Long.valueOf(money):0);
        order.setOrgId(sys.getOrgId());
        order.setAuditor(sys.getRealName());
        order.setUpdateOperator(sys.getRealName());
        order.setLoanOperator(sys.getRealName());
        return orderClient.updateOrder(order);
    }


    /**
     * 还款列表
     *
     * @return
     */
    @GetMapping("/repayment/list")
    public WebResult getRepaymentList(Integer offset, Integer limit, String states,  String key) {
        SysLoginUserInfo sys=getCurrLoginUser();
        return orderClient.getOrderList(states, key,sys.getOrgId(), offset, limit);
    }

    /**
     * 还款订单详情
     *
     * @return
     */
    @GetMapping("/repayment/detail")
    public WebResult getRepaymentDetail(String id) {
        OrderDtlVo vo = orderClient.getOrderDtl(id);
        return WebResult.ok(vo);
    }

    /**
     * 还款计划状态更新
     *
     * @return
     */
    @PutMapping("/repaymentPlan")
    @RecordLog(desc = "修改还款状态")
    public WebResult updatePaymentState(String id, Integer state) {
        SysLoginUserInfo sys = getCurrLoginUser();
        OrderUpdateVo vo = new OrderUpdateVo();
        vo.setState(state);
        vo.setUpdateOperator(sys.getRealName());
        return paymentClient.updateRepaymentPlan(id, vo);
    }

}