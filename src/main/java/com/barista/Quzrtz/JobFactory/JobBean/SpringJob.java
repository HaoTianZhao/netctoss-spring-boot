package com.barista.Quzrtz.JobFactory.JobBean;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

/**
 * 交给由Spring管理的Job,由自定义的JobFactory提供给Scheduler
 *
 * @ClassName SpringJob
 * @Author zhaoth
 * @Date 2019/9/10 14:27
 * @Version 1.0
 */
@Component
@Lazy
public class SpringJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("执行......" + this);
    }

    @PreDestroy
    public void destroy() {
        System.out.println("destroy......");
    }
}
