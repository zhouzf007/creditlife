package com.entrobus.credit.manager.client;

import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.vo.log.LogQueryVo;
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

    class LogClientFallBack implements LogClient {
        private static final Logger LOGGER = LoggerFactory.getLogger( LogClient.LogClientFallBack.class);
        @Override
        public WebResult bankOperationLogList(String orgId, int pageNum, int pageSize) {
            LOGGER.info("bankOperationLogList异常发生，进入fallback方法");
            return null;
        }
    }
}
