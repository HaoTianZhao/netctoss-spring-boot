package com.barista.service.impl;

import com.barista.dao.AccountMapper;
import com.barista.dao.ServiceMapper;
import com.barista.entity.Account;
import com.barista.service.AccountService;
import com.barista.util.MD5Util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

/**
 * @ClassName AccountServiceimpl
 * @Author zhaoth
 * @Date 2019/8/29 14:57
 * @Version 1.0
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private ServiceMapper serviceMapper;

    @Override
    public Account selectById(Integer accountId) {
        return accountMapper.selectByPrimaryKey(accountId);
    }

    @Override
    public Account selectByIdcardNo(String accountRecommenderIdcardNo) {
        return accountMapper.selectByIdcardNo(accountRecommenderIdcardNo);
    }

    @Override
    public boolean checkPassword(String password, String dbPassword) {
        password = MD5Util.encode(password);
        if (StringUtils.equals(password, dbPassword)) {
            return true;
        }
        return false;
    }

    @Override
    public int selectCount() {
        return accountMapper.selectCount();
    }

    @Override
    public List<Account> selectPaging(Integer currentPage, Integer pageSize) {
        return accountMapper.selectPaging((currentPage - 1) * pageSize, pageSize);
    }

    @Override
    public int selectCountFilter(String accountIdcardNo, String accountRealName, String accountLoginName, String accountStatus) {
        return accountMapper.selectCountFilter(accountIdcardNo, accountRealName, accountLoginName, accountStatus);
    }

    @Override
    public List<Account> selectPagingFilter(Integer currentPage, Integer pageSize, String accountIdcardNo
            , String accountRealName, String accountLoginName, String accountStatus) {
        return accountMapper.selectPagingFilter((currentPage - 1) * pageSize, pageSize
                , accountIdcardNo, accountRealName, accountLoginName, accountStatus);
    }

    @Override
    public int insertAccount(Account account) {

            return accountMapper.insertSelective(account);

    }

    @Override
    public int startUsing(Integer accountId) {
        return accountMapper.startUsing(accountId);
    }

    @Override
    public int pauseUsing(Integer accountId) {
        serviceMapper.pauseAllByAccountId(accountId);
        return accountMapper.pauseUsing(accountId);
    }

    @Override
    public int deleteAccount(Integer accountId) {
        serviceMapper.deleteByAccountId(accountId);
        return accountMapper.deleteByPrimaryKey(accountId);
    }

    @Override
    public int updateAccount(Account account) {
            return accountMapper.updateByPrimaryKeySelective(account);
    }

}
