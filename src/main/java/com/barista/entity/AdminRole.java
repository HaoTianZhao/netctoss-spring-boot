package com.barista.entity;

import java.io.Serializable;

public class AdminRole implements Serializable {
    private Integer adminId;

    private Integer roleId;

    private static final long serialVersionUID = 1L;

    public AdminRole(Integer adminId, Integer roleId) {
        this.adminId = adminId;
        this.roleId = roleId;
    }

    public AdminRole() {
        super();
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}