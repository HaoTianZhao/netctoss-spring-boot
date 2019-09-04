package com.barista.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ServiceDetail implements Serializable {
    private Integer id;

    private Integer serviceId;

    private String unixHost;

    private String osUsername;

    private Integer pid;

    private Date loginTime;

    private Date logoutTime;

    private BigDecimal duration;

    private BigDecimal cost;

    private static final long serialVersionUID = 1L;

    public ServiceDetail(Integer id, Integer serviceId, String unixHost, String osUsername, Integer pid, Date loginTime, Date logoutTime, BigDecimal duration, BigDecimal cost) {
        this.id = id;
        this.serviceId = serviceId;
        this.unixHost = unixHost;
        this.osUsername = osUsername;
        this.pid = pid;
        this.loginTime = loginTime;
        this.logoutTime = logoutTime;
        this.duration = duration;
        this.cost = cost;
    }

    public ServiceDetail() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
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

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Date getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(Date logoutTime) {
        this.logoutTime = logoutTime;
    }

    public BigDecimal getDuration() {
        return duration;
    }

    public void setDuration(BigDecimal duration) {
        this.duration = duration;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
}