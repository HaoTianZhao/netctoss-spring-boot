package com.barista.Quzrtz.JobFactory;

import com.barista.Quzrtz.JobFactory.JobBean.SpringJob;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.simpl.PropertySettingJobFactory;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

/**
 * JobFactory.
 * 自定义Job.比如把Job交给Spring而不是Quartz管理
 *
 *
 * @ClassName MyJobFactory
 * @Author zhaoth
 * @Date 2019/9/9 16:35
 * @Version 1.0
 */
@Component
@Lazy
public class MyJobFactory extends PropertySettingJobFactory {
    @Autowired
    private SpringJob springJob;

    @Override
    public Job newJob(TriggerFiredBundle bundle, Scheduler scheduler) {

        JobDetail jobDetail = bundle.getJobDetail();
        Class<? extends Job> jobClass = jobDetail.getJobClass();

        Field[] fields = MyJobFactory.class.getDeclaredFields();
        Job job;
        for (Field field : fields) {
            if (jobClass == field.getType()) {
                try {
                    job = (Job) field.get(this);
                    return job;

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

}