package com.entrobus.credit.schedule.service;

import org.quartz.Job;

public interface ScheduleService {
//    boolean add(String jobName, String cron, Class className);

    boolean removeJob(String jobName);

    boolean removeJob(String jobName, String groupName);

    void addJob(String jobName, String groupName, Class<? extends Job> jobClass, String cron);

    void addJob(String jobName, Class<? extends Job> jobClass, String cron);

    void registry(String jobName, String groupName, Class<? extends Job> jobClass, String cron);

    void pauseJob(String jobName, String jobGroupName);

    void modifyJobTime(String jobName, String groupName, String cron);

    void clear();
}
