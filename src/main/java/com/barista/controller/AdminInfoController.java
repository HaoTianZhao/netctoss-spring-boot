package com.barista.controller;

import com.alibaba.fastjson.JSON;
import com.barista.VO.AdminManageVO;
import com.barista.VO.PersonalInfoVO;
import com.barista.entity.AdminInfo;
import com.barista.entity.Role;
import com.barista.result.Result;
import com.barista.result.ResultCode;
import com.barista.service.AdminRoleService;
import com.barista.service.AdminService;
import com.barista.service.RolePrivilegeService;
import com.barista.util.MD5Util;
import com.barista.util.ValueUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpSession;

/**
 * 管理员管理
 *
 * @ClassName AdminInfoController
 * @Author zhaoth
 * @Date 2019/8/22 20:27
 * @Version 1.0
 */
@RestController
@RequestMapping("/admin")
public class AdminInfoController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private AdminRoleService adminRoleService;

    @Autowired
    RolePrivilegeService rolePriService;


    @RequestMapping("/getAdminInfo")
    public Result getAdminInfo(String adminCode, HttpSession session) {
        if (adminCode == null) {
            return Result.fail(ResultCode.ILLEGAL_PARAM);
        }
        if (!Objects.equals(adminCode, session.getAttribute("token"))) {
            return Result.fail(ResultCode.NO_PERMISSION);
        }
        AdminInfo adminInfo = adminService.selectByAdminCode(adminCode);
        List<Role> roles = adminRoleService.selectRolesByAdminCode(adminCode);
        PersonalInfoVO personalInfoVO = new PersonalInfoVO(adminInfo, roles);
        JSON.toJSON(personalInfoVO);
        return Result.success(personalInfoVO);
    }

    @RequestMapping("/updateAdminInfo")
    public Result updateAdminInfo(AdminInfo adminInfo, HttpSession session) {
        if (adminInfo.getAdminCode() == null) {
            return Result.fail(ResultCode.ILLEGAL_PARAM);
        }
        if (!Objects.equals(adminInfo.getAdminCode(), session.getAttribute("token"))) {
            return Result.fail(ResultCode.NO_PERMISSION);
        }
        AdminInfo admin = new AdminInfo();
        admin.setAdminCode(adminInfo.getAdminCode());
        admin.setAdminName(adminInfo.getAdminName());
        admin.setAdminEmail(adminInfo.getAdminEmail());
        admin.setAdminTelephone(admin.getAdminTelephone());
        int result = adminService.updateAdminInfo(admin);
        if (result > 0) {
            return Result.success("修改管理员信息成功");
        } else {
            return Result.fail(ResultCode.SERVER_ERROR);
        }

    }

    @RequestMapping("/changePassword")
    public Result changePassword(String oldPassword, String newPassword, HttpSession session) {
        if (oldPassword == null || newPassword == null) {
            return Result.fail(ResultCode.ILLEGAL_PARAM);
        }
        String adminCode = Objects.toString(session.getAttribute("token"), "");
        AdminInfo dbAdmin = adminService.selectByAdminCode(adminCode);
        if (!adminService.checkPassword(oldPassword, dbAdmin.getAdminPassword())) {
            return Result.fail(ResultCode.PASSWORD_ERROR);
        }

        int result = adminService.changePassword(dbAdmin.getAdminCode(), newPassword);
        if (result > 0) {
            return Result.success("修改密码成功");
        } else {
            return Result.fail(ResultCode.SERVER_ERROR);
        }
    }

    @SuppressWarnings("all")
    @RequestMapping("getPageInfo")
    public Result getPageInfo(Integer currentPage, Integer pageSize) {
        if (ValueUtil.haveNullOrBlack(currentPage, pageSize)) {
            return Result.fail(ResultCode.ILLEGAL_PARAM);
        }
        int count = adminService.selectCount();
        List<Role> list = adminRoleService.selectAllRole();
        AdminManageVO adminManageVO = new AdminManageVO(count, list);

        List<AdminInfo> adminInfos = adminService.selectPaging(currentPage, pageSize);
        for (AdminInfo adminInfo : adminInfos) {
            adminInfo.setAdminPassword(null);
            List<Role> roles = adminRoleService.selectRolesByAdminCode(adminInfo.getAdminCode());
            adminManageVO.add(adminInfo, roles.stream().collect(ArrayList::new, (a, b) -> a.add(b.getRoleName()), ArrayList::addAll));
        }

        return Result.success(adminManageVO);
    }

    @SuppressWarnings("all")
    @RequestMapping("getPageInfoSelected")
    public Result getPageInfoSelected(Integer currentPage, Integer pageSize, String privilegeName, String roleName) {
        if (ValueUtil.haveNullOrBlack(currentPage, pageSize)) {
            return Result.fail(ResultCode.ILLEGAL_PARAM);
        }
        if (privilegeName.equals("全部")) {
            privilegeName = null;
        }
        if (ValueUtil.haveNullOrBlack(roleName)) {
            roleName = null;
        }
        int count = adminRoleService.selectCountFilter(privilegeName, roleName);
        List<Role> list = adminRoleService.selectAllRole();
        AdminManageVO adminManageVO = new AdminManageVO(count, list);

        List<AdminInfo> adminInfos = adminRoleService.selectPagingFilter(currentPage, pageSize, privilegeName, roleName);
        for (AdminInfo adminInfo : adminInfos) {
            adminInfo.setAdminPassword(null);
            List<Role> roles = adminRoleService.selectRolesByAdminCode(adminInfo.getAdminCode());
            adminManageVO.add(adminInfo, roles.stream().collect(ArrayList::new, (a, b) -> a.add(b.getRoleName()), ArrayList::addAll));
        }

        return Result.success(adminManageVO);
    }

    @RequestMapping("/resetPassword")
    public Result resetPassword(@RequestParam("adminIds[]") Integer[] adminIds, @RequestParam("adminCodes[]") String[] adminCodes) {
        if (ValueUtil.haveNullOrBlack(adminCodes, adminCodes) || adminIds.length != adminCodes.length) {
            return Result.fail(ResultCode.ILLEGAL_PARAM);
        }
        List<String> passwords = new ArrayList<>(adminCodes.length);
        for (String adminCode : adminCodes) {
            passwords.add(MD5Util.encode(adminCode));
        }

        int result = adminService.resetPasswords(Arrays.asList(adminIds), passwords);
        if (result > 0) {
            return Result.success("重置密码成功");
        } else {
            return Result.fail(ResultCode.SERVER_ERROR);
        }
    }

    @RequestMapping("/add")
    public Result addAdmin(AdminInfo adminInfo, Integer[] roleIds) {
        if (ValueUtil.haveNullOrBlack(adminInfo, roleIds)) {
            return Result.fail(ResultCode.ILLEGAL_PARAM);
        }
        adminInfo.setAdminPassword(MD5Util.encode(adminInfo.getAdminPassword()));
        int result = adminRoleService.insertAdminRole(adminInfo, new ArrayList<>(Arrays.asList(roleIds)));

        if (result > 0) {
            return Result.success("增加管理员成功");
        } else {
            return Result.fail(ResultCode.SERVER_ERROR);
        }

    }

    @RequestMapping("/delete")
    public Result deleteAdmin(Integer adminId) {
        if (ValueUtil.haveNullOrBlack(adminId)) {
            return Result.fail(ResultCode.ILLEGAL_PARAM);
        }
        int result = adminRoleService.deleteAdminAndRoles(adminId);
        if (result > 0) {
            return Result.success("删除管理员成功");
        } else {
            return Result.fail(ResultCode.SERVER_ERROR);
        }

    }

    @RequestMapping("/update")
    public Result updateAdmin(AdminInfo adminInfo, Integer[] roleIds) {
        if (ValueUtil.haveNullOrBlack(adminInfo, roleIds)) {
            return Result.fail(ResultCode.ILLEGAL_PARAM);
        }
        adminInfo.setAdminCode(null);
        adminInfo.setAdminPassword(null);
        int result = adminRoleService.updateAdminAndRoles(adminInfo, new ArrayList<>(Arrays.asList(roleIds)));
        if (result > 0) {
            return Result.success("更改管理员信息成功");
        } else {
            return Result.fail(ResultCode.SERVER_ERROR);
        }
    }

}
