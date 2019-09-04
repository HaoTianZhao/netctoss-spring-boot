package com.barista.service;

import com.alibaba.fastjson.JSON;
import com.barista.entity.Account;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName AccountServiceTest
 * @Author zhaoth
 * @Date 2019/8/29 17:38
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@ContextConfiguration({"classpath:application.xml"})
public class AccountServiceTest {
    @Autowired
    private AccountService accountService;

    @Test
    public void selectById(){
        System.out.println(JSON.toJSONString(accountService.selectById(2000)));
    }

    @Test
    public void selectByIdcardNo(){
        System.out.println(JSON.toJSONString(accountService.selectByIdcardNo("230202199706040619")));
    }

    @Test
    public void selectCount() {
        System.out.println(accountService.selectCount());
    }

    @Test
    public void selectPaging() {
        System.out.println(JSON.toJSONString(accountService.selectPaging(1, 10)));
    }

    @Test
    public void selectCountFilter() {
        System.out.println(JSON.toJSONString(accountService.selectCountFilter
                (null, "ji", "0", null)));
    }

    @Test
    public void selectPagingFilter() {
        System.out.println(JSON.toJSONString(accountService.selectPagingFilter
                (1, 10, null, null, null, "0")));
    }

    private Account account = new Account();

    {
        account.setAccountId(2000);
        account.setAccountRealName("赵天昊");
        account.setAccountLoginName("barista");
        account.setAccountLoginPasswd("password");
        account.setAccountIdcardNo("230202199706040619");
        account.setAccountTelephone("15626134357");
    }

    @Test
    public void insertAccount() {
        System.out.println(accountService.insertAccount(account));
    }

    @Test
    public void startUsing() {
        System.out.println(accountService.startUsing(account.getAccountId()));
    }

    @Test
    public void pauseUsing() {
        System.out.println(accountService.pauseUsing(account.getAccountId()));
    }

    @Test
    public void deleteAccount() {
        System.out.println(accountService.deleteAccount(account.getAccountId()));
    }

    @Test
    public void updateAccount() {
        System.out.println(accountService.updateAccount(account));
    }

}
