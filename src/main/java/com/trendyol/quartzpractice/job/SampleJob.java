package com.trendyol.quartzpractice.job;

import com.trendyol.quartzpractice.service.SampleService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SampleJob extends QuartzJobBean{

    private static final Logger log = LoggerFactory.getLogger(SampleJob.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    SampleService sampleService;

    public SampleJob(){}


    @Override
    public void executeInternal(JobExecutionContext context){

        //sampleService.add(1,2);

        //System.out.println( " Time: " + dateFormat.format(new Date()));
        log.info("Sysout: " + sampleService.sysout() + " Add: " + sampleService.add(1,2).toString() + " Time: " + dateFormat.format(new Date()));
    }
}
