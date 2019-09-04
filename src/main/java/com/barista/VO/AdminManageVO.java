package com.barista.VO;

import com.barista.entity.AdminInfo;
import com.barista.entity.Role;

import java.util.ArrayList;
import java.util.List;

/**
 * 管理员管理页面
 *
 * @ClassName AdminManageVO
 * @Author zhaoth
 * @Date 2019/8/26 16:29
 * @Version 1.0
 */
public class AdminManageVO {
    private int totalCount;
    private List<AdminColumn> adminColumns;
    private List<Role> roles;//所有角色

    public AdminManageVO() {
    }

    public AdminManageVO(int count, List roles) {
        totalCount = count;
        adminColumns = new ArrayList<>();
        this.roles = roles;
    }

    public void add(AdminInfo adminInfo, List<String> roleNames) {
        adminColumns.add(new AdminColumn(adminInfo, roleNames));
    }

    private class AdminColumn {
        AdminInfo adminInfo;
        List<String> roleNames;

        public AdminColumn() {
        }

        public AdminColumn(AdminInfo adminInfo, List<String> roleNames) {
            this.adminInfo = adminInfo;
            this.roleNames = roleNames;
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

    public List<AdminColumn> getAdminColumns() {
        return adminColumns;
    }

    public void setAdminColumns(List<AdminColumn> adminColumns) {
        this.adminColumns = adminColumns;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}
