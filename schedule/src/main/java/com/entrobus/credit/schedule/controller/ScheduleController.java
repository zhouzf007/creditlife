package com.entrobus.credit.schedule.controller;

import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.schedule.bean.JobDetailProcessor;
import com.entrobus.credit.schedule.job.DemoJobBean;
import com.entrobus.credit.schedule.service.ScheduleService;
import org.quartz.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private JobDetailProcessor jobDetailProcessor;
    @PostMapping("/")
    public WebResult addJob(@RequestParam String jobName,@RequestParam String groupName, @RequestParam String cron){
        Class<? extends Job> jobClass = jobDetailProcessor.getJobClass(jobName,groupName);
         scheduleService.addJob(jobName,groupName, jobClass,cron);
        return WebResult.ok("成功");
    }
    @GetMapping("/")
    public WebResult get(){

        return WebResult.ok("成功");
    }
    @DeleteMapping("/")
    public WebResult clear(){
        scheduleService.clear();
        return WebResult.ok("成功");
    }
}
