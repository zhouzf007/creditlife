package com.entrobus.credit.schedule.controller;

import com.entrobus.credit.common.Constants;
import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.schedule.bean.JobClassManager;
import com.entrobus.credit.schedule.service.ScheduleService;
import com.entrobus.credit.vo.schedule.QuartzJobVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private JobClassManager jobDetailProcessor;

    private String getValidationMsg(BindingResult result) {
        StringBuilder sb = new StringBuilder();
        for (ObjectError error : result.getAllErrors()) {
            if (sb.length() > 0 ) sb.append(",");
            sb.append(error.getDefaultMessage());
        }
        return sb.toString();
    }
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
    @GetMapping("/")
    public WebResult get(){
        //todo
        return WebResult.ok("成功");
    }
    @DeleteMapping("/")
    public WebResult clear(){
        scheduleService.clear();
        return WebResult.ok("成功");
    }
}
