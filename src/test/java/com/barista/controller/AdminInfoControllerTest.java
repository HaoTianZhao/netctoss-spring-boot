package com.barista.controller;

import com.alibaba.fastjson.JSON;
import com.barista.entity.AdminInfo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @ClassName AdminInfoControllerTest
 * @Author zhaoth
 * @Date 2019/8/26 17:45
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminInfoControllerTest {
    @Autowired
    private AdminInfoController adminInfoController;

    AdminInfo adminInfo;
    Integer[] roleIds;

    @Test
    public void getPageInfo() {
        System.out.println(JSON.toJSON(adminInfoController.getPageInfo(1, 10)));
    }

    @Test
    public void addAdmin() {
        adminInfoController.deleteAdmin(1003);
        roleIds = new Integer[]{1, 2, 3, 4, 5, 6};
        adminInfo = new AdminInfo();
        adminInfo.setAdminCode("qwe");
        adminInfo.setAdminPassword("admin");
        adminInfo.setAdminId(1003);
        adminInfo.setAdminTelephone("15626134357");
        adminInfo.setAdminEmail("597611084@qq.com");
        adminInfo.setAdminName("赵天昊");
        adminInfo.setAdminEnrolldate(null);

        adminInfoController.addAdmin(adminInfo, roleIds);
    }

    @Test
    public void deleteAdmin() {
        addAdmin();
        adminInfoController.deleteAdmin(adminInfo.getAdminId());
    }

    @Test
    public void updateAdmin() {
        roleIds = new Integer[]{1};
        adminInfo = new AdminInfo();
        adminInfo.setAdminId(1003);
        adminInfoController.updateAdmin(adminInfo, roleIds);
    }
}
