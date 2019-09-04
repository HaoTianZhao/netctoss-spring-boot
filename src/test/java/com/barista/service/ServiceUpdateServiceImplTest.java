package com.barista.service;

import com.alibaba.fastjson.JSON;
import com.barista.entity.ServiceUpdate;
import com.barista.service.impl.ServiceUpdateServiceImpl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @ClassName ServiceUpdateServiceImplTest
 * @Author zhaoth
 * @Date 2019/9/1 17:39
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@ContextConfiguration({"classpath:application.xml"})
public class ServiceUpdateServiceImplTest {
    @Autowired
    private ServiceUpdateServiceImpl serviceUpdateService;

    @Test
    public void test() {

    }

    private ServiceUpdate serviceUpdate = new ServiceUpdate();

    {
        serviceUpdate.setServiceId(2013);
        serviceUpdate.setUnixHost("192.168.1.120");
        serviceUpdate.setOsUsername("barista");
        serviceUpdate.setCostId(1);
    }

    @Test
    public void saveUpdateInfo() {
        ServiceUpdate serviceUpdate = new ServiceUpdate();

        serviceUpdate.setServiceId(2001);
        serviceUpdate.setUnixHost("192.168.1.120");
        serviceUpdate.setOsUsername("barista");
        serviceUpdate.setCostId(1002);
        System.out.println(serviceUpdateService.saveUpdateInfo(serviceUpdate));

        serviceUpdate.setServiceId(2002);
        serviceUpdate.setUnixHost("192.168.1.120");
        serviceUpdate.setOsUsername("barista");
        serviceUpdate.setCostId(1002);
        System.out.println(serviceUpdateService.saveUpdateInfo(serviceUpdate));

        serviceUpdate.setServiceId(2013);
        serviceUpdate.setUnixHost("192.168.1.120");
        serviceUpdate.setOsUsername("barista");
        serviceUpdate.setCostId(1002);
        System.out.println(serviceUpdateService.saveUpdateInfo(serviceUpdate));

    }

    @Test
    public void getHistoryUpdateInfo() {
        System.out.println(JSON.toJSONString(serviceUpdateService.getHistoryUpdateInfo(0, 10, new Date())));
        System.out.println(JSON.toJSONString(serviceUpdateService.getHistoryUpdateInfo(0, 2, new Date())));
        System.out.println(JSON.toJSONString(serviceUpdateService.getHistoryUpdateInfo(1, 10, new Date())));
        System.out.println(JSON.toJSONString(serviceUpdateService.getHistoryUpdateInfo(0, 10, new Date(2019 - 1900, 8, 2))));
    }

    @Test
    public void deleteUsedUpdateInfo() {
        System.out.println(serviceUpdateService.deleteUsedUpdateInfo(10));
    }

    //定时任务通过观察SQL日志测试过了

}
