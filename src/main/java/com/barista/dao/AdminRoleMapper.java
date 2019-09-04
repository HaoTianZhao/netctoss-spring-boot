package com.barista.dao;

import com.barista.entity.AdminInfo;
import com.barista.entity.AdminRole;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AdminRoleMapper {
    //    int deleteByPrimaryKey(@Param("adminId") Integer adminId, @Param("roleId") Integer roleId);
    //
    //    int insertRolePrivileges(AdminRole record);
    //
    //    int insertSelective(AdminRole record);

    List<Integer> selectAllRoleIdsByAdminId(Integer adminId);

    int selectAdminCountByRoleId(Integer roleId);

    int insertAdminRoles(@Param("adminId") Integer adminId, @Param("roleIds") List<Integer> roleIds);

    int deleteByAdminId(Integer adminId);

    int selectCountFilter(@Param("privilegeName") String privilegeName, @Param("roleName") String roleName);

    List<AdminInfo> selectPagingFilter(@Param("begin") Integer begin, @Param("pageSize") Integer pageSize,
                                       @Param("privilegeName") String privilegeName, @Param("roleName") String roleName);

}