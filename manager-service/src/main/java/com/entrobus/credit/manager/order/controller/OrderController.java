package com.entrobus.credit.manager.order.controller;

import com.entrobus.credit.common.annotation.RecordLog;
import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.common.util.GUIDUtil;
import com.entrobus.credit.manager.client.OrderClient;
import com.entrobus.credit.manager.client.PaymentClient;
import com.entrobus.credit.manager.common.bean.SysLoginUserInfo;
import com.entrobus.credit.manager.common.controller.ManagerBaseController;
import com.entrobus.credit.vo.order.*;
import com.entrobus.credit.vo.order.UserOrderListVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.*;

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
    public WebResult getOrderList(Integer offset, Integer limit, String states, String orgId) {
        return orderClient.getOrderList(states, orgId, offset, limit);
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
    public WebResult updateOrderState(String id, Integer state, String reason, Integer rejectType, String loanTime, Long money) throws ParseException {
        if (StringUtils.isEmpty(id) || state == null) {
            return WebResult.error(WebResult.CODE_PARAMETERS, "参数有误");
        }
        SysLoginUserInfo sys = getCurrLoginUser();
        OrderUpdateVo order = new OrderUpdateVo();
        order.setId(id);
        order.setState(state);
        order.setReason(reason);
        order.setRejectType(rejectType);
        order.setLoanTimeStr(loanTime);
        order.setActualMoney(money);
        order.setOrgId(sys.getOrgId());
        order.setAuditor(sys.getUsername());
        order.setUpdateOperator(sys.getUsername());
        order.setLoanOperator(sys.getUsername());
        return orderClient.updateOrder(order);
    }


    /**
     * 还款列表
     *
     * @return
     */
    @GetMapping("/repayment/list")
    public WebResult getRepaymentList(Integer offset, Integer limit, String states, String orgId) {
        return orderClient.getOrderList(states, orgId, offset, limit);
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
     * @return
     */
    @PutMapping("/repaymentPlan")
    @RecordLog(desc = "修改还款状态")
    public WebResult updatePaymentState(String id, Integer state) {
        return paymentClient.updateRepaymentPlan(id,state);
    }

}