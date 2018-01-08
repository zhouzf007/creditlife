package com.entrobus.credit.schedule.bean;

import com.entrobus.credit.common.util.ClassUtils;
import com.entrobus.credit.schedule.annotation.JobBean;
import org.apache.commons.lang3.StringUtils;
import org.quartz.Job;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
@Order(Ordered.LOWEST_PRECEDENCE)
public class JobClassManagerImpl implements JobClassManager,InitializingBean{
    @Value("${creditlife.schedule.jobDetail.basePackage}")
    private String basePackage;

//    private Map<JobKey,JobDetailMsg> classMap = new HashMap<>();
    private Map<String,Class <? extends Job>> classMap = new HashMap<>();

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
                    if (classMap.containsKey(jobName)){
                        String msg = String.format("jobBean重复，类%s和%s有相同的jobName", classMap.get(jobName).getClass().getName(),jobClass.getName());
                        throw new Exception(msg);
                    }
                    classMap.put(jobName,jobClass);
                }
            }
        }
    }

    @Override
    public Class <? extends Job> getJobClass(String jobName){
        return classMap.get(jobName);
    }



}
