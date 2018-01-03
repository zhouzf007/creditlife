package com.entrobus.credit.schedule.service.impl;

import com.entrobus.credit.schedule.service.ScheduleService;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    private Scheduler scheduler;
    @Override
    public boolean add(String jobName,String cron,Class className){
        JobKey jobKey = new JobKey(jobName);
        try {
            if (scheduler.checkExists(jobKey)) return false;

            JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
            jobDetailFactoryBean.setJobClass(className);
            jobDetailFactoryBean.setName(jobName);
            jobDetailFactoryBean.setDurability(true);
            jobDetailFactoryBean.setRequestsRecovery(true);
            jobDetailFactoryBean.afterPropertiesSet();
//            CronTriggerImpl trigger = new CronTriggerImpl();
//            trigger.setCronExpression(new CronExpression(cron));
            String triggerName = jobName + "Trgger";

            CronTriggerFactoryBean triggerFactoryBean = new CronTriggerFactoryBean();
            JobDetail jobDetail = jobDetailFactoryBean.getObject();
            triggerFactoryBean.setJobDetail(jobDetail);
            triggerFactoryBean.setCronExpression(cron);
            triggerFactoryBean.setName(triggerName);
            triggerFactoryBean.afterPropertiesSet();
            scheduler.addJob(jobDetail,true);

            TriggerKey triggerKey = new TriggerKey(triggerName);
            CronTrigger trigger = triggerFactoryBean.getObject();
            if (scheduler.checkExists(triggerKey)){
                scheduler.rescheduleJob(triggerKey, trigger);
            }else {
                scheduler.scheduleJob(trigger);
            }

        } catch (SchedulerException e) {
            e.printStackTrace();
            return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    @Override
    public boolean del(String jobName){
        //todo
        JobKey jobKey = new JobKey(jobName);
        try {
            if (scheduler.checkExists(jobKey)){
                //http://blog.csdn.net/xlxxcc/article/details/52118102
                JobDetail jobDetail = scheduler.getJobDetail(jobKey);
                List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
                scheduler.pauseTrigger(triggers.get(0).getKey());
                scheduler.unscheduleJob(triggers.get(0).getKey());
                scheduler.deleteJob(jobKey);
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }


}
