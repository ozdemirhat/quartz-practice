package com.trendyol.quartzpractice.service;

import org.quartz.*;
import org.quartz.core.QuartzScheduler;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
public class SchedulerService {

    private Scheduler scheduler;

    @Autowired
    SchedulerFactoryBean schedulerFactoryBean;

    public SchedulerService(){}

    @PostConstruct
    void init() throws SchedulerException {
        scheduler = schedulerFactoryBean.getScheduler();
        //scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();
    }

    @PreDestroy
    void destroy() throws SchedulerException {
        scheduler.shutdown();
    }

    public void schedule(String identifier, Class<? extends Job> jobClass, String cronExpression ) throws SchedulerException {

        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule( cronExpression );

        JobDetail jobDetail = JobBuilder.newJob( jobClass ).withIdentity( identifier ).build();

        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity( identifier ).withSchedule( cronScheduleBuilder ).build();

        scheduler.scheduleJob( jobDetail, cronTrigger );
    }

    public void reschedule (String identifier, String cronExpression) throws SchedulerException {

        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule( cronExpression );

        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity( identifier ).withSchedule( cronScheduleBuilder ).build();

        scheduler.rescheduleJob( TriggerKey.triggerKey( identifier ), cronTrigger );
    }


}
