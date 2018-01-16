package com.entrobus.credit.manager.bank.controller;

import com.entrobus.credit.manager.bank.service.LoanProductService;
import com.entrobus.credit.manager.common.controller.ManagerBaseController;
import com.entrobus.credit.vo.loan.LoanProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
@RequestMapping("/loan/rate")
public class LoanRateController extends ManagerBaseController{

    @Autowired
    private LoanProductService loanProductService;

    /**
     * 根据配置ID查询资金方贷款配置信息
     * @param id
     * @return
     */
    @GetMapping("getInfoById/{id}")
    public LoanProductVo getInfoById(@PathVariable("id") String id) {
        return loanProductService.getInfoById(id);
    }

    /**
     * 根据资金方ID查询贷款配置信息
     * @param orgId
     * @return
     */
    @GetMapping("getInfoByOrgId/{orgId}")
    public LoanProductVo getInfoByOrgId(@PathVariable("orgId") String orgId) {
        return loanProductService.getInfoByOrgId(orgId);
    }
}