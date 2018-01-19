package com.entrobus.credit.schedule.service;

import org.quartz.Job;

public interface JobClassManager {
    Class <? extends Job> getJobClass(String jobName);
}
