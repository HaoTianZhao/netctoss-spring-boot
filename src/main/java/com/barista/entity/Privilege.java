package com.barista.entity;

import java.io.Serializable;

public class Privilege implements Serializable {
    private Integer privilegeGroupId;

    private Integer privilegeId;

    private String privilegeGroupName;

    private String privilegeName;

    private String privilegeUrl;

    private static final long serialVersionUID = 1L;

    public Privilege(Integer privilegeGroupId, Integer privilegeId, String privilegeGroupName, String privilegeName, String privilegeUrl) {
        this.privilegeGroupId = privilegeGroupId;
        this.privilegeId = privilegeId;
        this.privilegeGroupName = privilegeGroupName;
        this.privilegeName = privilegeName;
        this.privilegeUrl = privilegeUrl;
    }

    public Privilege() {
        super();
    }

    public Integer getPrivilegeGroupId() {
        return privilegeGroupId;
    }

    public void setPrivilegeGroupId(Integer privilegeGroupId) {
        this.privilegeGroupId = privilegeGroupId;
    }

    public Integer getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(Integer privilegeId) {
        this.privilegeId = privilegeId;
    }

    public String getPrivilegeGroupName() {
        return privilegeGroupName;
    }

    public void setPrivilegeGroupName(String privilegeGroupName) {
        this.privilegeGroupName = privilegeGroupName;
    }

    public String getPrivilegeName() {
        return privilegeName;
    }

    public void setPrivilegeName(String privilegeName) {
        this.privilegeName = privilegeName;
    }

    public String getPrivilegeUrl() {
        return privilegeUrl;
    }

    public void setPrivilegeUrl(String privilegeUrl) {
        this.privilegeUrl = privilegeUrl;
    }
}