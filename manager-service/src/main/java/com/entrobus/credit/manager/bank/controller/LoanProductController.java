package com.entrobus.credit.manager.bank.controller;

import com.alibaba.fastjson.JSONArray;
import com.entrobus.credit.common.Constants;
import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.common.util.ConversionUtil;
import com.entrobus.credit.manager.bank.service.LoanProductService;
import com.entrobus.credit.manager.common.controller.ManagerBaseController;
import com.entrobus.credit.pojo.manager.LoanProduct;
import com.entrobus.credit.pojo.manager.LoanProductExample;
import com.entrobus.credit.pojo.manager.Organization;
import com.entrobus.credit.pojo.manager.OrganizationExample;
import com.entrobus.credit.vo.loan.LoanConfigureVo;
import com.entrobus.credit.vo.loan.LoanPeriodsRateVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RefreshScope
@RestController
@RequestMapping("/loan/product")
public class LoanProductController extends ManagerBaseController{

    @Autowired
    private LoanProductService loanProductService;

    @PostMapping("")
    public WebResult save(LoanConfigureVo loanConfigureVo){
        if(ConversionUtil.isEmptyParameter(loanConfigureVo.getStrLoanPeriodsRateVoList())){
            return WebResult.error("至少填一项期数利率");
        }
        return loanProductService.save(loanConfigureVo);
    }

    @GetMapping("")
    public WebResult detail() {
        return loanProductService.detail();
    }

}