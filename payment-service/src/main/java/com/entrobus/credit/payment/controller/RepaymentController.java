package com.entrobus.credit.payment.controller;

import com.entrobus.credit.common.Constants;
import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.payment.client.OrderClient;
import com.entrobus.credit.payment.services.RepaymentPlanService;
import com.entrobus.credit.payment.services.RepaymentService;
import com.entrobus.credit.pojo.order.Orders;
import com.entrobus.credit.pojo.payment.Repayment;
import com.entrobus.credit.pojo.payment.RepaymentPlan;
import com.entrobus.credit.vo.order.OrderUpdateVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RefreshScope
@RestController
@RequestMapping("/api")
public class RepaymentController extends PaymentBaseController {

    @Autowired
    private RepaymentService repaymentService;

    @Autowired
    private OrderClient orderClient;

    @Autowired
    private RepaymentPlanService repaymentPlanService;


    @GetMapping("/orderRepaymentState")
    public int getOrderRepaymentState(@RequestParam("orderId") String orderId) {
        Repayment repayment = repaymentService.getRepaymentByOrderId(orderId);
        if (repayment == null) return -1;
        else return repayment.getState();
    }

    @GetMapping("/orderRepaymentPlan")
    public List<RepaymentPlan> getOrderRepaymentPlan(@RequestParam("orderId") String orderId) {
        List<RepaymentPlan> list = repaymentPlanService.getRepaymentPlanByOrderId(orderId);
        return list;
    }

    /**
     * 订单的当前还款计划
     *
     * @param orderId
     * @return
     */
    @GetMapping("/presentRepaymentPlan")
    public RepaymentPlan getPresentRepaymentPlan(@RequestParam("orderId") String orderId) {
        RepaymentPlan plan = repaymentPlanService.getPresentRepaymentPlan(orderId);
        return plan;
    }

    /**
     * 更新还款计划状态
     * 主动变更使用该方法
     * 结清，逾期
     *
     * @param id
     * @return
     */
    @PutMapping("/repaymentPlan")
    public WebResult updateRepaymentPlan(@RequestParam("id") String id, @RequestParam("state") Integer state) {
        RepaymentPlan plan = repaymentPlanService.selectByPrimaryKey(id);
        OrderUpdateVo updateOrder = new OrderUpdateVo();
        updateOrder.setId(id);
        if (plan != null) {
            //使用中 变更为 逾期
            if (state == Constants.REPAYMENT_ORDER_STATE.OVERDUE) {
                plan.setState(Constants.REPAYMENT_ORDER_STATE.OVERDUE);
                plan.setSystemState(Constants.REPAYMENT_ORDER_STATE.OVERDUE);
                repaymentPlanService.updateByPrimaryKeySelective(plan);
                //变更订单状态
                updateOrder.setState(Constants.ORDER_STATE.OVERDUE);
                orderClient.updateOrder(plan.getOrderId(), updateOrder);

            } else if (state == Constants.REPAYMENT_ORDER_STATE.FINISHED) {
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
                        updateOrder.setState(Constants.ORDER_STATE.FINISHED);
                        orderClient.updateOrder(plan.getOrderId(), updateOrder);
                    } else if (overDuePlan.size() == 0 && order.getState() == Constants.ORDER_STATE.OVERDUE) {
                        //不存在逾期
                        updateOrder.setState(Constants.ORDER_STATE.PASS);
                        orderClient.updateOrder(plan.getOrderId(), updateOrder);
                    }
                }
            } else {
                return WebResult.fail(WebResult.CODE_NO_PERMISSION, "订单状态有误，操作失败");
            }
            return WebResult.ok("");
        } else {
            return WebResult.fail(WebResult.CODE_NO_PERMISSION, "找不到还款计划单，操作失败");
        }
    }
}
