package com.barista.entity;

/**
 * TODO
 *
 * @ClassName MyInteger
 * @Author zhaoth
 * @Date 2019/9/18 18:26
 * @Version 1.0
 */
public class MyInteger {
    Integer adminRole;

    @Override
    public String toString() {
        return "MyInteger{" +
                "adminRole=" + adminRole +
                '}';
    }

    public MyInteger() {
    }

    public MyInteger(Integer adminRole) {
        this.adminRole = adminRole;
    }

    public Integer getAdminRole() {
        return adminRole;
    }

    public void setAdminRole(Integer adminRole) {
        this.adminRole = adminRole;
    }
}
