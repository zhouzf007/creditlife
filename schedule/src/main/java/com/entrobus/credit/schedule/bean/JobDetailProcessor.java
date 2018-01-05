package com.entrobus.credit.schedule.bean;

import com.entrobus.credit.common.util.ClassUtils;
import com.entrobus.credit.schedule.annotation.JobBean;
import com.entrobus.credit.schedule.service.ScheduleService;
import org.apache.commons.lang3.StringUtils;
import org.quartz.Job;
import org.quartz.JobKey;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
@Order(Ordered.LOWEST_PRECEDENCE)
public class JobDetailProcessor implements InitializingBean,CommandLineRunner {
    @Value("${creditlife.schedule.jobDetail.basePackage}")
    private String basePackage;
    @Autowired
    private ScheduleService scheduleService;

    private Map<JobKey,JobDetailMsg> classMap = new HashMap<>();

    @Override
    public void afterPropertiesSet() throws Exception {
        Set<String> classNameSet = ClassUtils.getClassName(basePackage, true);
        if (classNameSet != null && classNameSet.size() > 0){
            for (String className : classNameSet) {
                Class<?> aClass = Class.forName(className);
                JobBean jobBean = aClass.getAnnotation(JobBean.class);
                if (jobBean != null && Job.class.isAssignableFrom(aClass)){
                    String jobName = jobBean.jobName();
                    Class <? extends Job> jobClass = (Class <? extends Job>) aClass;
                    if (StringUtils.isBlank(jobName)) {
                        jobName = className.substring(0, 1).toLowerCase() + className.substring(1);
                    }
                    String group = jobBean.groupName();
                    JobKey jobKey = JobKey.jobKey(jobName, group);

                    JobDetailMsg msg = new JobDetailMsg();
                    msg.setJobName(jobName);
                    msg.setGroupName(group);
                    msg.setCron(jobBean.cron());
                    msg.setJobClass(jobClass);

                    classMap.put(jobKey,msg);
                }
            }
        }
    }



    /**
     * spring boot 应用启动完后执行
     * Callback used to run the bean.
     *
     * @param args incoming main method arguments
     * @throws Exception on error
     */
    @Override
    public void run(String... args) throws Exception {
        classMap.forEach((jobKey, msg) -> {
            scheduleService.registry(msg.getJobName(),msg.getGroupName(),msg.getJobClass(),msg.getCron());
        });

    }

    static class JobDetailMsg{
        private Class <? extends Job> jobClass;
        private String cron;
        private String jobName;
        private String groupName;

        public Class<? extends Job> getJobClass() {
            return jobClass;
        }

        public void setJobClass(Class<? extends Job> jobClass) {
            this.jobClass = jobClass;
        }

        public String getCron() {
            return cron;
        }

        public void setCron(String cron) {
            this.cron = cron;
        }

        public String getJobName() {
            return jobName;
        }

        public void setJobName(String jobName) {
            this.jobName = jobName;
        }

        public String getGroupName() {
            return groupName;
        }

        public void setGroupName(String groupName) {
            this.groupName = groupName;
        }
    }
}
