package com.entrobus.credit.schedule.bean;

import org.quartz.Job;

public interface JobClassManager {
    Class <? extends Job> getJobClass(String jobName);
}
