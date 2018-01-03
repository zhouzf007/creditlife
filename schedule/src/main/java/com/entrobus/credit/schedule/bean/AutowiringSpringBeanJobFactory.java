package com.entrobus.credit.schedule.bean;

/**
 * Created by zhouzf on 2016/5/28.
 */
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

/**
 * Autowire Quartz Jobs with Spring context dependencies
 *
 * @seehttp
 *      ://stackoverflow.com/questions/6990767/inject-bean-reference-into-a-quartz
 *      -job-in-spring/15211030#15211030
 */

/**
 * Autowire Quartz Jobs with Spring context dependencies
 *
 * @seehttp
 *      ://stackoverflow.com/questions/6990767/inject-bean-reference-into-a-quartz
 *      -job-in-spring/15211030#15211030
 */
public final class AutowiringSpringBeanJobFactory extends SpringBeanJobFactory
        implements ApplicationContextAware {

    private transient AutowireCapableBeanFactory beanFactory;

    public void setApplicationContext(final ApplicationContext context) {
        beanFactory = context.getAutowireCapableBeanFactory();
    }

    @Override
    protected Object createJobInstance(final TriggerFiredBundle bundle)
            throws Exception {
        final Object job = super.createJobInstance(bundle);
        beanFactory.autowireBean(job);
        System.out.println("AutowiringSpringBeanJobFactory-------------------");
        return job;
    }
}
