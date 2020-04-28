package com.mmd.carrotorder.schedule;

import lombok.extern.slf4j.Slf4j;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @author hukai
 * @date 2020/4/20 17:53
 */
@Component
@Slf4j
public class DynamicJob implements Job {

    @Autowired
    private QuartzManager quartzManager;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        CronTrigger trigger = (CronTrigger) jobExecutionContext.getTrigger();
        String corn = trigger.getCronExpression();
        String jobName = trigger.getKey().getName();
        String jobGroup = trigger.getKey().getGroup();
        trigger.getJobDataMap();
        System.out.println("corn:" + corn);
        System.out.println("jobName:" + jobName);
        System.out.println("jobGroup:" + jobGroup);



    }




}
