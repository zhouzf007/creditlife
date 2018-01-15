package com.entrobus.credit.manager.common.client;

import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.vo.schedule.QuartzJobVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "schedule", fallback = ScheduleClient.ScheduleClientFallback.class)
public interface ScheduleClient {
    /**
     * 添加任务
     * @param vo
     * @return
     */
    @PostMapping(value = "/job",consumes = "application/json")
    WebResult addJob(@RequestBody  QuartzJobVo vo);

    /**
     * 编辑任务
     * 暂时仅支持编辑任务 cron
     * @param vo
     * @return
     */
    @PutMapping(value = "/job/{jobName}",consumes = "application/json")
    WebResult editJob(@PathVariable("jobName") String jobName, @RequestBody  QuartzJobVo vo);

    /**
     * 任务列表
     * @return
     */
    @GetMapping("/job")
    WebResult jobList(@RequestParam("jobName") String jobName, @RequestParam("groupName") String groupName,
                      @RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize")Integer pageSize);

    /**
     * 任务组名称
     * @return
     */
    @GetMapping("/groupName")
    List<String> groupNames();

    /**
     * 删除任务
     * @param jobName
     * @return
     */
    @DeleteMapping("/job/{jobName}")
    WebResult removeJob(@PathVariable("jobName") String jobName);

    /**
     * 暂停任务
     * @param jobName
     * @param groupName
     * @return
     */
    @PostMapping("/pauseJob")
    WebResult pauseJob(@RequestParam("jobName") String jobName,@RequestParam("groupName") String groupName);

    /**
     * 恢复任务
     * @param jobName
     * @param groupName
     * @return
     */
    @PostMapping("/resumeJob")
    WebResult resumeJob(@RequestParam("jobName") String jobName,@RequestParam("groupName") String groupName);

    /**
     *  触发任务（单独执行一次）
     * @param jobName
     * @return
     */
    @PostMapping("/triggerJob")
    WebResult triggerJob( @RequestParam("jobName") String jobName, @RequestParam("groupName") String groupName);
    class ScheduleClientFallback implements ScheduleClient{
        private static final Logger LOGGER = LoggerFactory.getLogger(BsStaticsClient.BsStaticsClientFallback.class);

        /**
         * 添加任务
         *
         * @param vo
         * @return
         */
        @Override
        public WebResult addJob(QuartzJobVo vo) {
            LOGGER.info("addJob异常发生，进入fallback方法");
            return WebResult.error("服务异常");
        }

        /**
         * 编辑任务
         * 暂时仅支持编辑任务 cron
         *
         * @param jobName
         * @param vo
         * @return
         */
        @Override
        public WebResult editJob(String jobName, QuartzJobVo vo) {
            LOGGER.info("editJob异常发生，进入fallback方法");
            return WebResult.error("服务异常");
        }

        /**
         * 任务列表
         *
         * @param jobName
         * @param groupName
         * @param pageNum
         * @param pageSize
         * @return
         */
        @Override
        public WebResult jobList(String jobName, String groupName, Integer pageNum, Integer pageSize) {
            LOGGER.info("jobList异常发生，进入fallback方法");
            return WebResult.error("服务异常");
        }


        /**
         * 任务组名称
         *
         * @return
         */
        @Override
        public List<String> groupNames() {
            LOGGER.info("groupNames异常发生，进入fallback方法");
            return null;
        }

        /**
         * 删除任务
         *
         * @param jobName
         * @return
         */
        @Override
        public WebResult removeJob(String jobName) {
            LOGGER.info("removeJob异常发生，进入fallback方法");
            return WebResult.error("服务异常");
        }

        /**
         * 暂停任务
         *
         * @param jobName
         * @param groupName
         * @return
         */
        @Override
        public WebResult pauseJob(String jobName, String groupName) {
            LOGGER.info("pauseJob异常发生，进入fallback方法");
            return WebResult.error("服务异常");
        }

        /**
         * 恢复任务
         *
         * @param jobName
         * @param groupName
         * @return
         */
        @Override
        public WebResult resumeJob(String jobName, String groupName) {
            LOGGER.info("resumeJob异常发生，进入fallback方法");
            return WebResult.error("服务异常");
        }

        /**
         * 触发任务（单独执行一次）
         *
         * @param jobName
         * @param groupName
         * @return
         */
        @Override
        public WebResult triggerJob(String jobName, String groupName) {
            LOGGER.info("triggerJob异常发生，进入fallback方法");
            return WebResult.error("服务异常");
        }
    }
}
