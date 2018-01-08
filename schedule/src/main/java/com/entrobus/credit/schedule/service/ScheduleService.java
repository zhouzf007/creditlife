package com.entrobus.credit.schedule.service;

import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.vo.schedule.QuartzJobVo;
import org.quartz.Job;
import org.quartz.SchedulerException;

import java.util.List;

public interface ScheduleService {
//    boolean add(String jobName, String cron, Class className);

    List<QuartzJobVo> jobList();

    WebResult removeJob(String jobName) throws SchedulerException;

    WebResult removeJob(String jobName, String groupName) throws SchedulerException;

    void addJob(String jobName, String groupName, Class<? extends Job> jobClass, String cron);

    void addJob(String jobName, Class<? extends Job> jobClass, String cron);

    WebResult addJob(QuartzJobVo vo);

    void registry(String jobName, String groupName, Class<? extends Job> jobClass, String cron);

    void pauseJob(String jobName, String jobGroupName);

    void resumeJob(String jobName, String jobGroupName);

    WebResult modifyJobTime(String jobName, String groupName, String cron);

    void clear();
}
