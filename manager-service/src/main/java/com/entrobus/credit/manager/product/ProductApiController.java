package com.entrobus.credit.manager.product;

import com.entrobus.credit.common.Constants;
import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.common.util.DateUtils;
import com.entrobus.credit.manager.bank.service.LoanProductService;
import com.entrobus.credit.manager.client.BsStaticsClient;
import com.entrobus.credit.manager.common.controller.ManagerBaseController;
import com.entrobus.credit.vo.base.BsStaticVo;
import com.entrobus.credit.vo.loan.LoanPeriodsRateVo;
import com.entrobus.credit.vo.loan.LoanProductVo;
import com.entrobus.credit.vo.order.PlanVo;
import com.entrobus.credit.vo.order.UserRepaymentPlanVo;
import com.entrobus.credit.vo.product.ProductVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RefreshScope
@RestController
@RequestMapping("/api")
public class ProductApiController extends ManagerBaseController {

    @Autowired
    private LoanProductService loanProductService;

    @Autowired
    BsStaticsClient bsStaticsClient;

    @GetMapping(value = "/productInfo")
    public WebResult getProductInfo() {
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
        for (int i = 0; i < termList.size(); i++) {
            LoanPeriodsRateVo term = termList.get(i);
            Map rm = new HashMap<>();
            terms.add(term.getPeriods() + "");
            rm.put("term", term.getPeriods());
            rm.put("0", StringUtils.isEmpty(term.getInterestCapitalRate()) ? "" : term.getInterestCapitalRate());
            rm.put("1", StringUtils.isEmpty(term.getMonthEqualRate()) ? "" : term.getMonthEqualRate());
            // 0=先息后本 1=等额还款
            repaymentTerms.add(rm);
        }
        productVo.setTerms(terms);
        productVo.setRepaymentTerm(repaymentTerms);
        productVo.setMin(1000l);
        productVo.setMax(3000l);
        Map typeMap0 = new HashMap<>();
        typeMap0.put("type", "0");
        typeMap0.put("text", "先息后本");
        Map typeMap1 = new HashMap<>();
        typeMap1.put("type", "1");
        typeMap1.put("text", "等额还款");
        repayType.add(typeMap0);
        repayType.add(typeMap1);// 0=先息后本 1=等额还款
        usages.add("个人消费");
        usages.add("装修");
        usages.add("旅游");
        usages.add("教育");
        usages.add("医疗");
        productVo.setRepayType(repayType);
        productVo.setUsages(usages);
        return WebResult.ok(productVo);
    }

    @GetMapping(value = "/repaymentPlan")
    public WebResult getRepaymentPlan(String prodId, Integer type, Integer term) {
        UserRepaymentPlanVo vo = new UserRepaymentPlanVo();
        List<PlanVo> planList = new ArrayList<>();
        //计算还款总额
        vo.setBalance(10000L);
        vo.setBalance(888L);
        for (int i = 0; i < term; i++) {
            PlanVo plan = new PlanVo();
            //计算还款日期
            plan.setDueTime(DateUtils.addMonths(new Date(), i + 1));
            plan.setPrincipalAndInterest(833L);
            //计算本息
            plan.setCapital(1000l);
            planList.add(plan);
        }
        vo.setPlanList(planList);
        return WebResult.ok(vo);
    }

}