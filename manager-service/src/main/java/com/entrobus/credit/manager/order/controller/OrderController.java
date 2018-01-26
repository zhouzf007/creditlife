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
     * 还款计划状态更新
     * @return
     */
    @PutMapping("/repaymentPlan")
    @RecordLog(desc = "修改还款状态")
    public WebResult updatePaymentState(String id, Integer state) {
        return paymentClient.updateRepaymentPlan(id,state);
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


    @GetMapping("/testList")
    public WebResult testList(Integer offset, Integer limit, Integer state) {
        PageHelper.startPage(offset, limit);
        List<UserOrderListVo> list = new ArrayList<>();
        for (int i = (offset - 1) * limit + 1; i <= limit * offset; i++) {
            UserOrderListVo vo = new UserOrderListVo();
            vo.setId(GUIDUtil.genRandomGUID());
//            vo.setApplyTime(new Date());
//            vo.setApplyNo("NO:" + i);
            vo.setMoney(i * 100 + "");
            vo.setScore(i);
            vo.setUserName("user:" + i);
//            vo.setUpdateTime(new Date());
            vo.setMobile("12345678910");
            vo.setUserId("id:" + i);
            vo.setStateName(state == -1 ? "全部" : (state == 0 ? "待审核" : (state == 1 ? "待放款" : (state == 2 ? "已驳回" : "其他"))));
            list.add(vo);
        }
        PageInfo pageInfo = new PageInfo<>(list);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("total", 10 * limit);
        dataMap.put("rows", list);
        return WebResult.ok(dataMap);
    }

    @GetMapping("/testDetail")
    public WebResult testDetail(String id) {
        OrderDtlVo vo = new OrderDtlVo();
        vo.setId(id);
        vo.setUserId(GUIDUtil.genRandomGUID());
        vo.setMoney("1,000,100,200,100");
        vo.setUsage("个人日常消费");
        vo.setApplyNo(GUIDUtil.genRandomGUID());
        vo.setIdCard("43132219891228888x");
        vo.setName("张三");
        vo.setApplyTime(new Date());
        vo.setMobile("13800138000");
        vo.setContractId(GUIDUtil.genRandomGUID());
        vo.setRepaymentTerm("2020-12-31 23:59");
        vo.setRepaymentType("每月等额");
        vo.setRate("0.0001");
        vo.setScore(99);
        vo.setAccount("建设银行信用卡0001");
        vo.setCreditReportId(GUIDUtil.genRandomGUID());
        vo.setStateName("待审核");
        vo.setAuditor("小红");
        vo.setAuditTime("2018-01-17 15:43");
        vo.setLoanOperator("大红");
        vo.setLoanTime("2018-1-17 15:44");
        return WebResult.ok(vo);
    }


    @GetMapping("/repayment/testList")
    public WebResult repaymentList(Integer offset, Integer limit, Integer state) {
        PageHelper.startPage(offset, limit);
        List<UserOrderListVo> list = new ArrayList<>();
        for (int i = (offset - 1) * limit + 1; i <= limit * offset; i++) {
            UserOrderListVo vo = new UserOrderListVo();
            vo.setId(GUIDUtil.genRandomGUID());
//            vo.setApplyTime(new Date());
//            vo.setApplyNo("NO:" + i);
            vo.setMoney(i * 100 + "");
            vo.setScore(i);
            vo.setUserName("user:" + i);
//            vo.setUpdateTime(new Date());
            vo.setMobile("12345678910");
            vo.setUserId("id:" + i);
            vo.setStateName(state == -1 ? "全部" : (state == 0 ? "还款中" : (state == 1 ? "已完成" : "其他")));
            list.add(vo);
        }
        PageInfo pageInfo = new PageInfo<>(list);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("total", 10 * limit);
        dataMap.put("rows", list);
        return WebResult.ok(dataMap);
    }

    @GetMapping("/repayment/testDetail")
    public WebResult repaymentDetail(String id) {
        OrderDtlVo vo = new OrderDtlVo();
        vo.setId(id);
        vo.setUserId(GUIDUtil.genRandomGUID());
        vo.setMoney("1,000,100,200,100");
        vo.setUsage("个人日常消费");
        vo.setApplyNo(GUIDUtil.genRandomGUID());
        vo.setIdCard("43132219891228888x");
        vo.setName("张三");
        vo.setApplyTime(new Date());
        vo.setMobile("13800138000");
        vo.setContractId(GUIDUtil.genRandomGUID());
        vo.setRepaymentTerm("2020-12-31 23:59");
        vo.setRepaymentType("每月等额");
        vo.setRate("0.0001");
        vo.setScore(99);
        vo.setAccount("建设银行信用卡0001");
        vo.setCreditReportId(GUIDUtil.genRandomGUID());
        vo.setStateName("待审核");
        vo.setAuditor("小红");
        vo.setAuditTime("2018-01-17 15:43");
        vo.setLoanOperator("大红");
        vo.setLoanTime("2018-1-17 15:44");
        List<RepaymentPlanVo> planList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            RepaymentPlanVo planVo = new RepaymentPlanVo();
            planVo.setId(GUIDUtil.genRandomGUID());
            planVo.setMoney("123,456,789");
            planVo.setRepayTime(new Date());
            planVo.setStateName(i < 2 ? "已结清" : (i >= 2 && i < 4 ? "已逾期" : "待还款"));
            planVo.setOperator("小红");
            planVo.setUpdateTime(new Date());
            planList.add(planVo);
        }
        vo.setList(planList);
        return WebResult.ok(vo);
    }
}