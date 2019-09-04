package com.barista.netctoss;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.JSON;
import com.barista.controller.AccountController;
import com.barista.dao.AccountMapper;
import com.barista.entity.Account;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NetctossApplicationTests {
    private Logger logger = LoggerFactory.getLogger(NetctossApplicationTests.class);

    @Autowired
    private DataSource dataSource;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private AccountController accountController;

    @Test
    public void contextLoads() {
        //        Account account = accountController.get();
        Account account2000 = accountMapper.selectByPrimaryKey(2000);
        System.out.println(account2000.getAccountId());
        //        System.out.println(dataSource.toString());
        //        System.out.println(accountMapper.selectCount());
    }

}
