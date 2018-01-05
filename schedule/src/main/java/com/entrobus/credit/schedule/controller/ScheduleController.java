package com.entrobus.credit.schedule.controller;

import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.schedule.job.DemoJobDetail;
import com.entrobus.credit.schedule.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;
    @PostMapping("/")
    public WebResult addJob(@RequestParam String jobName, @RequestParam String cron){
         scheduleService.addJob(jobName,  DemoJobDetail.class,cron);
        return WebResult.ok("成功");
    }
    @GetMapping("/")
    public WebResult get(){

        return WebResult.ok("成功");
    }
    @DeleteMapping("/")
    public WebResult resumeAll(){
        scheduleService.resumeAll();
        return WebResult.ok("成功");
    }
}
