package com.barista.entity;

import com.alibaba.fastjson.annotation.JSONField;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class AdminInfo implements Serializable {
    private Integer adminId;

    private String adminCode;

    private String adminPassword;

    private String adminName;

    private String adminTelephone;

    private String adminEmail;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date adminEnrolldate;

    private static final long serialVersionUID = 1L;

    public AdminInfo(Integer adminId, String adminCode, String adminPassword, String adminName, String adminTelephone, String adminEmail, Date adminEnrolldate) {
        this.adminId = adminId;
        this.adminCode = adminCode;
        this.adminPassword = adminPassword;
        this.adminName = adminName;
        this.adminTelephone = adminTelephone;
        this.adminEmail = adminEmail;
        this.adminEnrolldate = adminEnrolldate;
    }

    public AdminInfo() {
        super();
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getAdminCode() {
        return adminCode;
    }

    public void setAdminCode(String adminCode) {
        this.adminCode = adminCode;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminTelephone() {
        return adminTelephone;
    }

    public void setAdminTelephone(String adminTelephone) {
        this.adminTelephone = adminTelephone;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public Date getAdminEnrolldate() {
        return adminEnrolldate;
    }

    public void setAdminEnrolldate(Date adminEnrolldate) {
        this.adminEnrolldate = adminEnrolldate;
    }
}