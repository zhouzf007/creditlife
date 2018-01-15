package com.entrobus.credit.payment.controller;

import com.entrobus.credit.common.Constants;
import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.payment.client.OrderClient;
import com.entrobus.credit.payment.services.RepaymentPlanService;
import com.entrobus.credit.payment.services.RepaymentService;
import com.entrobus.credit.pojo.order.Orders;
import com.entrobus.credit.pojo.payment.Repayment;
import com.entrobus.credit.pojo.payment.RepaymentExample;
import com.entrobus.credit.pojo.payment.RepaymentPlan;
import com.sun.corba.se.spi.ior.IdentifiableFactory;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RefreshScope
@RestController
public class RepaymentController {

    @Autowired
    private RepaymentService repaymentService;

    @Autowired
    private OrderClient orderClient;

    @Autowired
    private RepaymentPlanService repaymentPlanService;


    @GetMapping("/orderRepaymentState/{orderId}")
    public int getOrderRepaymentState(@PathVariable("orderId") String orderId) {
        Repayment repayment = repaymentService.getRepaymentByOrderId(orderId);
        if (repayment == null) return -1;
        else return repayment.getState();
    }

    @GetMapping("/orderRepaymentPlan/{orderId}")
    public List<RepaymentPlan> getOrderRepaymentPlan(@PathVariable("orderId") String orderId) {
        List<RepaymentPlan> list = repaymentPlanService.getRepaymentPlanByOrderId(orderId);
        return list;
    }

    /**
     * 更新还款计划状态
     * 主动变更使用该方法
     * 结清，逾期
     *
     * @param id
     * @param repaymentPlan
     * @return
     */
    @PutMapping("/repaymentPlan/{id}")
    public WebResult updateRepaymentPlan(@PathVariable("id") String id, @RequestBody RepaymentPlan repaymentPlan) {
        RepaymentPlan plan = repaymentPlanService.selectByPrimaryKey(id);
        if (plan != null) {
            //使用中 变更为 逾期
            if (plan.getState() == Constants.REPAYMENT_ORDER_STATE.PASS && repaymentPlan.getState() == Constants.REPAYMENT_ORDER_STATE.OVERDUE) {
                plan.setState(Constants.REPAYMENT_ORDER_STATE.OVERDUE);
                plan.setSystemState(Constants.REPAYMENT_ORDER_STATE.OVERDUE);
                repaymentPlanService.updateByPrimaryKeySelective(plan);
                //变更订单状态
                Orders order = new Orders();
                order.setState(Constants.ORDER_STATE.OVERDUE);
                orderClient.updateOrder(plan.getOrderId(), order);

            } else if (plan.getState() == Constants.REPAYMENT_ORDER_STATE.PASS && repaymentPlan.getState() == Constants.REPAYMENT_ORDER_STATE.FINISHED) {
                //使用中 变更为 已结清
                plan.setState(Constants.REPAYMENT_ORDER_STATE.FINISHED);
                plan.setSystemState(Constants.REPAYMENT_ORDER_STATE.FINISHED);
                repaymentPlanService.updateByPrimaryKeySelective(plan);
                //检查订单状态
                Orders order = orderClient.getOrder(plan.getOrderId());
                List<RepaymentPlan> overDuePlan = repaymentPlanService.getOverDueRepaymentPlans(plan.getOrderId());
                List<RepaymentPlan> finishedPlan = repaymentPlanService.getFinishedRepaymentPlans(plan.getOrderId());
                if (order != null) {
                    if (order.getRepaymentTerm() == finishedPlan.size()) {
                        //整个订单完成
                        Orders updateOrder = new Orders();
                        updateOrder.setState(Constants.ORDER_STATE.FINISHED);
                        orderClient.updateOrder(plan.getOrderId(), updateOrder);
                    } else if (overDuePlan.size() == 0 && order.getState() == Constants.ORDER_STATE.OVERDUE) {
                        //不存在逾期
                        Orders updateOrder = new Orders();
                        updateOrder.setState(Constants.ORDER_STATE.PASS);
                        orderClient.updateOrder(plan.getOrderId(), updateOrder);
                    }
                }
            } else {
                return WebResult.error("订单状态有误，操作失败");
            }
            return WebResult.ok("");
        } else {
            return WebResult.error("找不到还款计划单，操作失败");
        }
    }
}
