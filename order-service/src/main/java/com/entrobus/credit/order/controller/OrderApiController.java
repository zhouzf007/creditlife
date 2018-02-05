package com.entrobus.credit.order.controller;

import com.entrobus.credit.cache.CacheService;
import com.entrobus.credit.cache.Cachekey;
import com.entrobus.credit.common.Constants;
import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.common.util.AmountUtil;
import com.entrobus.credit.common.util.DateUtils;
import com.entrobus.credit.common.util.ImageUtil;
import com.entrobus.credit.order.client.BsStaticsClient;
import com.entrobus.credit.order.client.PaymentClient;
import com.entrobus.credit.order.client.ProductionClient;
import com.entrobus.credit.order.services.OrderCacheService;
import com.entrobus.credit.order.services.OrdersService;
import com.entrobus.credit.pojo.order.Orders;
import com.entrobus.credit.pojo.payment.RepaymentPlan;
import com.entrobus.credit.vo.order.ApplyVo;
import com.entrobus.credit.vo.order.PlanVo;
import com.entrobus.credit.vo.order.UserOrdersVo;
import com.entrobus.credit.vo.order.UserRepaymentPlanVo;
import com.entrobus.credit.vo.user.CacheUserInfo;
import com.entrobus.credit.vo.user.UserStateVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

@RefreshScope
@RestController
@RequestMapping("/api")
public class OrderApiController {

    @Autowired
    OrdersService ordersService;

    @Autowired
    OrderCacheService cacheService;

    @Autowired
    ProductionClient productionClient;


    @Autowired
    PaymentClient paymentClient;

    @Autowired
    BsStaticsClient bsStaticsClient;

    /**
     * 用户的贷款状态
     * 未贷款-1,待审核 0,待放款 1,已驳回 2,使用中 3,使用中（已逾期）4,已完成 5
     *
     * @param token
     * @return
     */
    @GetMapping(path = "/userLoanState")
    public WebResult getUserLoanState(@RequestParam("token") String token) throws Exception {
        CacheUserInfo userInfo = cacheService.getUserCacheBySid(token);
        if (userInfo == null) {
            return WebResult.fail(WebResult.CODE_NOT_LOGIN, "用户未登陆");
        }
        Orders lastOrder = ordersService.getUserLastOrder(userInfo.getId());
        UserStateVo vo = new UserStateVo();
        if (lastOrder == null) {
            vo.setState(-1);
            vo.setMoney(AmountUtil.changeF2Y(userInfo.getQuota()));
        } else {
            vo.setState(lastOrder.getState());
            vo.setApplyTime(lastOrder.getApplyTime());
            Date enterAuditTime=DateUtils.addDays(lastOrder.getApplyTime(),1);
            vo.setAuditTime(enterAuditTime.before(new Date())?enterAuditTime:null);
            vo.setMoney(AmountUtil.changeF2Y(lastOrder.getApplyMoney()));
        }
        return WebResult.ok(vo);
    }

    /**
     * 贷款申请
     *
     * @param vo
     * @return
     */
    @PostMapping(value = "/order", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResult apply(@RequestBody ApplyVo vo) throws Exception {
        CacheUserInfo userInfo = cacheService.getUserCacheBySid(vo.getToken());
        if (userInfo == null) {
            return WebResult.fail(WebResult.CODE_NOT_LOGIN, "用户未登录");
        }
        return ordersService.applyOrder(vo, userInfo);
    }

    /**
     * 借款记录列表
     *
     * @param
     * @return
     */
    @GetMapping(path = "/userOrderList")
    public WebResult getUserOrderList(@RequestParam("token") String token) throws Exception {
        CacheUserInfo userInfo = cacheService.getUserCacheBySid(token);
        if (userInfo == null) {
            return WebResult.fail(WebResult.CODE_NOT_LOGIN, "用户未登录");
        }
        List<UserOrdersVo> list = ordersService.getUserOrderList(userInfo.getId(), null, null);
        Map rsMap = new HashMap<>();
        rsMap.put("list", list);
        return WebResult.ok((Object) rsMap);
    }

    /**
     * 还款计划
     *
     * @param
     * @return
     */
    @GetMapping(path = "/repaymentPlan")
    public WebResult getRepaymentPlan(@RequestParam("token") String token, @RequestParam("orderId") String orderId) throws Exception {
        CacheUserInfo userInfo = cacheService.getUserCacheBySid(token);
        if (userInfo == null) {
            return WebResult.fail(WebResult.CODE_NOT_LOGIN, "用户未登录");
        }
        long dueTotal = 0;
        long total = 0;
        long finished = 0;
        UserRepaymentPlanVo rsVo = new UserRepaymentPlanVo();
        List<PlanVo> plist = new ArrayList<>();
        List<RepaymentPlan> list = paymentClient.getOrderRepaymentPlan(orderId);
        for (int i = 0; i < list.size(); i++) {
            RepaymentPlan plan = list.get(i);
            PlanVo vo = new PlanVo();
            //阶段 0 未到期，1 当期，2 往期
            Date dueTime = plan.getPlanTime();
            vo.setOverdue(0);
            if (DateUtils.getStartOfMonth(new Date()).after(dueTime)) {
                vo.setStatus(Constants.PLAN_STATUS.PAST);
            } else if (DateUtils.getStartOfMonth(new Date()).before(dueTime) && DateUtils.getEndDateTimeOfMonth(new Date()).after(dueTime)) {
                vo.setStatus(Constants.PLAN_STATUS.PRESENT);
                if (new Date().after(dueTime)) {
                    vo.setOverdue((int) DateUtils.getDaySub(DateUtils.formatDateTime(dueTime), DateUtils.formatDateTime(new Date())));
                }
            } else if (DateUtils.getMonth(new Date()) == DateUtils.getMonth(dueTime)) {
                vo.setStatus(Constants.PLAN_STATUS.PRESENT);
            }else {
                vo.setStatus(Constants.PLAN_STATUS.FEATURE);
            }
            Integer state=plan.getSystemState()==Constants.ORDER_STATE.OVERDUE?Constants.ORDER_STATE.OVERDUE:plan.getState();
            vo.setState(state);
            vo.setStateName(cacheService.translate(Cachekey.Translation.REPAYMENT_STATE + state));
            vo.setDueTime(plan.getPlanTime());
            vo.setInterest(AmountUtil.changeF2Y(plan.getInterest()));
            vo.setPrincipal(AmountUtil.changeF2Y(plan.getPrincipal()));
            vo.setCapital(AmountUtil.changeF2Y(plan.getPrincipal()));
            plist.add(vo);
            if (plan.getState() == Constants.REPAYMENT_ORDER_STATE.PASS || plan.getState() == Constants.REPAYMENT_ORDER_STATE.OVERDUE) {
                dueTotal += plan.getPrincipal() + plan.getInterest();
            }else {
                total+= plan.getPrincipal() + plan.getInterest();
                finished++;
            }
        }
        if (finished==list.size()){
            rsVo.setBalance(AmountUtil.changeF2Y(total));
        }else {
            rsVo.setBalance(AmountUtil.changeF2Y(dueTotal));
        }
        rsVo.setPlanList(plist);
        return WebResult.ok(rsVo);
    }
    @PostMapping(path = "/imgBase64")
    public WebResult textImageBase64(String imgUrl) throws IOException {
        return WebResult.ok().data(ImageUtil.getImageBase64Src(imgUrl));
    }

}