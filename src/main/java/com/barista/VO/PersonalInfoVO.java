package com.barista.VO;

import com.barista.entity.AdminInfo;
import com.barista.entity.Role;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 个人信息页面
 *
 * @ClassName PersonalInfoVO
 * @Author zhaoth
 * @Date 2019/8/23 16:42
 * @Version 1.0
 */
public class PersonalInfoVO {
    private AdminInfo adminInfo;
    private List<String> roleNames;

    public PersonalInfoVO() {
    }

    public PersonalInfoVO(AdminInfo adminInfo, List<Role> roles) {
        this.adminInfo = adminInfo;
        this.roleNames = roles.stream().map(Role::getRoleName).collect(Collectors.toList());
        this.adminInfo.setAdminId(0);
        this.adminInfo.setAdminPassword(null);
    }

    public AdminInfo getAdminInfo() {
        return adminInfo;
    }

    public void setAdminInfo(AdminInfo adminInfo) {
        this.adminInfo = adminInfo;
    }

    public List<String> getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(List<String> roleNames) {
        this.roleNames = roleNames;
    }
}
