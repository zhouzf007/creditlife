package com.entrobus.credit.manager.product;

import com.entrobus.credit.common.Constants;
import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.common.util.*;
import com.entrobus.credit.manager.bank.service.LoanProductService;
import com.entrobus.credit.manager.client.BsStaticsClient;
import com.entrobus.credit.manager.client.UserClient;
import com.entrobus.credit.manager.common.controller.ManagerBaseController;
import com.entrobus.credit.manager.common.service.ManagerCacheService;
import com.entrobus.credit.vo.base.BsStaticVo;
import com.entrobus.credit.vo.loan.LoanPeriodsRateVo;
import com.entrobus.credit.vo.loan.LoanProductVo;
import com.entrobus.credit.vo.order.PlanVo;
import com.entrobus.credit.vo.order.UserRepaymentPlanVo;
import com.entrobus.credit.vo.product.ProductVo;
import com.entrobus.credit.vo.user.CacheUserInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.*;

@RefreshScope
@RestController
@RequestMapping("/api")
public class ProductApiController extends ManagerBaseController {

    @Autowired
    private LoanProductService loanProductService;

    @Autowired
    BsStaticsClient bsStaticsClient;


    @Autowired
    ManagerCacheService managerCacheService;


    @Autowired
    UserClient userClient;

    @GetMapping(value = "/productInfo")
    public WebResult getProductInfo(String token) throws Exception {
        BsStaticVo defualtSp = bsStaticsClient.getByTypeAndValue(Constants.CODE_TYPE.SUPPLIER, "defualt");
        ProductVo productVo = new ProductVo();
        String orgId = "08AF5B53B9BF0299076AF64C65E26189";
        if (defualtSp != null) {
            orgId = defualtSp.getCodeName();
        }
        LoanProductVo vo = loanProductService.getInfoByOrgId(orgId);
        List<LoanPeriodsRateVo> termList = vo.getLoanPeriodsRateVoList();
        productVo.setOrgId(orgId);
        productVo.setProdId(vo.getId());
        productVo.setDesc(vo.getRemark());
        List<String> terms = new ArrayList<>();
        List<Map> repayType = new ArrayList<>();
        List<String> usages = new ArrayList<>();
        List<Map> repaymentTerms = new ArrayList<>();
        boolean type0flag = false;
        boolean type1flag = false;
        for (int i = 0; i < termList.size(); i++) {
            LoanPeriodsRateVo term = termList.get(i);
            Map rm = new HashMap<>();
            terms.add(term.getPeriods() + "个月");
            rm.put("term", term.getPeriods());
            if (StringUtils.isNotEmpty(term.getInterestCapitalRate())) {
                rm.put("0", MoneyUtils.multiply(term.getInterestCapitalRate(), "10000").longValue() + "");
                type0flag = true;
            }
            if (StringUtils.isNotEmpty(term.getMonthEqualRate())) {
                rm.put("1", MoneyUtils.multiply(term.getMonthEqualRate(), "10000").longValue() + "");
                type1flag = true;
            }
            // 0=先息后本 1=等额还款
            repaymentTerms.add(rm);
        }
        CacheUserInfo  cacheUserInfo=managerCacheService.getUserBySid(token);
        productVo.setTerms(terms);
        productVo.setRepaymentTerm(repaymentTerms);
        productVo.setMin("1000");
        productVo.setMax("30000");
        if (cacheUserInfo!=null){
            productVo.setMin("1000");
            productVo.setMax(AmountUtil.changeF2Y(cacheUserInfo.getQuota()));
        }
        // 0=先息后本 1=等额还款
        if (type0flag) {
            Map typeMap0 = new HashMap<>();
            typeMap0.put("type", "0");
            typeMap0.put("text", "先息后本");
            repayType.add(typeMap0);
        }
        if (type1flag) {
            Map typeMap1 = new HashMap<>();
            typeMap1.put("type", "1");
            typeMap1.put("text", "等额还款");
            repayType.add(typeMap1);
        }
        usages.add("个人日常消费");
        usages.add("装修");
        usages.add("旅游");
        usages.add("教育");
        usages.add("医疗");
        productVo.setRepayType(repayType);
        productVo.setUsages(usages);
        return WebResult.ok(productVo);
    }

    @GetMapping(value = "/repaymentPlan")
    public WebResult getRepaymentPlan(String prodId, String rate, String principal, Integer type, Integer term) throws Exception {
        UserRepaymentPlanVo vo = new UserRepaymentPlanVo();
        if (type == null || term == null) {
            return WebResult.fail(WebResult.CODE_PARAMETERS, "参数不正确");
        }
        List<PlanVo> planList = new ArrayList<>();
        //计算还款总额
        BigDecimal princl = MoneyUtils.newBigDecimal(principal);
        BigDecimal monthRate = MoneyUtils.divide(rate, "120000");
        BigDecimal totaLInterest = new BigDecimal(0);
        if (type == Constants.REPAYMENT_TYPE.INTEREST_CAPITAL) {
            totaLInterest = BIAPPUtils.interest(princl, monthRate, term).multiply(new BigDecimal(100));
        } else if (type == Constants.REPAYMENT_TYPE.MONTH_EQUAL) {
            totaLInterest = CPMUtils.interest(princl, monthRate, term).multiply(new BigDecimal(100));
        }
        BigDecimal princl00 = MoneyUtils.multiply(princl, new BigDecimal(100));
        vo.setBalance(AmountUtil.changeF2Y(new Long(MoneyUtils.add(princl00, totaLInterest).longValue())));
        vo.setInterest(AmountUtil.changeF2Y(new Long(totaLInterest.longValue())));
        Date repayDate = DateUtils.addMonths(new Date(), 1);//默认按照一月一还
        if (DateUtils.getDay(repayDate) > 28) {
            repayDate = DateUtils.setDays(repayDate, 28);//当月大于28号按28号算
        }
        for (int i = 0; i < term; i++) {
            PlanVo plan = new PlanVo();
            //计算还款日期
            plan.setDueTime(repayDate);
            if (type == Constants.REPAYMENT_TYPE.INTEREST_CAPITAL) {
//                BigDecimal monthlyRepayment = BIAPPUtils.monthlyRepayment(princl, monthRate, term, i + 1).multiply(new BigDecimal(100));
                BigDecimal monthlyInterest = BIAPPUtils.monthlyInterest(princl, monthRate).multiply(new BigDecimal(100));
                plan.setInterest(AmountUtil.changeF2Y(monthlyInterest.longValue()));
                plan.setPrincipal(i == term - 1 ? principal : "0,00");
                plan.setCapital(AmountUtil.changeF2Y(princl00.longValue()));
            } else if (type == Constants.REPAYMENT_TYPE.MONTH_EQUAL) {
                BigDecimal monthlyRepayment = CPMUtils.monthlyRepayment(princl, monthRate, term).multiply(new BigDecimal(100));
                BigDecimal monthlyInterest = CPMUtils.monthlyInterest(princl, monthRate, monthlyRepayment, i+1).multiply(new BigDecimal(100));
                plan.setInterest(AmountUtil.changeF2Y(monthlyInterest.longValue()));
                BigDecimal monthlyPrincipal = CPMUtils.monthPrincipal(monthlyRepayment, monthlyInterest);
                plan.setPrincipal(AmountUtil.changeF2Y(monthlyPrincipal.longValue()));
            }
            //计算本息
            planList.add(plan);
            repayDate = DateUtils.addMonths(repayDate, 1);
        }
        vo.setPlanList(planList);
        return WebResult.ok(vo);
    }

}