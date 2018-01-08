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
    @PostMapping("/")
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
    @PutMapping("/")
    public WebResult editJob(@RequestBody @Validated QuartzJobVo vo, BindingResult result) {
        //参数校验
        if (result.hasErrors()) {
            String validationMsg = getValidationMsg(result);
            return WebResult.error(validationMsg);
        }
        //todo cron校验
        if (StringUtils.isBlank(vo.getGroupName())){
            vo.setGroupName(Constants.JobGroupName.DEFAULT);
        }
        return scheduleService.modifyJobTime(vo.getJobName(),vo.getGroupName(),vo.getCron());
    }

    /**
     * 任务列表
     * @return
     */
    @GetMapping("/")
    public WebResult get(){
        List<QuartzJobVo> voList = scheduleService.jobList();
        return WebResult.ok("成功").put("list",voList);
    }
    /**
     * 任务列表
     * @return
     */
    @GetMapping("/groupName")
    public  List<String> groupNames() throws SchedulerException {
        return   scheduleService.groupNames();
    }

    /**
     * 删除任务
     * @param jobName
     * @param groupName
     * @return
     * @throws SchedulerException
     */
    @DeleteMapping("/")
    public WebResult removeJob(@RequestParam String jobName,@RequestParam String groupName) throws SchedulerException {
        return scheduleService.removeJob(jobName,groupName);
    }

    /**
     * 暂停任务
     * @param jobName
     * @param groupName
     * @return
     * @throws SchedulerException
     */
    @PostMapping("/pauseJob")
    public WebResult pauseJob(@RequestParam String jobName,@RequestParam String groupName) throws SchedulerException {
        return scheduleService.pauseJob(jobName,groupName);
    }

    /**
     * 恢复任务
     * @param jobName
     * @param groupName
     * @return
     * @throws SchedulerException
     */
    @PostMapping("/resumeJob")
    public WebResult resumeJob(@RequestParam String jobName,@RequestParam String groupName) throws SchedulerException {
        return scheduleService.resumeJob(jobName,groupName);
    }



}
