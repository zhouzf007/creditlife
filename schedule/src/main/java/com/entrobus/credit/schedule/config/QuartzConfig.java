package com.entrobus.credit.schedule.config;

import com.entrobus.credit.schedule.bean.AutowiringSpringBeanJobFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

import javax.sql.DataSource;

@Configuration
public class QuartzConfig {
    @Autowired
    private DataSource dataSource;
//    //线程池
//    private ThreadPoolTaskExecutor executor;
    public static final String QUARTZ_PROPERTIES_NAME = "quartz.properties";

    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(100);
        executor.setQueueCapacity(500);
        return executor;
    }
    @Bean
    public SpringBeanJobFactory springBeanJobFactory(ApplicationContext applicationContext){
        AutowiringSpringBeanJobFactory beanJobFactory = new AutowiringSpringBeanJobFactory();
        beanJobFactory.setApplicationContext(applicationContext);
        return beanJobFactory;
    }
//    @Bean("quartzProperties")
//    public Properties quartzProperties() throws IOException {
//        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
//        propertiesFactoryBean.setLocation(new ClassPathResource(QUARTZ_PROPERTIES_NAME));
//        propertiesFactoryBean.afterPropertiesSet();
//        return propertiesFactoryBean.getObject();
//    }
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(SpringBeanJobFactory beanJobFactory,ThreadPoolTaskExecutor executor){
        SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();
        try {
//            factoryBean.setQuartzProperties(quartzProperties);
            factoryBean.setConfigLocation(new ClassPathResource(QUARTZ_PROPERTIES_NAME));
            factoryBean.setDataSource(dataSource);
            factoryBean.setJobFactory(beanJobFactory);
//            factoryBean.setTriggers(cronTrigger);

            // 任务名，任务组，任务执行类

//            factoryBean.setJobDetails(jobDetails);
            factoryBean.setOverwriteExistingJobs(true);
            factoryBean.setSchedulerName("quartzScheduler");
            factoryBean.setStartupDelay(5);
            factoryBean.setApplicationContextSchedulerContextKey("applicationContext");
            factoryBean.setTaskExecutor(executor);
        } catch (Exception e) {
//            Logger.error("加载 {} 配置文件失败.", QUARTZ_PROPERTIES_NAME, e);
            throw new RuntimeException("加载配置文件失败", e);
        }

        return factoryBean;
    }



}
