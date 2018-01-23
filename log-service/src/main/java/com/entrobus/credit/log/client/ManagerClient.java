package com.entrobus.credit.log.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "manager-service" ,fallback = ManagerClient.ManagerClientFallBack.class)
public interface ManagerClient {

    /**
     * 获取同一机构下所有用户的id,用户名
     * @param orgId
     * @return
     */
    @GetMapping("/sys/user/org/userNameMap")
     Map<String,String> userNameMapByOrg(@RequestParam("orgId") String orgId);

    class ManagerClientFallBack implements ManagerClient{
        private static final Logger LOGGER = LoggerFactory.getLogger(ManagerClient.ManagerClientFallBack.class);

        /**
         * 获取同一机构下所有用户的id,用户名
         *
         * @param orgId
         * @return
         */
        @Override
        public Map<String, String> userNameMapByOrg(String orgId) {
            return null;
        }
    }
}
