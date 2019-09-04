package com.barista.service;

import com.barista.entity.AdminInfo;
import com.barista.entity.AdminRole;
import com.barista.entity.Role;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName AdminRoleService
 * @Author zhaoth
 * @Date 2019/8/21 20:06
 * @Version 1.0
 */
public interface AdminRoleService {

    /**
     * 返回管理员具有的角色
     *
     * @param adminCode 管理员账号
     * @return java.util.List<com.barista.entity.Role> 管理员角色列表
     * @author zhaoth
     */
    List<Role> selectRolesByAdminCode(String adminCode);

    /**
     * 新增管理员和它对应的角色关联信息
     *
     * @param adminInfo 管理员信息
     * @param roleIds   它具有的角色id
     * @return int
     * @author zhaoth
     */
    int insertAdminRole(AdminInfo adminInfo, List<Integer> roleIds);

    /**
     * 删除管理员和它对应的角色关联信息
     *
     * @param adminId 管理员id
     * @return
     */
    int deleteAdminAndRoles(Integer adminId);

    /**
     * 更改管理员信息和它对应的角色关联信息
     *
     * @param adminInfo 管理员id
     * @param roleIds   角色id列表
     * @return int
     * @author zhaoth
     */
    int updateAdminAndRoles(AdminInfo adminInfo, List<Integer> roleIds);

    /**
     * 查询所有角色信息
     *
     * @return com.barista.entity.Role
     * @author zhaoth
     */
    List<Role> selectAllRole();

    /**
     * 具有指定权限组和角色名的管理员数量
     *
     * @param privilegeName 权限组名
     * @param roleName      角色名
     * @return java.lang.Integer
     * @author zhaoth
     */
    Integer selectCountFilter(String privilegeName, String roleName);

    /**
     * 具有指定权限组和角色名的分页查询
     *
     * @param currentPage   当前页
     * @param pageSize      页行数
     * @param privilegeName 权限组名
     * @param roleName      角色名
     * @return
     */
    List<AdminInfo> selectPagingFilter(Integer currentPage, Integer pageSize, String privilegeName, String roleName);
}
