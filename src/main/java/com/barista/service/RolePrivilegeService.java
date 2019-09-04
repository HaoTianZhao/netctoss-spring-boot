package com.barista.service;

import com.barista.VO.RoleManageVO;
import com.barista.entity.Role;

import java.util.List;
import java.util.Map;

/**
 * @ClassName RolePrivilegeService
 * @Author zhaoth
 * @Date 2019/8/24 11:13
 * @Version 1.0
 */
public interface RolePrivilegeService {


    /**
     * 分页查询
     *
     * @param currentPage 需要获取的页编号
     * @param pageSize 每页的长度
     * @return com.barista.VO.RoleManageVO
     * @author zhaoth
     */
    RoleManageVO selectAllRolePrivileges(Integer currentPage, Integer pageSize);

    /**
     * 根据角色id列表，查询所具有的权限组名
     * @param roleIds 角色id列表
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>> 
     * @author zhaoth
     */
    List<Map<String, Object>> selectPrivilegeGroupNames(List<Integer> roleIds);

    /**
     * 新建一个角色，并赋予指定权限
     *
     * @param role              角色信息
     * @param privilegeGroupIds 权限组id
     * @return int
     * @author zhaoth
     */
    int insertRolePrivilege(Role role, List<Integer> privilegeGroupIds);

    /**
     * 修改指定角色信息和所拥有的权限
     *
     * @param role              角色信息
     * @param privilegeGroupIds 权限组id
     * @return int
     * @author zhaoth
     */
    int updateRoleAndPrivileges(Role role, List<Integer> privilegeGroupIds);

    /**
     * 删除一个角色
     *
     * @param roleId 角色id
     * @return int
     * @author zhaoth
     */
    int deleteRole(Integer roleId);

    /**
     * 查找有无角色被管理员使用
     * @param roleId 角色id
     * @return int 使用此角色的管理员数量
     * @author zhaoth
     */
    int selectAdminCountByRoleId(Integer roleId);

}
