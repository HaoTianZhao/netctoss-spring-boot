package com.barista.controller;

import com.barista.Quzrtz.QuartzDemo;

import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 测试
 *
 * @ClassName TestController
 * @Author zhaoth
 * @Date 2019/9/9 15:10
 * @Version 1.0
 */
@Controller()
@RequestMapping("/test")
public class TestController {

    @Autowired
    private QuartzDemo quartzDemo;


    @RequestMapping("/quartz")
    public void quartz() throws JobExecutionException {
        quartzDemo.start();


    }
}
