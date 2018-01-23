package com.entrobus.credit.order.client;

import com.entrobus.credit.vo.loan.LoanProductVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by zhouzf on 2017/12/28.
 */
@FeignClient(name = "manager-service", fallback = ProductionClient.PaymentClientFallback.class)
public interface ProductionClient {

    /**
     * 根据配置ID查询资金方贷款配置信息
     *
     * @param id
     * @return
     */
    @GetMapping("/loan/rate/getInfoById/{id}")
    LoanProductVo getInfoById(@PathVariable("id") String id);

    /**
     * 根据资金方ID查询贷款配置信息
     *
     * @param orgId
     * @return
     */
    @GetMapping("/loan/rate/getInfoByOrgId/{orgId}")
    LoanProductVo getInfoByOrgId(@PathVariable("orgId") String orgId);


    /**
     * 贷款产品校验
     * 产品id，分期期数，还款方式
     *
     * @param prodId
     * @return
     */
    @GetMapping("/loan/rate/prodValidation")
    boolean checkProd(@RequestParam("prodId") String prodId, @RequestParam("repaymentType") Integer repaymentType, @RequestParam("repaymentTerm") Integer repaymentTerm, @RequestParam("rate") long rate);


    class PaymentClientFallback implements ProductionClient {

        private static final Logger LOGGER = LoggerFactory.getLogger(PaymentClientFallback.class);

        @Override
        public LoanProductVo getInfoById(String id) {
            return null;
        }

        @Override
        public LoanProductVo getInfoByOrgId(String orgId) {
            return null;
        }

        @Override
        public boolean checkProd(String prodId, Integer repaymentType, Integer repaymentTerm, long rate) {
            return false;
        }
    }
}
