package com.barista.Quzrtz.job;

import com.barista.dao.AdminInfoMapper;
import com.barista.service.AdminService;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.SchedulerContext;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.web.context.WebApplicationContext;

/**
 * 由Quartz管理的Job,生命周期为任务执行-任务结束.
 * 每次执行时通过上下文注入所需SpringBean.
 *
 * @ClassName HelloJob
 * @Author zhaoth
 * @Date 2019/9/7 14:57
 * @Version 1.0
 */
//@PersistJobDataAfterExecution//有状态,每次结束时更新JobDataMap
//@DisallowConcurrentExecution//禁止并发,一般同时使用
public class HelloJob implements Job {
    private Logger logger = LoggerFactory.getLogger(HelloJob.class);

    //第(1)种获取传入的参数的方法:创建setter方法,Quartz的JobFactory在创建Job时会使用反射调用其赋值
    private String setKey;

    @Autowired
    private AdminService adminService;
    @Autowired
    private AdminInfoMapper adminInfoMapper;

    @Override
    public void execute(JobExecutionContext context) {
        autoWire(context);

        //获取JobDetail对象
        JobDetail detail = context.getJobDetail();
        String name = detail.getKey().getName();
        String group = detail.getKey().getGroup();
        JobDataMap jobDataMap = detail.getJobDataMap();
        String value = jobDataMap.getString("key");

        //获取Trigger对象
        Trigger trigger = context.getTrigger();
        TriggerKey triggerKey = trigger.getKey();
        JobDataMap triggerJobDataMap = trigger.getJobDataMap();//这是属于trigger的DataMap,和Job的Data不相同

        logger.info("jobStart:" + this + name + " " + group + "key:" + value + " ,setKey:" + setKey);
    }

    private void autoWire(JobExecutionContext context) {
        try {
            SchedulerContext schedulerContext = context.getScheduler().getContext();
            WebApplicationContext webAppContext = (WebApplicationContext) schedulerContext.get("applicationContext");

            // SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
            // 这是上面那个自动注入的代码,但是其中无法获得WebApplicationContext，所以抄下来用
            AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
            bpp.setBeanFactory(webAppContext.getAutowireCapableBeanFactory());
            bpp.processInjection(this);


        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    private void setProperty(JobExecutionContext context){

        //这是scheduler,JobDetail,Trigger中的JobDataMap按顺序putAll之后的Map
        JobDataMap mergedJobDataMap = context.getMergedJobDataMap();

        //这是Job的DataMap
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();

        //这是Trigger的DataMap
        JobDataMap triggerDataMap = context.getTrigger().getJobDataMap();

        //第(2)种获取传入的参数的方法
        setKey = mergedJobDataMap.getString("setKey");
    }

    public String getSetKey() {
        return setKey;
    }

    public void setSetKey(String setKey) {
        this.setKey = setKey;
    }

}