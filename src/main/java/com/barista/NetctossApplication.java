package com.barista;

import com.barista.util.RedisUtil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import cn.yueshutong.springprojecttree.config.annotation.EnableProjectTree;


@SpringBootApplication
//@EnableAsync
//@EnableScheduling
@EnableCaching
@EnableTransactionManagement
//@EnableAspectJAutoProxy
//@EnableProjectTree(value = "execution(* com.barista..*.*(..))")
public class NetctossApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(NetctossApplication.class, args);
    }


}
