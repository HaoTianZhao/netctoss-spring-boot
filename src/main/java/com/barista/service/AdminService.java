package com.barista.service;

import com.barista.entity.AdminInfo;

import java.util.List;

/**
 * @ClassName AdminService
 * @Author zhaoth
 * @Date 2019/8/21 15:42
 * @Version 1.0
 */
public interface AdminService {

    /**
     * 通过管理员账号查询管理员信息
     *
     * @param adminCode 管理员账号
     * @return com.barista.entity.AdminInfo 管理员信息
     * @author zhaoth
     */
    AdminInfo selectByAdminCode(String adminCode);

    /**
     * 查询管理员总数
     */
    Integer selectCount();

    /**
     * 分页查询
     *
     * @param currentPage    第几页，从0开始
     * @param pageSize 每页行数
     * @return java.util.List<com.barista.entity.Role>
     * @author zhaoth
     */
    List<AdminInfo> selectPaging(Integer currentPage, Integer pageSize);

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
     * 修改管理员密码
     *
     * @param adminCode 管理员账号
     * @param password  新密码
     * @return int
     * @author zhaoth
     */
    int changePassword(String adminCode, String password);

    /**
     * 更改管理员信息
     *
     * @param adminInfo 管理员信息
     * @return int
     * @author zhaoth
     */
    int updateAdminInfo(AdminInfo adminInfo);

    /**
     * 重置密码
     *
     * @param adminIds  管理员id列表
     * @param passwords 管理员密码列表
     * @return int
     * @author zhaoth
     */
    int resetPasswords(List<Integer> adminIds, List<String> passwords);

}
