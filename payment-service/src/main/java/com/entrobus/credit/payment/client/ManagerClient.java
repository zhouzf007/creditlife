package com.entrobus.credit.payment.client;

import com.entrobus.credit.vo.loan.LoanProductVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 */
@FeignClient(name = "manager-service", fallback = ManagerClient.ManagerClientFallback.class)
public interface ManagerClient {

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


    class ManagerClientFallback implements ManagerClient {

        private static final Logger LOGGER = LoggerFactory.getLogger(ManagerClientFallback.class);

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
