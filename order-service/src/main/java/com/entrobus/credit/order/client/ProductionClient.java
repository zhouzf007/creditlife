package com.entrobus.credit.order.client;

import com.entrobus.credit.vo.loan.LoanProductVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
    @GetMapping("getInfoById/{id}")
    LoanProductVo getInfoById(@PathVariable("id") String id);

    /**
     * 根据资金方ID查询贷款配置信息
     *
     * @param orgId
     * @return
     */
    @GetMapping("getInfoByOrgId/{orgId}")
    LoanProductVo getInfoByOrgId(@PathVariable("orgId") String orgId);


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
    }
}
