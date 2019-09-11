package com.barista.Quzrtz;

import com.barista.Quzrtz.JobFactory.MyJobFactory;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 提供给外部调用的,操作scheduler的统一接口
 *
 * @ClassName SchedulerManager
 * @Author zhaoth
 * @Date 2019/9/10 17:22
 * @Version 1.0
 */
@Component
@Lazy
public class SchedulerManager {
    private Logger logger = LoggerFactory.getLogger(SchedulerManager.class);

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MyJobFactory myJobFactory;

    public Scheduler getScheduler(String schedulerName) throws SchedulerException {
        StdSchedulerFactory stdFactory = new StdSchedulerFactory();
        Scheduler scheduler = stdFactory.getScheduler(schedulerName);
        scheduler.getContext().put("applicationContext", webApplicationContext);
        return scheduler;
    }

    public Scheduler getScheduler() throws SchedulerException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.getContext().put("applicationContext", webApplicationContext);
        return scheduler;
    }

    public void set(Scheduler scheduler) throws SchedulerException {
        scheduler.setJobFactory(myJobFactory);
    }

    public Date scheduleJob(Scheduler scheduler, JobDetail jobDetail, Trigger trigger) throws SchedulerException {
        return scheduler.scheduleJob(jobDetail, trigger);
    }

    public Date scheduleJob(Scheduler scheduler, Trigger trigger) throws SchedulerException {
        return scheduler.scheduleJob(trigger);
    }

    public void scheduleJob(Scheduler scheduler, JobDetail jobDetail, Set<? extends Trigger> triggersForJob, boolean replace)
            throws SchedulerException {
        scheduler.scheduleJob(jobDetail, triggersForJob, replace);
    }

    public void scheduleJobs(Scheduler scheduler, Map<JobDetail, Set<? extends Trigger>> triggersAndJobs, boolean replace)
            throws SchedulerException {
        scheduler.scheduleJobs(triggersAndJobs, replace);
    }

    public void unScheduleJob(Scheduler scheduler, TriggerKey triggerKey) throws SchedulerException {
        scheduler.unscheduleJob(triggerKey);
    }

    public void unScheduleJobs(Scheduler scheduler, List<TriggerKey> triggerKeys) throws SchedulerException {
        scheduler.unscheduleJobs(triggerKeys);
    }

    public void start(Scheduler scheduler) throws SchedulerException {
        scheduler.start();
    }

    public void standBy(Scheduler scheduler) throws SchedulerException {
        scheduler.standby();
    }

    public void stop(Scheduler scheduler) throws SchedulerException {
        scheduler.shutdown();
    }

    public void stop(Scheduler scheduler, boolean waitForJobsToComplete) throws SchedulerException {
        scheduler.shutdown(waitForJobsToComplete);
    }

    public void rescheduleJob(Scheduler scheduler, TriggerKey oldTriggerKey, Trigger newTrigger) throws SchedulerException {
        scheduler.rescheduleJob(oldTriggerKey, newTrigger);
    }

}
