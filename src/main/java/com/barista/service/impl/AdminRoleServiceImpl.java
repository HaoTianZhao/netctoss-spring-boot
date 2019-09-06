package com.barista.service.impl;

import com.barista.dao.AdminInfoMapper;
import com.barista.dao.AdminRoleMapper;
import com.barista.dao.RoleMapper;
import com.barista.entity.AdminInfo;
import com.barista.entity.Role;
import com.barista.service.AdminRoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

/**
 * @ClassName AdminRoleServiceImpl
 * @Author zhaoth
 * @Date 2019/8/21 20:08
 * @Version 1.0
 */
@Service
public class AdminRoleServiceImpl implements AdminRoleService {
    @Autowired
    private AdminInfoMapper adminInfoMapper;

    @Autowired
    private AdminRoleMapper adminRoleMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> selectRolesByAdminCode(String adminCode) {
        AdminInfo adminInfo = adminInfoMapper.selectByAdminCode(adminCode);
        List<Integer> roleIds = adminRoleMapper.selectAllRoleIdsByAdminId(adminInfo.getAdminId());
        List<Role> roles = roleMapper.selectByRoleIds(roleIds);

        return roles;
    }

    @Override
    public int insertAdminRole(AdminInfo adminInfo, List<Integer> roleIds) {

            int result1 = adminInfoMapper.insertSelective(adminInfo);
            int result2 = adminRoleMapper.insertAdminRoles(adminInfo.getAdminId(), roleIds);
            return getResult(result1, result2);

    }

    @Override
    public int deleteAdminAndRoles(Integer adminId) {
        int result1 = adminInfoMapper.deleteByPrimaryKey(adminId);
        int result2 = adminRoleMapper.deleteByAdminId(adminId);
        return getResult(result1, result2);
    }

    @Override
    public int updateAdminAndRoles(AdminInfo adminInfo, List<Integer> roleIds) {

            adminInfoMapper.updateByPrimaryKeySelective(adminInfo);
            int result1 = adminRoleMapper.deleteByAdminId(adminInfo.getAdminId());
            int result2 = adminRoleMapper.insertAdminRoles(adminInfo.getAdminId(), roleIds);
            return getResult(result1, result2);

    }

    @Override
    public Integer selectCountFilter(String privilegeName, String roleName) {
        return adminRoleMapper.selectCountFilter(privilegeName, roleName);
    }

    @Override
    public List<AdminInfo> selectPagingFilter(Integer currentPage, Integer pageSize, String privilegeName, String roleName) {
        return adminRoleMapper.selectPagingFilter((currentPage - 1) * pageSize, pageSize, privilegeName, roleName);
    }

    @Override
    public List<Role> selectAllRole() {
        return roleMapper.selectAll();
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
