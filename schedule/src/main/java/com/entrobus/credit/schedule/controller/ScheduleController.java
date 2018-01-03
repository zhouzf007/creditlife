package com.entrobus.credit.schedule.controller;

import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.schedule.job.DetailQuartzJobBean;
import com.entrobus.credit.schedule.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;
    @PostMapping("")
    public WebResult addJob(@RequestParam String jobName, @RequestParam String cron){
        boolean add = scheduleService.add(jobName, cron, DetailQuartzJobBean.class);
        return add ? WebResult.ok("成功") : WebResult.error("失败");
    }
    @GetMapping("")
    public WebResult get(){

        return WebResult.ok("成功");
    }
}
