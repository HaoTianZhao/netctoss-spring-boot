package com.barista.service;

import com.alibaba.fastjson.JSON;
import com.barista.entity.Service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @ClassName ServiceServiceTest
 * @Author zhaoth
 * @Date 2019/8/31 21:14
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@ContextConfiguration({"classpath:application.xml"})
public class ServiceServiceTest {
    @Autowired
    private ServiceService serviceService;

    @Test
    public void selectByExistField() {
        Service service = new Service();
        service.setCostId(4);
        List<Service> list = serviceService.selectByExistField(service);
        System.out.println(JSON.toJSONString(list));
    }

    @Test
    public void selectCount() {
        System.out.println(serviceService.selectCount());
    }


    @Test
    public void selectPaging() {
        System.out.println(JSON.toJSONString(serviceService.selectPaging(2, 5)));
    }

    @Test
    public void selectCountFilter() {
        System.out.println(serviceService.selectCountFilter("ang", "2", null, "0"));
        System.out.println(serviceService.selectCountFilter(null, null, "9930", null));

    }

    @Test
    public void selectPagingFilter() {
        System.out.println(JSON.toJSONString(serviceService.selectPagingFilter(1, 2
                , "ang", "2", null, "0")));
        System.out.println(JSON.toJSONString(serviceService.selectPagingFilter(1, 5
                , null, null, "9930", null)));
    }

    private Service service = new Service();

    {
        service.setServiceId(2013);
        service.setAccountId(2000);
        service.setUnixHost("192.168.1.120");
        service.setOsUsername("test");
        service.setLoginPasswd("barista");
        service.setCostId(1001);
    }

    @Test
    public void insertSelective() {
        System.out.println(serviceService.insertService(service));
    }

    @Test
    public void startUsing() {
        System.out.println(serviceService.startUsing(2013));
    }

    @Test
    public void pauseUsing() {
        System.out.println(serviceService.pauseUsing(2013));
    }

    @Test
    public void deleteByPrimaryKey() {
        System.out.println(serviceService.deleteService(2013));
    }

    @Test
    public void updateByPrimaryKeySelective() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2010, 1, 1, 12, 10, 35);
        service.setServiceCloseDate(calendar.getTime());
        System.out.println(serviceService.updateService(service));
    }

    @Test
    public void updateAllById() {
        List<Service> list = new ArrayList<>();
        list.add(service);
        list.add(service);
        list.add(service);
        System.out.println(serviceService.updateAllById(list));
    }

}
