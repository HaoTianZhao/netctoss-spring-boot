package com.barista.entity;

import java.io.Serializable;

public class RolePrivilege implements Serializable {
    private Integer roleId;

    private Integer privilegeGroupId;

    private static final long serialVersionUID = 1L;

    public RolePrivilege(Integer roleId, Integer privilegeGroupId) {
        this.roleId = roleId;
        this.privilegeGroupId = privilegeGroupId;
    }

    public RolePrivilege() {
        super();
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getPrivilegeGroupId() {
        return privilegeGroupId;
    }

    public void setPrivilegeGroupId(Integer privilegeGroupId) {
        this.privilegeGroupId = privilegeGroupId;
    }
}