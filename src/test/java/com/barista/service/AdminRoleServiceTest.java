package com.barista.service;

import com.alibaba.fastjson.JSON;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName AdminRoleService
 * @Author zhaoth
 * @Date 2019/8/21 20:20
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminRoleServiceTest {
    @Autowired
    private AdminRoleService adminRoleService;

    @Test
    public void selectRolesByAdminCode(){
        System.out.println(adminRoleService.selectRolesByAdminCode("admin"));
    }

    @Test
    public void selectCountFilter(){
        System.out.println(adminRoleService.selectCountFilter(null,"超级管理员"));
        System.out.println(adminRoleService.selectCountFilter(null,"业务账号管理员"));
        System.out.println(adminRoleService.selectCountFilter(null,"角色管理员"));
        System.out.println(adminRoleService.selectCountFilter(null,"asdowebgfisdl"));
        System.out.println(adminRoleService.selectCountFilter(null,""));
    }

    @Test
    public void selectPagingFilter(){
        System.out.println(JSON.toJSONString(adminRoleService.selectPagingFilter(1,10,null,"超级管理员")));
        System.out.println(JSON.toJSONString(adminRoleService.selectPagingFilter(1,10,null,"业务账号管理员")));
        System.out.println(JSON.toJSONString(adminRoleService.selectPagingFilter(1,10,null,"角色管理员")));
        System.out.println(JSON.toJSONString(adminRoleService.selectPagingFilter(1,10,null,"asdowebgfisdl")));
        System.out.println(JSON.toJSONString(adminRoleService.selectPagingFilter(1,10,null,"")));
    }


}
