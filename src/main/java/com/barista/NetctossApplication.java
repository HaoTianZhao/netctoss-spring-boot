package com.barista;

import com.barista.util.RedisUtil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import cn.yueshutong.springprojecttree.config.annotation.EnableProjectTree;


@SpringBootApplication
@EnableAsync
@EnableScheduling
@EnableCaching
@EnableTransactionManagement
@EnableProjectTree(value = "execution(* com.barista..*.*(..))")
public class NetctossApplication {

    public static void main(String[] args) {
        SpringApplication.run(NetctossApplication.class, args);
    }


}
