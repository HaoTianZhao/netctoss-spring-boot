package com.barista.entity;

import java.io.Serializable;
import java.util.Date;

public class ServiceUpdate implements Serializable {
    private Integer id;

    private Integer serviceId;

    private String unixHost;

    private String osUsername;

    private Integer costId;

    private Date serviceCreateDate;

    private static final long serialVersionUID = 1L;

    public ServiceUpdate(Integer id, Integer serviceId, String unixHost, String osUsername, Integer costId, Date serviceCreateDate) {
        this.id = id;
        this.serviceId = serviceId;
        this.unixHost = unixHost;
        this.osUsername = osUsername;
        this.costId = costId;
        this.serviceCreateDate = serviceCreateDate;
    }

    public ServiceUpdate() {
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

    public Integer getCostId() {
        return costId;
    }

    public void setCostId(Integer costId) {
        this.costId = costId;
    }

    public Date getServiceCreateDate() {
        return serviceCreateDate;
    }

    public void setServiceCreateDate(Date serviceCreateDate) {
        this.serviceCreateDate = serviceCreateDate;
    }
}