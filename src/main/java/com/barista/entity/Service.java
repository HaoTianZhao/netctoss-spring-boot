package com.barista.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

public class Service implements Serializable {
    private Integer serviceId;

    private Integer accountId;

    private String unixHost;

    private String osUsername;

    private String loginPasswd;

    private String serviceStatus;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date serviceCreateDate;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date servicePauseDate;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date serviceCloseDate;

    private Integer costId;

    private static final long serialVersionUID = 1L;

    public Service(Integer serviceId, Integer accountId, String unixHost, String osUsername, String loginPasswd, String serviceStatus, Date serviceCreateDate, Date servicePauseDate, Date serviceCloseDate, Integer costId) {
        this.serviceId = serviceId;
        this.accountId = accountId;
        this.unixHost = unixHost;
        this.osUsername = osUsername;
        this.loginPasswd = loginPasswd;
        this.serviceStatus = serviceStatus;
        this.serviceCreateDate = serviceCreateDate;
        this.servicePauseDate = servicePauseDate;
        this.serviceCloseDate = serviceCloseDate;
        this.costId = costId;
    }

    public Service() {
        super();
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getUnixHost() {
        return unixHost;
    }

    public void setUnixHost(String unixHost) {
        this.unixHost = unixHost;
    }

    public String getOsUsername() {
        return osUsername;
    }

    public void setOsUsername(String osUsername) {
        this.osUsername = osUsername;
    }

    public String getLoginPasswd() {
        return loginPasswd;
    }

    public void setLoginPasswd(String loginPasswd) {
        this.loginPasswd = loginPasswd;
    }

    public String getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(String serviceStatus) {
        this.serviceStatus = serviceStatus;
    }

    public Date getServiceCreateDate() {
        return serviceCreateDate;
    }

    public void setServiceCreateDate(Date serviceCreateDate) {
        this.serviceCreateDate = serviceCreateDate;
    }

    public Date getServicePauseDate() {
        return servicePauseDate;
    }

    public void setServicePauseDate(Date servicePauseDate) {
        this.servicePauseDate = servicePauseDate;
    }

    public Date getServiceCloseDate() {
        return serviceCloseDate;
    }

    public void setServiceCloseDate(Date serviceCloseDate) {
        this.serviceCloseDate = serviceCloseDate;
    }

    public Integer getCostId() {
        return costId;
    }

    public void setCostId(Integer costId) {
        this.costId = costId;
    }
}