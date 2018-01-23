package com.entrobus.credit.manager.client;

import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.vo.log.LogQueryVo;
import com.entrobus.credit.vo.log.ManagerOperationLogDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "log-service",fallback = LogClient.LogClientFallBack.class)
public interface LogClient {
    @GetMapping(value = "/bank/operationLog",consumes = "application/json")
    WebResult bankOperationLogList( @RequestParam(value = "orgId")String orgId,
                                   @RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                   @RequestParam(value = "pageSize",defaultValue = "20") int pageSize);
    @GetMapping(value = "/manager/operationLog",consumes = "application/json")
    WebResult managerOperationLogList( @RequestParam(value = "orgId")String orgId,
                                   @RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                   @RequestParam(value = "pageSize",defaultValue = "20") int pageSize);
    @GetMapping("/manager/operationLog/detail")
    ManagerOperationLogDetail managerOperationLogDetail(@RequestParam("id") String id);
    class LogClientFallBack implements LogClient {
        private static final Logger LOGGER = LoggerFactory.getLogger( LogClient.LogClientFallBack.class);
        @Override
        public WebResult bankOperationLogList(String orgId, int pageNum, int pageSize) {
            LOGGER.info("bankOperationLogList异常发生，进入fallback方法");
            return null;
        }

        @Override
        public WebResult managerOperationLogList(String orgId, int pageNum, int pageSize) {
            LOGGER.info("managerOperationLogList异常发生，进入fallback方法");
            return null;
        }

        @Override
        public ManagerOperationLogDetail managerOperationLogDetail(String id) {
            LOGGER.info("managerOperationLogDetail异常发生，进入fallback方法");

            return null;
        }
    }
}
