package com.entrobus.credit.schedule.controller;

import com.entrobus.credit.schedule.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;
}
