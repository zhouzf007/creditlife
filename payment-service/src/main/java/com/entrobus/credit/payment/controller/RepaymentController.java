package com.entrobus.credit.payment.controller;

import com.entrobus.credit.payment.services.RepaymentPlanService;
import com.entrobus.credit.payment.services.RepaymentService;
import com.entrobus.credit.pojo.payment.Repayment;
import com.entrobus.credit.pojo.payment.RepaymentPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RefreshScope
@RestController
public class RepaymentController extends PaymentBaseController{

    @Autowired
    private RepaymentService repaymentService;

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

}
