package com.barista.controller;

import com.barista.entity.Role;
import com.barista.entity.RolePrivilege;
import com.barista.result.Result;
import com.barista.result.ResultCode;
import com.barista.service.RolePrivilegeService;
import com.barista.util.ValueUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 角色管理
 *
 * @ClassName RoleController
 * @Author zhaoth
 * @Date 2019/8/24 11:05
 * @Version 1.0
 */
@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RolePrivilegeService rolePrivilegeService;

    @RequestMapping("/getPageInfo")
    public Result getRolePage(Integer currentPage, Integer pageSize) {
        if (ValueUtil.haveNullOrBlack(currentPage, pageSize)) {
            return Result.fail(ResultCode.ILLEGAL_PARAM);
        }
        return Result.success(rolePrivilegeService.selectAllRolePrivileges(currentPage, pageSize));

    }

    @RequestMapping("/add")
    public Result addRole(Role role, Integer[] privilegeGroupIds) {
        if (ValueUtil.haveNullOrBlack(role, privilegeGroupIds)) {
            return Result.fail(ResultCode.ILLEGAL_PARAM);
        }
        int result = rolePrivilegeService.insertRolePrivilege(role, new ArrayList<>(Arrays.asList(privilegeGroupIds)));

        if (result > 0) {
            return Result.success("增加角色成功");
        } else {
            return Result.fail(ResultCode.SERVER_ERROR);
        }

    }

    @RequestMapping("/delete")
    public Result deleteRole(Integer roleId) {
        if (ValueUtil.haveNullOrBlack(roleId)) {
            return Result.fail(ResultCode.ILLEGAL_PARAM);
        }
        if (rolePrivilegeService.selectAdminCountByRoleId(roleId) > 0) {
            return Result.fail(ResultCode.IS_USING);
        }
        int result = rolePrivilegeService.deleteRole(roleId);
        if (result > 0) {
            return Result.success("删除角色成功");
        } else {
            return Result.fail(ResultCode.SERVER_ERROR);
        }

    }

    @RequestMapping("/update")
    public Result updateRole(Role role, Integer[] privilegeGroupIds) {
        if (ValueUtil.haveNullOrBlack(role.getRoleId(), role.getRoleName(), privilegeGroupIds)) {
            return Result.fail(ResultCode.ILLEGAL_PARAM);
        }
        int result = rolePrivilegeService.updateRoleAndPrivileges(role, new ArrayList<>(Arrays.asList(privilegeGroupIds)));
        if (result > 0) {
            return Result.success("更改角色成功");
        } else {
            return Result.fail(ResultCode.SERVER_ERROR);
        }
    }

}
