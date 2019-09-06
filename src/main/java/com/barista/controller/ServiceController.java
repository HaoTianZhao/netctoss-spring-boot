package com.barista.controller;

import com.barista.VO.ServiceManageVO;
import com.barista.entity.Account;
import com.barista.entity.Cost;
import com.barista.entity.Service;
import com.barista.entity.ServiceUpdate;
import com.barista.result.Result;
import com.barista.result.ResultCode;
import com.barista.service.AccountService;
import com.barista.service.CostService;
import com.barista.service.ServiceService;
import com.barista.service.impl.ServiceUpdateServiceImpl;
import com.barista.service.schedule.ScheduleService;
import com.barista.util.MD5Util;
import com.barista.util.ValueUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * 业务账号管理
 *
 * @ClassName ServiceController
 * @Author zhaoth
 * @Date 2019/8/30 19:44
 * @Version 1.0
 */
@RestController
@RequestMapping("/service")
public class ServiceController {
    private Logger logger = LoggerFactory.getLogger(ScheduleService.class);

    @Autowired
    private ServiceUpdateServiceImpl serviceupdateService;
    @Autowired
    private ServiceService serviceService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private CostService costService;


    @RequestMapping("/selectById")
    public Result getServiceInfo(Integer serviceId) {
        if (ValueUtil.haveNullOrBlack(serviceId)) {
            return Result.fail(ResultCode.ILLEGAL_PARAM);
        }
        Service item = new Service();
        item.setServiceId(serviceId);
        Service service = serviceService.selectByExistField(item).get(0);
        Account account = accountService.selectById(service.getAccountId());
        Cost id = new Cost();
        id.setCostId(service.getCostId());
        Cost cost = costService.selectByExistField(id).get(0);
        List<Cost> allCost = costService.selectByExistField(null);

        ServiceManageVO vo = new ServiceManageVO(1, Arrays.asList(service), Arrays.asList(account), Arrays.asList(cost), allCost);
        return Result.success(vo);
    }

    @RequestMapping("/getPageInfo")
    public Result getPageInfo(Integer currentPage, Integer pageSize) {
        if (ValueUtil.haveNullOrBlack(currentPage, pageSize)) {
            return Result.fail(ResultCode.ILLEGAL_PARAM);
        }

        List<Service> serviceList = serviceService.selectPaging(currentPage, pageSize);
        List<Account> accountList = new ArrayList<>(serviceList.size());
        int count = serviceService.selectCount();
        List<Cost> costList = new ArrayList<>(serviceList.size());
        for (Service service : serviceList) {
            accountList.add(accountService.selectById(service.getAccountId()));
            Cost id = new Cost();
            id.setCostId(service.getCostId());
            costList.add(costService.selectByExistField(id).get(0));
        }
        List<Cost> allCost = costService.selectByExistField(null);
        ServiceManageVO vo = new ServiceManageVO(count, serviceList, accountList, costList, allCost);
        return Result.success(vo);
    }

    /**
     * 通过OS账号，服务器IP，身份证，账号状态做筛选
     */
    @RequestMapping("/getPageInfoSelected")
    public Result getPageInfoSelected(Integer currentPage, Integer pageSize, String osUsername
            , String unixHost, String accountIdcardNo, String serviceStatus) {
        if (ValueUtil.haveNullOrBlack(currentPage, pageSize)) {
            return Result.fail(ResultCode.ILLEGAL_PARAM);
        }
        if (ValueUtil.haveNullOrBlack(osUsername)) {
            osUsername = null;
        }
        if (ValueUtil.haveNullOrBlack(unixHost)) {
            unixHost = null;
        }
        if (ValueUtil.haveNullOrBlack(accountIdcardNo)) {
            accountIdcardNo = null;
        }
        if (Objects.equals(serviceStatus, "全部")) {
            serviceStatus = null;
        }

        int count = serviceService.selectCountFilter(osUsername, unixHost, accountIdcardNo, serviceStatus);
        List<Service> serviceList = serviceService.selectPagingFilter(currentPage, pageSize
                , osUsername, unixHost, accountIdcardNo, serviceStatus);
        List<Account> accountList = new ArrayList<>(serviceList.size());
        List<Cost> costList = new ArrayList<>(serviceList.size());
        for (Service service : serviceList) {
            accountList.add(accountService.selectById(service.getAccountId()));
            Cost id = new Cost();
            id.setCostId(service.getCostId());
            costList.add(costService.selectByExistField(id).get(0));
        }
        List<Cost> allCost = costService.selectByExistField(null);
        ServiceManageVO vo = new ServiceManageVO(count, serviceList, accountList, costList, allCost);
        return Result.success(vo);
    }


    @RequestMapping("/add")
    public Result addAccount(String accountIdcardNo, String costName, Service service) {
        if (ValueUtil.haveNullOrBlack(accountIdcardNo, costName, service)) {
            return Result.fail(ResultCode.ILLEGAL_PARAM);
        }

        Account account = accountService.selectByIdcardNo(accountIdcardNo);
        service.setAccountId(account.getAccountId());
        Cost nameCost = new Cost();
        nameCost.setCostName(costName);
        Cost cost = costService.selectByExistField(nameCost).get(0);
        service.setCostId(cost.getCostId());
        service.setLoginPasswd(MD5Util.encode(service.getLoginPasswd()));

        int result = serviceService.insertService(service);
        if (result > 0) {
            return Result.success("增加业务账号成功");
        } else {
            return Result.fail(ResultCode.SERVER_ERROR);
        }
    }


    @RequestMapping("/startUsing")
    public Result startUsing(Integer serviceId) {
        if (ValueUtil.haveNullOrBlack(serviceId)) {
            return Result.fail(ResultCode.ILLEGAL_PARAM);
        }

        int result = serviceService.startUsing(serviceId);
        if (result > 0) {
            return Result.success("启用业务账号成功");
        } else if (result == -1) {
            return Result.fail(ResultCode.ILLEGAL_STATUS);
        } else {
            return Result.fail(ResultCode.SERVER_ERROR);
        }
    }

    @RequestMapping("/pauseUsing")
    public Result pauseUsing(Integer serviceId) {
        if (ValueUtil.haveNullOrBlack(serviceId)) {
            return Result.fail(ResultCode.ILLEGAL_PARAM);
        }

        int result = serviceService.pauseUsing(serviceId);
        if (result > 0) {
            return Result.success("暂停业务账号成功");
        } else {
            return Result.fail(ResultCode.SERVER_ERROR);
        }
    }

    @RequestMapping("/delete")
    public Result deleteAccount(Integer serviceId) {
        if (ValueUtil.haveNullOrBlack(serviceId)) {
            return Result.fail(ResultCode.ILLEGAL_PARAM);
        }

        int result = serviceService.deleteService(serviceId);
        if (result > 0) {
            return Result.success("删除业务账号成功");
        } else {
            return Result.fail(ResultCode.SERVER_ERROR);
        }
    }

    @RequestMapping("/update")
    public Result updateAccount(Service service, Cost cost) {
        if (ValueUtil.haveNullOrBlack(service, cost)) {
            return Result.fail(ResultCode.ILLEGAL_PARAM);
        }

        Cost costItem = new Cost();
        costItem.setCostName(cost.getCostName());
        List<Cost> list = costService.selectByExistField(cost);
        if (list.size() == 0) {
            return Result.fail(ResultCode.ILLEGAL_PARAM);
        }
        Integer costId = list.get(0).getCostId();

        ServiceUpdate serviceUpdate = new ServiceUpdate();
        serviceUpdate.setServiceId(service.getServiceId());
        serviceUpdate.setUnixHost(service.getUnixHost());
        serviceUpdate.setOsUsername(service.getOsUsername());
        serviceUpdate.setCostId(costId);

        int result = serviceupdateService.saveUpdateInfo(serviceUpdate);
        if (result > 0) {
            return Result.success("更改业务账号成功，月底统一修改");
        } else {
            return Result.fail(ResultCode.SERVER_ERROR);
        }

    }

}
