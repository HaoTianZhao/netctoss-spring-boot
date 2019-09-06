package com.barista.service;

import com.barista.entity.AdminInfo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

/**
 * @ClassName AdminServiceTest
 * @Author zhaoth
 * @Date 2019/8/22 15:44
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminServiceTest {
    @Autowired
    private AdminService adminService;

    @Test
    public void selectByAdminCode() {
        System.out.println(adminService.selectByAdminCode("admin"));
    }

    @Test
    public void checkPassword() {
        AdminInfo dbAdmin = adminService.selectByAdminCode("admin");
        System.out.println(adminService.checkPassword("admin", dbAdmin.getAdminPassword()));
    }

    @Test
    public void changePassword() {
        AdminInfo dbAdmin = adminService.selectByAdminCode("admin");
        System.out.println(adminService.changePassword(dbAdmin.getAdminCode(), "admin"));
    }

    @Test
    public void resetPasswords() {
        adminService.resetPasswords(Arrays.asList(1002, 1006), Arrays.asList("admin", "barista"));
    }

}
