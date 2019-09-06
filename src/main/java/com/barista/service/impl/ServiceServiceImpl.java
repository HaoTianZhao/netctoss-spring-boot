package com.barista.service.impl;

import com.barista.dao.AccountMapper;
import com.barista.dao.ServiceMapper;
import com.barista.entity.Account;
import com.barista.service.ServiceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Objects;

/**
 * @ClassName ServiceServiceImpl
 * @Author zhaoth
 * @Date 2019/8/30 19:46
 * @Version 1.0
 */
@Service("service")
public class ServiceServiceImpl implements ServiceService {
    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private ServiceMapper serviceMapper;

    @Override
    public List<com.barista.entity.Service> selectByExistField(com.barista.entity.Service service) {
        return serviceMapper.selectByExistField(service);
    }

    @Override
    public int selectCount() {
        return serviceMapper.selectCount();
    }

    @Override
    public List<com.barista.entity.Service> selectPaging(Integer currentPage, Integer pageSize) {
        return serviceMapper.selectPaging((currentPage - 1) * pageSize, pageSize);
    }

    @Override
    public int selectCountFilter(String osUsername, String unixHost, String accountIdcardNo, String serviceStatus) {
        return serviceMapper.selectCountFilter(osUsername, unixHost, accountIdcardNo, serviceStatus);
    }

    @Override
    public List<com.barista.entity.Service> selectPagingFilter(Integer currentPage, Integer pageSize
            , String osUsername, String unixHost, String accountIdcardNo, String serviceStatus) {
        return serviceMapper.selectPagingFilter((currentPage - 1) * pageSize, pageSize
                , osUsername, unixHost, accountIdcardNo, serviceStatus);
    }

    @Override
    public int insertService(com.barista.entity.Service service) {
        return serviceMapper.insertSelective(service);
    }

    @Override
    public int startUsing(Integer serviceId) {
        com.barista.entity.Service service = serviceMapper.selectByPrimaryKey(serviceId);
        Account account = accountMapper.selectByPrimaryKey(service.getAccountId());
        if (!Objects.equals(account.getAccountStatus(), "0")) {
            return -1;
        }
        return serviceMapper.startUsing(serviceId);
    }

    @Override
    public int pauseUsing(Integer serviceId) {
        return serviceMapper.pauseUsing(serviceId);
    }

    @Override
    public int deleteService(Integer serviceId) {
        return serviceMapper.deleteByPrimaryKey(serviceId);
    }

    @Override
    public int updateAllById(List<com.barista.entity.Service> serviceList) {
        return serviceMapper.updateAllById(serviceList);
    }

    @Override
    public int updateService(com.barista.entity.Service service) {
        return serviceMapper.updateByPrimaryKeySelective(service);
    }
}
