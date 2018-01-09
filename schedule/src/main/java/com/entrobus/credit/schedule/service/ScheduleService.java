package com.entrobus.credit.schedule.service;

import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.vo.schedule.QuartzJobVo;
import org.quartz.SchedulerException;

import java.util.List;

public interface ScheduleService {

    List<String > groupNames() throws SchedulerException;

    List<QuartzJobVo> jobList();

    WebResult removeJob(String jobName) throws SchedulerException;

    WebResult removeJob(String jobName, String groupName) throws SchedulerException;


    WebResult addJob(QuartzJobVo vo);


    WebResult pauseJob(String jobName, String jobGroupName) throws SchedulerException;

    WebResult resumeJob(String jobName, String jobGroupName) throws SchedulerException;

    WebResult modifyJobTime(String jobName, String groupName, String cron);

    void clear();
}
