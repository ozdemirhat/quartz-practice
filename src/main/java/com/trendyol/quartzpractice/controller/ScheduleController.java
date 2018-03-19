package com.trendyol.quartzpractice.controller;


import com.trendyol.quartzpractice.job.SampleJob;
import com.trendyol.quartzpractice.service.SchedulerService;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScheduleController {

    @Autowired
    SchedulerService schedulerService;

    @RequestMapping(value = "/schedule")
    public String schedule (@RequestParam(name="identifier", required = true) String identifier) throws SchedulerException {
        schedulerService.schedule(identifier ,SampleJob.class, "0/5 * * * * ?");
        return "ok";
    }

    @RequestMapping(value = "/reschedule")
    public String reschedule (@RequestParam(name="identifier", required = true) String identifier) throws SchedulerException {
        schedulerService.reschedule(identifier,"0/2 * * * * ?");
        return "ok";
    }

    @RequestMapping(value = "/unschedule")
    public String unschedule (@RequestParam(name="identifier", required = true) String identifier) throws SchedulerException {
        schedulerService.unschedule(identifier);
        return "ok";
    }

    @RequestMapping(value = "/pause")
    public String pauseTrigger (@RequestParam(name="identifier", required = true) String identifier) throws SchedulerException {
        schedulerService.pauseTrigger(identifier);
        return "ok";
    }

    @RequestMapping(value = "/resume")
    public String resumeTrigger (@RequestParam(name="identifier", required = true) String identifier) throws SchedulerException {
        schedulerService.resumeTrigger(identifier);
        return "ok";
    }


}
