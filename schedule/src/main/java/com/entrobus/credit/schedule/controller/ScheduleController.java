package com.entrobus.credit.schedule.controller;

import com.entrobus.credit.common.Constants;
import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.schedule.service.ScheduleService;
import com.entrobus.credit.vo.schedule.QuartzJobVo;
import org.apache.commons.lang3.StringUtils;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    /**
     * 拼接参数校验错误信息
     * @param result
     * @return
     */
    private String getValidationMsg(BindingResult result) {
        StringBuilder sb = new StringBuilder();
        for (ObjectError error : result.getAllErrors()) {
            if (sb.length() > 0 ) sb.append(",");
            sb.append(error.getDefaultMessage());
        }
        return sb.toString();
    }

    /**
     * 添加任务
     * @param vo
     * @param result
     * @return
     */
    @PostMapping("/job")
    public WebResult addJob(@RequestBody @Validated QuartzJobVo vo, BindingResult result) {
        //参数校验
        if (result.hasErrors()) {
            String validationMsg = getValidationMsg(result);
            return WebResult.error(validationMsg);
        }
        if (StringUtils.isBlank(vo.getGroupName())){
            vo.setGroupName(Constants.JobGroupName.DEFAULT);
        }
        //todo cron校验
        return scheduleService.addJob(vo);
    }

    /**
     * 编辑任务
     * 暂时仅支持编辑任务 cron
     * @param vo
     * @param result
     * @return
     */
    @PutMapping("/job/{jobName}")
//    public WebResult editJob(@PathVariable String jobName,@RequestBody @Validated QuartzJobVo vo, BindingResult result) {
    public WebResult editJob(@PathVariable String jobName,@RequestBody @Validated QuartzJobVo vo, BindingResult result) {
        //参数校验
        if (result.hasErrors()) {
            String validationMsg = getValidationMsg(result);
            return WebResult.error(validationMsg);
        }
        //todo cron校验
        if (StringUtils.isBlank(vo.getGroupName())){
            vo.setGroupName(Constants.JobGroupName.DEFAULT);
        }
        return scheduleService.modifyJobTime(jobName,vo.getGroupName(),vo.getCron());
    }

    /**
     * 任务列表
     * @return
     */
    @GetMapping("/job")
    public WebResult jobList(QuartzJobVo vo, Integer pageNum, Integer pageSize){
        List<QuartzJobVo> voList = scheduleService.jobList(vo);
        int total = voList.size();
        //模拟分页
        if (pageNum != null && pageSize != null) {
            int skip = (pageNum - 1) * pageSize;
            voList = voList.stream().skip(skip).limit(pageSize).collect(Collectors.toList());
        }
        return WebResult.ok("成功").put("rows",voList).put("total",total);
    }
    /**
     * 任务组名称
     * @return
     */
    @GetMapping("/groupName")
    public  List<String> groupNames() throws SchedulerException {
        return   scheduleService.groupNames();
    }

    /**
     * 删除任务
     * @param jobName
     * @return
     * @throws SchedulerException
     */
    @DeleteMapping("/job/{jobName}")
    public WebResult removeJob(@PathVariable String jobName) throws SchedulerException {
        return scheduleService.removeJob(jobName);
    }

    /**
     * 暂停任务
     * @param jobName
     * @param groupName
     * @return
     * @throws SchedulerException
     */
    @PostMapping("/pauseJob")
    public WebResult pauseJob(@RequestParam String jobName,@RequestParam(required = false) String groupName) throws SchedulerException {
        if (StringUtils.isNotBlank(groupName)) {
            return scheduleService.pauseJob(jobName, groupName);
        }
        return scheduleService.pauseJob(jobName);
    }

    /**
     * 恢复任务
     * @param jobName
     * @param groupName
     * @return
     * @throws SchedulerException
     */
    @PostMapping("/resumeJob")
    public WebResult resumeJob(@RequestParam String jobName,@RequestParam(required = false) String groupName) throws SchedulerException {
        if (StringUtils.isNotBlank(groupName)) {
            return scheduleService.resumeJob(jobName, groupName);
        }
        return scheduleService.resumeJob(jobName);
    }
    /**
     * 恢复任务
     * @param jobName
     * @return
     * @throws SchedulerException
     */
    @PostMapping("/triggerJob")
    public WebResult triggerJob( String jobName, String groupName) throws SchedulerException{
        return scheduleService.triggerJob(jobName);
    }


}
