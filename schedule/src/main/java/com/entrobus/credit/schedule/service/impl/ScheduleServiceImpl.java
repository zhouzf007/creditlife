package com.entrobus.credit.schedule.service.impl;

import com.entrobus.credit.common.Constants;
import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.schedule.bean.JobClassManager;
import com.entrobus.credit.schedule.service.ScheduleService;
import com.entrobus.credit.vo.schedule.QuartzJobVo;
import org.apache.commons.lang3.StringUtils;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Transactional
@Service
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    private Scheduler scheduler;
    @Autowired
    private JobClassManager jobClassManager;
    private final static Logger logger = LoggerFactory.getLogger(ScheduleServiceImpl.class);

//    private static final String DEFAULT_GROUP_NAME = "creditlife_default";
    private static final String TRIGGER_SUFFIX = "_trigger";

    @Override
    public List<String > groupNames() throws SchedulerException {
        return scheduler.getJobGroupNames();
    }
    @Override
    public List<QuartzJobVo>  jobList(){
        List<QuartzJobVo> voList = new ArrayList<>();
        try {
            GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
//            matcher = GroupMatcher.jobGroupEquals();
            Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
            for (JobKey jobKey : jobKeys) {
                QuartzJobVo vo = new QuartzJobVo();
                voList.add(vo);

                vo.setGroupName(jobKey.getGroup());
                vo.setJobName(jobKey.getName());

                List<? extends Trigger> triggersOfJob = scheduler.getTriggersOfJob(jobKey);
                if (triggersOfJob != null && triggersOfJob.size() > 0){
                    CronTrigger trigger = (CronTrigger) triggersOfJob.get(0);
                    vo.setCron(trigger.getCronExpression());
                    Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                    vo.setStateName(triggerStateName(triggerState));
                }
                JobDetail jobDetail = scheduler.getJobDetail(jobKey);
                vo.setDescription(jobDetail.getDescription());
                vo.setParam((String) jobDetail.getJobDataMap().get(Constants.JobDataKey.CUSTOM));

            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return voList;
    }
    private String triggerStateName(Trigger.TriggerState state){
        switch (state){
            case NONE:
                return "不存在";
            case ERROR:
                return "异常";
            case PAUSED:
                return "暂停";
            case BLOCKED:
                return "阻塞";
            case NORMAL:
                return "正常";
            case COMPLETE:
                return "完成";

        }
        return "";
    }

    @Override
    public WebResult removeJob(String jobName) throws SchedulerException {
        return removeJob(jobName, Constants.JobGroupName.DEFAULT);
    }
    @Override
    public WebResult removeJob(String jobName, String groupName) throws SchedulerException {
        if(groupName == null) groupName = Constants.JobGroupName.DEFAULT;
        JobKey jobKey = JobKey.jobKey(jobName,groupName);
        if (scheduler.checkExists(jobKey)) {
            List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
            if (triggers != null && triggers.size() > 0) {
                for (Trigger trigger : triggers) {
                    scheduler.pauseTrigger(trigger.getKey());
                    scheduler.unscheduleJob(trigger.getKey());
                }
            }
            scheduler.deleteJob(jobKey);
        }else {
            return WebResult.error("任务不存在");
        }
        return WebResult.ok("操作成功");
    }
    @Override
    public WebResult addJob(QuartzJobVo vo) {
        JobKey jobKey = JobKey.jobKey(vo.getJobName(),vo.getGroupName());
        Class<? extends Job> jobClass = jobClassManager.getJobClass(vo.getJobName());
        if (jobClass == null){
            String msg = String.format("找不到%s对应的Java类，请确认jobName和groupName是否正确", vo.getJobName());
            return WebResult.error(msg);
        }
        try {
            if (scheduler.checkExists(jobKey)){
                return WebResult.error("任务已存在，请勿重复添加");
            }
            // 任务名，任务组，任务执行类
            JobBuilder jobBuilder = JobBuilder.newJob(jobClass).withIdentity(vo.getJobName(), vo.getGroupName());
            if (StringUtils.isNotBlank(vo.getDescription())){
                jobBuilder.withDescription(vo.getDescription());
            }
            jobBuilder.storeDurably(true);//持久化
            jobBuilder.requestRecovery(true);//重写执行失败的任务,default=false
            if (StringUtils.isNotBlank(vo.getParam())){
                jobBuilder.usingJobData(Constants.JobDataKey.CUSTOM,vo.getParam());
            }

            JobDetail jobDetail= jobBuilder.build();

            // 触发器
            TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
            // 触发器名,触发器组
            String triggerName = getTriggerName(vo.getJobName());
            triggerBuilder.withIdentity(triggerName, vo.getGroupName());
            triggerBuilder.startNow();
            // 触发器时间设定
            triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(vo.getCron()));
            // 创建Trigger对象
            CronTrigger trigger = (CronTrigger) triggerBuilder.build();

            // 调度容器设置JobDetail和Trigger
            scheduler.scheduleJob(jobDetail, trigger);

            // 启动
            if (!scheduler.isStarted()) {
                scheduler.start();
            }
            return WebResult.ok("添加成功！");
        } catch (Exception e) {
            logger.error("schedule添加任务失败!",e);
            return WebResult.error("操作失败");
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
    public WebResult modifyJobTime(String jobName,String jobGroupName, String triggerName, String triggerGroupName, String cron) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);

            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            if (trigger == null) {
                return WebResult.error("任务不存在");
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
            return WebResult.ok("操作成功！");
        } catch (Exception e) {
            logger.error("schedule修改任务时间失败！",e);
            return WebResult.error("操作失败！");
        }
    }
    /**
     * @Description: 暂停任务
     *@param jobName
     * @param jobGroupName
     */
    @Override
    public WebResult pauseJob(String jobName, String jobGroupName) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
        List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
        if (scheduler.isStarted()) {
            scheduler.pauseJob(jobKey);
            if (triggers != null && triggers.size() > 0) {
                scheduler.pauseTrigger(triggers.get(0).getKey());
            }
        }
        return WebResult.ok("操作成功");
    }
    /**
     * @Description: 恢复任务
     *@param jobName
     * @param jobGroupName
     */
    @Override
    public WebResult resumeJob(String jobName, String jobGroupName) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
        List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
        if (!scheduler.isStarted()) {
            scheduler.start();
        }
        scheduler.resumeJob(jobKey);
        if (triggers != null && triggers.size() > 0) {
            scheduler.resumeTrigger(triggers.get(0).getKey());
        }
        return WebResult.ok("操作成功");
    }
    /**
     * @Description: 修改一个任务
     *@param jobName
     * @param groupName
     * @param cron   时间设置，参考quartz说明文档
     */
    @Override
    public WebResult modifyJobTime(String jobName, String groupName, String cron) {
        String triggerName = getTriggerName(jobName);
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, groupName);
            if (!scheduler.checkExists(triggerKey)){
                return WebResult.error("任务不存在");
            }
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            if (trigger == null) {
                return WebResult.error("任务不存在");
            }

            String oldTime = trigger.getCronExpression();
            if (!oldTime.equalsIgnoreCase(cron)) {
                /** 方式一 ：调用 rescheduleJob 开始 */
                // 触发器
                TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
                // 触发器名,触发器组
                triggerBuilder.withIdentity(triggerName, groupName);
                triggerBuilder.startNow();
                // 触发器时间设定
                triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cron));
                // 创建Trigger对象
                trigger = (CronTrigger) triggerBuilder.build();
                // 方式一 ：修改一个任务的触发时间
                scheduler.rescheduleJob(triggerKey, trigger);
            }
            return WebResult.error("操作成功");
        } catch (Exception e) {
            logger.error("schedule修改任务时间失败！",e);
            return WebResult.error("操作失败！");
        }
//       return modifyJobTime(jobName,groupName,jobName+TRIGGER_SUFFIX,groupName,cron);
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
