package com.entrobus.credit.schedule.service;

import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.vo.schedule.QuartzJobVo;
import org.quartz.Job;

public interface ScheduleService {
//    boolean add(String jobName, String cron, Class className);

    boolean removeJob(String jobName);

    boolean removeJob(String jobName, String groupName);

    void addJob(String jobName, String groupName, Class<? extends Job> jobClass, String cron);

    void addJob(String jobName, Class<? extends Job> jobClass, String cron);

    WebResult addJob(QuartzJobVo vo);

    void registry(String jobName, String groupName, Class<? extends Job> jobClass, String cron);

    void pauseJob(String jobName, String jobGroupName);

    WebResult modifyJobTime(String jobName, String groupName, String cron);

    void clear();
}
