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

import java.util.Map;

@FeignClient(name = "log-service",fallback = LogClient.LogClientFallBack.class)
public interface LogClient {

    /**
     * 资金方平台 操作日志列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/bank/operationLog",consumes = "application/json")
    WebResult bankOperationLogList( @RequestParam(value = "orgId")String orgId,
                                   @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                                   @RequestParam(value = "pageSize",defaultValue = "20") Integer pageSize);

    /**
     * 熵商后台操作日志列表
     * @param map
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/manager/operationLog",consumes = "application/json")
    WebResult managerOperationLogList(@RequestParam Map<String,Object> map,
                                      @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                                      @RequestParam(value = "pageSize",defaultValue = "20") Integer pageSize);

    /**
     * 操作日志详情
     * @return
     */
    @GetMapping("/manager/loginLog")
    WebResult sysLoginLogList(@RequestParam("sysUserId") String sysUserId,
                                     @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                                     @RequestParam(value = "pageSize",defaultValue = "20") Integer pageSize);
    /**
     * 操作日志详情
     * @param id
     * @return
     */
    @GetMapping("/manager/operationLog/detail")
    ManagerOperationLogDetail managerOperationLogDetail(@RequestParam("id") String id);
    class LogClientFallBack implements LogClient {
        private static final Logger LOGGER = LoggerFactory.getLogger( LogClient.LogClientFallBack.class);
        @Override
        public WebResult bankOperationLogList(String orgId, Integer pageNum, Integer pageSize) {
            LOGGER.info("bankOperationLogList异常发生，进入fallback方法");
            return null;
        }

        @Override
        public WebResult managerOperationLogList(Map<String,Object> map, Integer pageNum, Integer pageSize) {
            LOGGER.info("managerOperationLogList异常发生，进入fallback方法");
            return null;
        }

        /**
         * 操作日志详情
         *
         * @param sysUserId
         * @param pageNum
         * @param pageSize
         * @return
         */
        @Override
        public WebResult sysLoginLogList(String sysUserId, Integer pageNum, Integer pageSize) {
            LOGGER.info("sysLoginLogList异常发生，进入fallback方法");
            return null;
        }

        @Override
        public ManagerOperationLogDetail managerOperationLogDetail(String id) {
            LOGGER.info("managerOperationLogDetail异常发生，进入fallback方法");

            return null;
        }
    }
}
