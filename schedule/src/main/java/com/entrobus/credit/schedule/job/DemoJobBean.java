package com.entrobus.credit.schedule.job;

import com.entrobus.credit.common.Constants;
import com.entrobus.credit.common.util.DateUtils;
import com.entrobus.credit.schedule.annotation.JobBean;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

/**
 * @author laotou
 */
//@JobBean//默认类名首字母小写
@JobBean("demoJobBean")
public class DemoJobBean extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //获取执行参数，可自定义配置
        JobDataMap jobDataMap = jobExecutionContext.getMergedJobDataMap();
        //后台添加的自定义参数
        String param = jobDataMap == null ? null : jobDataMap.getString(Constants.JOB_DATA_KEY.CUSTOM);

        System.out.println("执行 DemoJobBean 时间："+ DateUtils.formatDate(new Date()));
    }
}
