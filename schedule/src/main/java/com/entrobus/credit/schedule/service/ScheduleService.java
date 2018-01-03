package com.entrobus.credit.schedule.service;

public interface ScheduleService {
    boolean add(String jobName, String cron, Class className);

    boolean del(String jobName);
}
