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

    private static final String DEFAULT_GROUP_NAME = "creditlife_default";
    private static final String TRIGGER_SUFFIX = "_trigger";




//    @Override
//    public boolean add(String jobName,String cron,Class className){
//        JobKey jobKey = new JobKey(jobName);
//        try {
//            if (scheduler.checkExists(jobKey)) return false;
//
//            JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
//            jobDetailFactoryBean.setJobClass(className);
//            jobDetailFactoryBean.setName(jobName);
//            jobDetailFactoryBean.setDurability(true);
//            jobDetailFactoryBean.setRequestsRecovery(true);
//            jobDetailFactoryBean.afterPropertiesSet();
////            CronTriggerImpl trigger = new CronTriggerImpl();
////            trigger.setCronExpression(new CronExpression(cron));
//            String triggerName = jobName + "Trgger";
//
//            CronTriggerFactoryBean triggerFactoryBean = new CronTriggerFactoryBean();
//            JobDetail jobDetail = jobDetailFactoryBean.getObject();
//            triggerFactoryBean.setJobDetail(jobDetail);
//            triggerFactoryBean.setCronExpression(cron);
//            triggerFactoryBean.setName(triggerName);
//            triggerFactoryBean.afterPropertiesSet();
//            scheduler.addJob(jobDetail,true);
//
//            TriggerKey triggerKey = new TriggerKey(triggerName);
//            CronTrigger trigger = triggerFactoryBean.getObject();
//            if (scheduler.checkExists(triggerKey)){
//                scheduler.rescheduleJob(triggerKey, trigger);
//            }else {
//                scheduler.scheduleJob(trigger);
//            }
//
//        } catch (SchedulerException e) {
//            e.printStackTrace();
//            return false;
//        }catch (Exception e){
//            e.printStackTrace();
//            return false;
//        }
//        return true;
//    }
    @Override
    public boolean removeJob(String jobName){
        return removeJob(jobName,DEFAULT_GROUP_NAME);
    }
    @Override
    public boolean removeJob(String jobName, String groupName){
        if(groupName == null) groupName = DEFAULT_GROUP_NAME;
        JobKey jobKey = JobKey.jobKey(jobName,groupName);
        try {
            if (scheduler.checkExists(jobKey)){
                List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
                if (triggers != null && triggers.size() > 0){
                    for (Trigger trigger : triggers) {
                        scheduler.pauseTrigger(trigger.getKey());
                        scheduler.unscheduleJob(trigger.getKey());
                    }
                }

                scheduler.deleteJob(jobKey);
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }
    @Override
    public void addJob(String jobName, String groupName, Class <? extends Job> jobClass, String cron) {
        addJob(jobName,groupName,getTriggerName(jobName),groupName,jobClass,null,cron);
    }
    @Override
    public void addJob(String jobName, Class<? extends Job> jobClass, String cron) {
        addJob(jobName,DEFAULT_GROUP_NAME,getTriggerName(jobName),DEFAULT_GROUP_NAME,jobClass,null,cron);
    }
    public void addJob(String jobName, String jobGroupName,
                       String triggerName, String triggerGroupName, Class <? extends Job> jobClass,JobDataMap jobData, String cron) {
        try {
            // 任务名，任务组，任务执行类
            JobBuilder jobBuilder = JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroupName);
            jobBuilder.storeDurably(true);//持久化
            jobBuilder.requestRecovery(true);//重写执行失败的任务,default=false
            if (jobData != null) jobBuilder.setJobData(jobData);
            JobDetail jobDetail= jobBuilder.build();

            // 触发器
            TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
            // 触发器名,触发器组
            triggerBuilder.withIdentity(triggerName, triggerGroupName);
            triggerBuilder.startNow();
            // 触发器时间设定
            triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cron));
            // 创建Trigger对象
            CronTrigger trigger = (CronTrigger) triggerBuilder.build();

            // 调度容器设置JobDetail和Trigger
            scheduler.scheduleJob(jobDetail, trigger);

            // 启动
            if (!scheduler.isShutdown()) {
                scheduler.start();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void registry(String jobName, String groupName, Class<? extends Job> jobClass, String cron) {
        JobKey jobKey = JobKey.jobKey(jobName,groupName);
        try {
            if (scheduler.checkExists(jobKey)){
//                modifyJobTime(jobName,groupName,cron);
            }else {
                addJob(jobName,groupName,jobClass,cron);
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description: 修改一个任务的触发时间
     *
     * @param jobName
     * @param jobGroupName
     * @param triggerName 触发器名
     * @param triggerGroupName 触发器组名
     * @param cron   时间设置，参考quartz说明文档
     */
    public void modifyJobTime(String jobName,String jobGroupName, String triggerName, String triggerGroupName, String cron) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            if (trigger == null) {
                return;
            }

            String oldTime = trigger.getCronExpression();
            if (!oldTime.equalsIgnoreCase(cron)) {
                /** 方式一 ：调用 rescheduleJob 开始 */
                // 触发器
                TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
                // 触发器名,触发器组
                triggerBuilder.withIdentity(triggerName, triggerGroupName);
                triggerBuilder.startNow();
                // 触发器时间设定
                triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cron));
                // 创建Trigger对象
                trigger = (CronTrigger) triggerBuilder.build();
                // 方式一 ：修改一个任务的触发时间
                scheduler.rescheduleJob(triggerKey, trigger);
                /** 方式一 ：调用 rescheduleJob 结束 */

                /** 方式二：先删除，然后在创建一个新的Job  */
                //JobDetail jobDetail = scheduler.getJobDetail(JobKey.jobKey(jobName, jobGroupName));
                //Class<? extends Job> jobClass = jobDetail.getJobClass();
                //removeJob(jobName, jobGroupName, triggerName, triggerGroupName);
                //addJob(jobName, jobGroupName, triggerName, triggerGroupName, jobClass, cron);
                /** 方式二 ：先删除，然后在创建一个新的Job */
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * @Description: 暂停任务
     *
     * @param jobName
     * @param jobGroupName
     */
    @Override
    public void pauseJob(String jobName, String jobGroupName) {
        try {
           JobKey jobKey = JobKey.jobKey(jobName,jobGroupName);
            List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
            if (scheduler.isStarted()) {
                scheduler.pauseJob(jobKey);
                if (triggers != null && triggers.size() > 0) {
                    scheduler.pauseTrigger(triggers.get(0).getKey());
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * @Description: 修改一个任务
     *
     * @param jobName
     * @param groupName
     * @param cron   时间设置，参考quartz说明文档
     */
    @Override
    public void modifyJobTime(String jobName, String groupName, String cron) {
       modifyJobTime(jobName,groupName,jobName+TRIGGER_SUFFIX,groupName,cron);
    }

    private String getTriggerName(String jobName) {
        return jobName + TRIGGER_SUFFIX;
    }


    /**
     * @Description:关闭所有定时任务
     */
    public void shutdownJobs() {
        try {
            if (!scheduler.isShutdown()) {
                scheduler.shutdown();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * @Description:关闭并删除所有定时任务
     */
    @Override
    public void clear() {
        try {
            if (!scheduler.isStarted()) {
                scheduler.start();
            }
            scheduler.clear();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Scheduler getScheduler() {
        return scheduler;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }
}
