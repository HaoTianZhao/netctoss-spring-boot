package com.barista;

import com.barista.service.AuthorityService;
import com.barista.service.schedule.ScheduleService;
import com.barista.util.MD5Util;
import com.barista.util.RedisUtil;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName Demo1
 * @Author zhaoth
 * @Date 2019/8/5 12:48
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Demo1 {
    private static Logger logger = LoggerFactory.getLogger(Demo1.class);

    @Test
    public void test2() {
        try {

            Class clazz = Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println(clazz.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        logger.trace("trace message");
        logger.debug("debug message");
        logger.info("info message");
        logger.warn("warn message");
        logger.error("error message");
        //        logger.fatal("fatal message");//log4j2才有这个级别
        System.out.println("Hello World!");
    }

    @Autowired
    AuthorityService authorityService;

    @Test
    public void 测试权限拦截器() {
        Set<String> set = authorityService.selectAllPermission("admin");
        boolean b = "http://localhost:8080/user/loginAll".matches("http://localhost:8080/user/login.*");
        System.out.println(b);

        System.out.println(set);
    }

    @Test
    public void Test4() {
        System.out.println(MD5Util.encode("admin"));
    }

    @Test
    public void match() {
        Integer a = 127;
        Integer b = 127;

        System.out.println(a == b);

        List<Integer> list = new ArrayList<>();

    }

    @Autowired
    ScheduleService scheduleService;

    @Test
    public void multi() throws InterruptedException {


        a("a", "b", "c", "d");
    }

    public void a(String... string) {
        List list = Arrays.asList(string);
    }


    //使用mybatis的批量处理
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Test
    public void batch() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH)) {
            //批量执行dao层SQL操作
            authorityService.selectAllPermission();
            authorityService.selectAllPermission();
            authorityService.selectAllPermission();
            authorityService.selectAllPermission();
            sqlSession.commit();
        }

    }

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void redisTest() {
        redisUtil.set("赵天昊", "哈哈哈哈哈哈哈哈哈哈");
        Map<Object, Object> hash1 = redisUtil.hmget("hash1");

    }

}
