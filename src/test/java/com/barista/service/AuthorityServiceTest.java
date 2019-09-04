package com.barista.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

/**
 * @ClassName AuthorityServiceTest
 * @Author zhaoth
 * @Date 2019/8/22 15:44
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthorityServiceTest {
    @Autowired
    private AuthorityService authorityService;

    @Test
    public void 测试权限拦截selectAllPermission() {

        Set<String> set = authorityService.selectAllPermission("admin");
        Set<String> set2 = authorityService.selectAllPermission();
        boolean b = "http://localhost:8080/user/loginAll".matches("http://localhost:8080/user/login.*");
        System.out.println(b);

        System.out.println(set);
        System.out.println(set2);

    }

    @Test
    public void selectRoleIdByAdminId(){
        System.out.println(authorityService.selectPermissionGroupIds(1002));
    }

}
