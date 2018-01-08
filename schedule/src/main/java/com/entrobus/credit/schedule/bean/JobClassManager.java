package com.entrobus.credit.schedule.bean;

import org.quartz.Job;
import org.quartz.JobKey;

public interface JobClassManager {

    Class <? extends Job> getJobClass(String jobName, String groupName);

    Class <? extends Job> getJobClass(JobKey jobKey);
}
