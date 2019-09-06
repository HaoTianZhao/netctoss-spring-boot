package com.barista.VO;

import com.barista.entity.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 角色管理页面
 *
 * @ClassName RoleManageVO
 * @Author zhaoth
 * @Date 2019/8/24 11:14
 * @Version 1.0
 */
public class RoleManageVO {

    private List<RoleColumn> roleColumns;
    private int totalCount;

    public RoleManageVO() {
    }

    public RoleManageVO(int count) {
        totalCount = count;
        roleColumns = new ArrayList<>();
    }

    public void add(Role role, List<String> privilegeNames) {
        roleColumns.add(new RoleColumn(role.getRoleId(), role.getRoleName(), privilegeNames));
    }

    private class RoleColumn {
        private Integer roleId;
        private String roleName;
        private List<String> privilegeName;

        public RoleColumn() {
        }

        public RoleColumn(Integer roleId, String roleName, List<String> privilegeName) {
            this.roleId = roleId;
            this.roleName = roleName;
            this.privilegeName = privilegeName;
        }

        public Integer getRoleId() {
            return roleId;
        }

        public void setRoleId(Integer roleId) {
            this.roleId = roleId;
        }

        public String getRoleName() {
            return roleName;
        }

        public void setRoleName(String roleName) {
            this.roleName = roleName;
        }

        public List<String> getPrivilegeName() {
            return privilegeName;
        }

        public void setPrivilegeName(List<String> privilegeName) {
            this.privilegeName = privilegeName;
        }
    }

    public List<RoleColumn> getRoleColumns() {
        return roleColumns;
    }

    public void setRoleColumns(List<RoleColumn> roleColumns) {
        this.roleColumns = roleColumns;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}
