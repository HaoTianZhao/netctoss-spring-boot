package com.barista.Quzrtz;

import com.barista.Quzrtz.job.HelloJob;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 一个使用Quartz的启动类Demo
 *
 * @ClassName TestMain
 * @Author zhaoth
 * @Date 2019/9/7 14:57
 * @Version 1.0
 */
@Service
@Lazy
public class QuartzService {
    public void start() {
        try {
            // Quartz 作业：定义一个JobDetail：为Job类设置属性
            JobDetail jobDetail = JobBuilder.newJob(HelloJob.class)
                    .withIdentity("job1", "group1")
                    .usingJobData("key", "job-value")
                    .usingJobData("setKey", "setValue")
                    .withDescription("这是一个测试任务")
                    .build();


            // 定义任务开始时间以及结束时间
            Date startDate = new Date();
            startDate.setTime(new Date().getTime() + 2000);
            Date endDate = new Date();
            endDate.setTime(startDate.getTime() + 4000);

            // SimpleTrigger 在一个指定时间段内执行一次作业任务 或者在指定时间间隔内多次执行作业任务
            Trigger simpleTrigger = TriggerBuilder.newTrigger()
                    .withIdentity("trigger1", "group1") // 定义name/group
                    // .startNow()//一旦加入scheduler，立即生效
                    // .startAt(null)
                    // .endAt(null)
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                            // .withMisfireHandlingInstructionNextWithRemainingCount()//8次
                            .withMisfireHandlingInstructionNextWithExistingCount()//8次，应该是11次
                            .withMisfireHandlingInstructionNowWithExistingCount()//11次
                            .withIntervalInSeconds(2) // 每隔一秒执行一次
                            .withRepeatCount(10) // 执行第1次后再执行10次
                            .repeatForever()) // 一直执行，奔腾到老不停歇
                    .usingJobData("key", "trigger-value")
                    .build();

            //CronTrigger 基于 cron 表达式，更常用
            CronTrigger cronTrigger = TriggerBuilder
                    .newTrigger()
                    // .startAt(null)
                    // .endAt(null)
                    .withIdentity("trigger2", "group2") // 定义name/group
                    .withSchedule(
                            // Cron表达式：[秒][分][时][日][月][周][年] (周日为1-周六为7，年可不写)
                            // *每 ?不关心 -至 #第 /递增 ,和 L最后 W最近工作日，[日专用]LW最后工作日，[周专用]#当月第几周
                            CronScheduleBuilder.cronSchedule("0/1 * * * * ? * ")
                                    .withMisfireHandlingInstructionIgnoreMisfires()
                                    .withMisfireHandlingInstructionFireAndProceed()
                    )
                    .build();


            // 创建scheduler
            SchedulerManager schedulerManager = new SchedulerManager();
            Scheduler scheduler = schedulerManager.getScheduler();

            // 调度类链接“工作”和“触发器”到一起,返回第一次执行时间
            schedulerManager.scheduleJob(scheduler, jobDetail, simpleTrigger);

            // 启动
            schedulerManager.start(scheduler);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
