package com.entrobus.credit.schedule.job;

import com.entrobus.credit.common.util.DateUtils;
import com.entrobus.credit.schedule.annotation.JobBean;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

/**
 * 这里的cron目前设置是 只在第一次启动时有效，如果经过其它途径修改，这里的cron再后续启动时不会覆盖原有cron
 * 后续可能会改为手动配置
 * @author laotou
 */
//@JobBean(jobName = "demoJobBean",groupName = "asda")
@JobBean
public class DemoJobBean extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //获取执行参数，可自定义配置
        JobDataMap jobDataMap = jobExecutionContext.getMergedJobDataMap();
        System.out.println("执行 DemoJobBean 时间："+ DateUtils.formatDate(new Date()));
    }
}
