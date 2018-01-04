package com.entrobus.credit.schedule.job;

import com.entrobus.credit.schedule.annotation.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
@JobDetail(jobName = "aa",groupName = "asda",cron = "0/5 * * * * ?")
public class DemoJobDetail extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("执行 DemoJobDetail ");
    }
}
