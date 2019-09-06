package com.barista.service;

import com.barista.entity.Account;

import java.util.List;

/**
 * @ClassName AccountService
 * @Author zhaoth
 * @Date 2019/8/29 14:57
 * @Version 1.0
 */
public interface AccountService {

    /**
     * 根据查询单个Account信息
     *
     * @param accountId 主键
     * @return com.barista.entity.Account
     * @author zhaoth
     */
    Account selectById(Integer accountId);

    /**
     * 根据身份证号查询单个Account信息
     * @param accountRecommenderIdcardNo 身份证号
     * @return com.barista.entity.Account
     * @author zhaoth
     */
    Account selectByIdcardNo(String accountRecommenderIdcardNo);


    /**
     * 检测传入的明文密码与数据库加密密码是否一致
     *
     * @param password   明文密码
     * @param dbPassword 数据库加密密码
     * @return boolean 密码是否相同
     * @author zhaoth
     */
    boolean checkPassword(String password, String dbPassword);


    /**
     * 获取账号账号总数
     *
     * @return int
     * @author zhaoth
     */
    int selectCount();

    List<Account> selectPaging(Integer currentPage, Integer pageSize);

    /**
     * 通过身份证号，真实姓名，登录，账号状态做筛选，通过筛选的账号总数
     *
     * @param accountIdcardNo  身份证号
     * @param accountRealName  真实姓名
     * @param accountLoginName 登录
     * @param accountStatus    账号状态
     * @return int
     * @author zhaoth
     */
    int selectCountFilter(String accountIdcardNo, String accountRealName, String accountLoginName, String accountStatus);

    List<Account> selectPagingFilter(Integer currentPage, Integer pageSize, String accountIdcardNo
            , String accountRealName, String accountLoginName, String accountStatus);

    /**
     * 新建即开通，记录创建时间
     *
     * @return int
     * @author zhaoth
     */
    int insertAccount(Account account);

    /**
     * 被删除的账号不可开通
     * 开通时删除暂停时间
     *
     * @return int
     * @author zhaoth
     */
    int startUsing(Integer accountId);

    /**
     * 被删除的账号不可暂停
     * 暂停时记录暂停时间，同时暂停所有业务账号
     *
     * @return int
     * @author zhaoth
     */
    int pauseUsing(Integer accountId);

    /**
     * 删除时记录删除时间，同时删除所有业务账号
     *
     * @return int
     * @author zhaoth
     */
    int deleteAccount(Integer accountId);

    /**
     * 被删除的账号不可修改
     *
     * @return int
     * @author zhaoth
     */
    int updateAccount(Account account);

}
