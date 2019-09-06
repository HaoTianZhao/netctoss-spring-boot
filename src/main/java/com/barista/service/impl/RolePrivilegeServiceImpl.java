package com.barista.service.impl;

import com.barista.VO.RoleManageVO;
import com.barista.dao.AdminRoleMapper;
import com.barista.dao.RoleMapper;
import com.barista.dao.RolePrivilegeMapper;
import com.barista.entity.Role;
import com.barista.service.RolePrivilegeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @ClassName RolePrivilegeServiceImpl
 * @Author zhaoth
 * @Date 2019/8/24 11:35
 * @Version 1.0
 */
@Service
public class RolePrivilegeServiceImpl implements RolePrivilegeService {
    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RolePrivilegeMapper rolePriMapper;

    @Autowired
    private AdminRoleMapper adminRoleMapper;

    @Override
    public RoleManageVO selectAllRolePrivileges(Integer currentPage, Integer pageSize) {
        int count = roleMapper.selectCount();
        RoleManageVO roleManageVO = new RoleManageVO(count);

        List<Role> roles = roleMapper.selectPaging((currentPage - 1) * pageSize, pageSize);
        for (Role role : roles) {
            List<Map<String, Object>> map = selectPrivilegeGroupNames(Arrays.asList(role.getRoleId()));
            Set<String> privilegeSet = map.stream()
                    .map(t -> (String) t.get("privilege_group_name")).collect(Collectors.toSet());
            roleManageVO.add(role, new ArrayList<>(privilegeSet));
        }

        return roleManageVO;
    }

    @Override
    public List<Map<String, Object>> selectPrivilegeGroupNames(List<Integer> roleIds) {
        return rolePriMapper.selectPrivilegeGroupNames(roleIds);
    }

    @Override
    public int insertRolePrivilege(Role role, List<Integer> privilegeGroupIds) {

            int result1 = roleMapper.insertSelective(role);
            int result2 = rolePriMapper.insertRolePrivileges(role, privilegeGroupIds);
            return getResult(result1, result2);

    }

    @Override
    public int deleteRole(Integer roleId) {
        int result1 = roleMapper.deleteByPrimaryKey(roleId);
        int result2 = rolePriMapper.deleteByRoleId(roleId);
        return getResult(result1, result2);
    }

    @Override
    public int updateRoleAndPrivileges(Role role, List<Integer> privilegeGroupIds) {

            roleMapper.updateByPrimaryKeySelective(role);
            int result1 = rolePriMapper.deleteByRoleId(role.getRoleId());
            int result2 = rolePriMapper.insertRolePrivileges(role, privilegeGroupIds);
            return getResult(result1, result2);

    }

    @Override
    public int selectAdminCountByRoleId(Integer roleId) {
        return adminRoleMapper.selectAdminCountByRoleId(roleId);
    }

    private int getResult(int... results) {
        for (int i : results) {
            if (i == 0) {
                return 0;
            }
        }
        return 1;
    }
}
