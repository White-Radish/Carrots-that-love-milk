package com.mmd.carrotorder.schedule;


import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.Trigger.TriggerState;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author hukai
 * @date 2020/4/20 18:19
 * 定时器处理类
 */
@Component
@Slf4j
public class QuartzManager {
    /**
     * 定时器状态，开启
     */
    private final static String OPEN = "1";

    /**
     * 注入调度器
     */
    @Autowired
    private Scheduler scheduler;


    /**
     * 添加一个定时任务，使用默认的任务组名，触发器名，触发器组名
     *
     * @param jobEntity
     */
    public void addJob(JobEntity jobEntity, String  jsonStr ) throws ClassNotFoundException {
        log.info("method:addJob  start");

        Class cls=Class.forName(jobEntity.getClassPath());
        if ((OPEN).equals(jobEntity.getWorkStatus())) {
            try {
                // 唯一主键
                String jobName = jobEntity.getName();
                String jobGroup = jobEntity.getGroupName();
                TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
                if (scheduler == null) {
                    log.info("scheduler is null");
                }
                CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
                if (null == cronTrigger) {
//                    JobDataMap jobDataMap=new JobDataMap();
//                    jobDataMap.put("jobEntity", JSON.toJSONString(jobEntity));
                    JobDetail jobDetail = JobBuilder.newJob(cls).withIdentity(jobName, jobGroup)
                            .usingJobData("dsd",jsonStr)
                            .build();
                    cronTrigger = TriggerBuilder.newTrigger().withIdentity(jobName, jobGroup)
                            .withSchedule(CronScheduleBuilder.cronSchedule(jobEntity.getCron())).build();
                    scheduler.scheduleJob(jobDetail, cronTrigger);
                    //启动一个定时器
                    if (!scheduler.isShutdown()) {
                        scheduler.start();
                    }
                } else {
                    cronTrigger = cronTrigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(CronScheduleBuilder.cronSchedule(jobEntity.getCron()))
                            .build();
                    scheduler.rescheduleJob(triggerKey, cronTrigger);
                }
            } catch (Exception e) {
                log.error("定时任务启动失败：" + e.getMessage());
            }
            log.info("method:addJob  end");
        }

    }

    /**
     * 暂停一个任务
     *
     * @param jobEntity
     */
    public void pauseJob(JobEntity jobEntity) {
        log.info("method:pauseJob  start");
        if ((OPEN).equals(jobEntity.getWorkStatus())) {
            try {
                // 唯一主键
                String jobName = jobEntity.getName();
                String jobGroupName = jobEntity.getGroupName();
                TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroupName);
                Trigger trigger = scheduler.getTrigger(triggerKey);
                JobKey jobKey = trigger.getJobKey();
                scheduler.pauseJob(jobKey);
            } catch (Exception e) {
                log.info("method:pauseJob  error", e);
                throw new RuntimeException(e);
            }
        }


    }

    /**
     * 继续任务
     *
     * @param jobEntity
     */
    public void resumeJob(JobEntity jobEntity) {
        log.info("method:resumeJob  start");
        if ((OPEN).equals(jobEntity.getWorkStatus())) {
            try {
                // 唯一主键
                String jobName = jobEntity.getName();
                String jobGroupName = jobEntity.getGroupName();
                TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroupName);
                Trigger trigger = scheduler.getTrigger(triggerKey);
                JobKey jobKey = trigger.getJobKey();
                scheduler.resumeJob(jobKey);
            } catch (Exception e) {
                log.info("method:resumeJob  error", e);
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 重启一个任务
     *
     * @param jobEntity
     */
    public void restartJob(JobEntity jobEntity) {
        log.info("method:restartJob  start");
        if ((OPEN).equals(jobEntity.getWorkStatus())) {
            try {
                // 唯一主键
                String jobName = jobEntity.getName();
                String jobGroupName = jobEntity.getGroupName();
                TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroupName);
                Trigger trigger = scheduler.getTrigger(triggerKey);
                scheduler.rescheduleJob(triggerKey, trigger);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            log.info("method:resOneJob  end");
        }
    }

    /**
     * @Description: 修改一个任务的触发时间
     */
    public void updateJobTime(JobEntity jobEntity, String time) {
        log.info("method:updateJobTime  start");
        if ((OPEN).equals(jobEntity.getWorkStatus())) {
            try {
                // 唯一主键
                String jobName = jobEntity.getName();
                String jobGroupName = jobEntity.getGroupName();
                TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroupName);
                CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
                jobEntity.setCron(time);
                CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(jobEntity.getCron());
                cronTrigger = cronTrigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(cronScheduleBuilder).build();
                scheduler.rescheduleJob(triggerKey, cronTrigger);
            } catch (Exception e) {
                log.error("method:updateJobTime  error", e);
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 移除定时任务
     *
     * @param jobEntity
     */
    public void removeJob(JobEntity jobEntity) {
        log.info("method: removeJob  start");
        if ((OPEN).equals(jobEntity.getWorkStatus())) {
            try {
                // 唯一主键
                String jobName = jobEntity.getName();
                String jobGroupName = jobEntity.getGroupName();
                TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroupName);
                Trigger trigger = scheduler.getTrigger(triggerKey);
                if (null == trigger) {
                    log.info("method:removeJob trigger is NULL ");
                    return;
                }
                JobKey jobKey = trigger.getJobKey();
                // 停止触发器
                scheduler.pauseTrigger(triggerKey);
                // 移除触发器
                scheduler.unscheduleJob(triggerKey);
                // 删除任务
                scheduler.deleteJob(jobKey);
            } catch (Exception e) {
                log.error("removeJob error ", e);
                throw new RuntimeException(e);
            }
            log.info("method:removeJob  end");
        }
    }

    /**
     * 获取定时任务触发器状态
     *
     * @param jobEntity
     * @return
     */
    public String getTriggerStatus(JobEntity jobEntity) {
        String state = "NONE";
        if ((OPEN).equals(jobEntity.getWorkStatus())) {
            try {
                // 唯一主键
                String jobName = jobEntity.getName();
                String jobGroupName = jobEntity.getGroupName();
                TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroupName);
                //trigger state
                TriggerState triggerState = scheduler.getTriggerState(triggerKey);
                state = triggerState.toString();
            } catch (Exception e) {
                log.error("getTriggerStatus error ", e);
                throw new RuntimeException(e);
            }
            return state;
        }
        return new String();
    }

    /**
     * 是否存在任务
     * 方法表述
     *
     * @param jobEntity
     * @return boolean
     */
    public boolean hasTrigger(JobEntity jobEntity) {
        boolean isHas = true;
        if ((OPEN).equals(jobEntity.getWorkStatus())) {
            try {
                // 唯一主键
                String jobName = jobEntity.getName();
                String jobGroupName = jobEntity.getGroupName();
                TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroupName);
                Trigger trigger = scheduler.getTrigger(triggerKey);
                if (null == trigger) {
                    isHas = false;
                }
            } catch (SchedulerException e) {
                isHas = false;
                log.error("method:hasTrigger error ");
            }
        }
        return isHas;
    }


    /**
     * 关闭定时任务
     */
    public void shutdownJobs() {
        try {
            if (!scheduler.isShutdown()) {
                scheduler.shutdown();
            }
        } catch (Exception e) {
            log.error("shutdownJobs error",e);
            throw new RuntimeException(e);
        }
    }

}

