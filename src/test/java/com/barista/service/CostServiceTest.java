package com.barista.service;

import com.alibaba.fastjson.JSON;
import com.barista.entity.Cost;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

/**
 * @ClassName CostServiceTest
 * @Author zhaoth
 * @Date 2019/8/28 17:05
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CostServiceTest {
    @Autowired
    private CostService costService;

    @Test
    public void selectCount() {
        int result = costService.selectCount();
        System.out.println(result);
    }

    @Test
    public void selectByExistField(){
        Cost cost = new Cost();
//        cost.setCostUnitCost(new BigDecimal("0.4"));
        System.out.println(JSON.toJSONString(costService.selectByExistField(null)));
    }

    @Test
    public void selectPaging() {
        System.out.println(JSON.toJSONString(costService.selectPaging(1, 10)));
    }

    @Test
    public void selectPagingOrder() {
        System.out.println(JSON.toJSONString(costService.selectPagingOrder(1, 10, "cost_base_duration desc")));
    }

//    @Test
//    public void insertCost() {
//        Cost cost = new Cost();
//        cost.setCostId(1001);
//        cost.setCostName("100元套餐测试");
//        cost.setCostBaseDuration(500);
//        cost.setCostBaseCost(new BigDecimal(100.0));
//        cost.setCostUnitCost(new BigDecimal(0.1));
//        cost.setCostDescr("测试描述信息");
//        cost.setCostType("2");
//
//        int result = costService.insertCost(cost);
//        System.out.println(result);
//    }

    @Test
    public void startUsing() {
        int result = costService.startUsing(1001);
        System.out.println(result);
    }

    @Test
    public void deleteCost() {
        int result = costService.deleteCost(1001);
        System.out.println(result);
    }

    @Test
    public void updateCost() {
        Cost cost = new Cost();
        cost.setCostId(1001);
        cost.setCostName("包月测试");
        cost.setCostBaseDuration(0);
        cost.setCostBaseCost(new BigDecimal(500.0));
        cost.setCostUnitCost(new BigDecimal(0));
        cost.setCostDescr("测试修改描述信息");
        cost.setCostType("1");

        int result = costService.updateCost(cost);
        System.out.println(result);
    }
}
