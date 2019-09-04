package com.barista.dao;

import com.barista.entity.Role;
import com.barista.entity.RolePrivilege;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface RolePrivilegeMapper {

    /**
     * Map中的键为role_id,privilege_group_name
     *
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.String>>
     * @author zhaoth
     */
    List<Map<String, Object>> selectPrivilegeGroupNames(List<Integer> roleIds);

    int insertRolePrivileges(@Param("role") Role role, @Param("privilegeGroupIds") List<Integer> privilegeGroupIds);

    int deleteByRoleId(Integer roleId);
}
