package com.barista.entity;

import java.util.Date;
import java.util.List;

/**
 * TODO
 *
 * @ClassName Test
 * @Author zhaoth
 * @Date 2019/9/18 18:05
 * @Version 1.0
 */
public class Test {
    private Integer adminId;
    private MyInteger adminRole;
    private String adminCode;
    private String adminAdminPassword;
    private String adminName;
    private String adminTelephone;
    private String adminEmail;
    private Date adminEnrolldate;

    public Test() {
    }

    public Test(Integer adminId, MyInteger adminRole, String adminCode, String adminAdminPassword, String adminName, String adminTelephone, String adminEmail, Date adminEnrolldate) {
        this.adminId = adminId;
        this.adminRole = adminRole;
        this.adminCode = adminCode;
        this.adminAdminPassword = adminAdminPassword;
        this.adminName = adminName;
        this.adminTelephone = adminTelephone;
        this.adminEmail = adminEmail;
        this.adminEnrolldate = adminEnrolldate;
    }

    @Override
    public String toString() {
        return "Test{" +
                "adminId=" + adminId +
                ", adminRole=" + adminRole +
                ", adminCode='" + adminCode + '\'' +
                ", adminAdminPassword='" + adminAdminPassword + '\'' +
                ", adminName='" + adminName + '\'' +
                ", adminTelephone='" + adminTelephone + '\'' +
                ", adminEmail='" + adminEmail + '\'' +
                ", adminEnrolldate=" + adminEnrolldate +
                '}';
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public MyInteger getAdminRole() {
        return adminRole;
    }

    public void setAdminRole(MyInteger adminRole) {
        this.adminRole = adminRole;
    }

    public String getAdminCode() {
        return adminCode;
    }

    public void setAdminCode(String adminCode) {
        this.adminCode = adminCode;
    }

    public String getAdminAdminPassword() {
        return adminAdminPassword;
    }

    public void setAdminAdminPassword(String adminAdminPassword) {
        this.adminAdminPassword = adminAdminPassword;
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
