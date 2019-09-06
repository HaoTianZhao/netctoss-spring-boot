package com.barista.entity;

import com.alibaba.fastjson.annotation.JSONField;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Cost implements Serializable {
    private Integer costId;

    private String costName;

    private Integer costBaseDuration;

    private BigDecimal costBaseCost;

    private BigDecimal costUnitCost;

    /**
     * 资费状态为1(暂停)时，才可以修改和删除
     */
    private String costStatus;

    private String costDescr;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date costCreattime;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date costStarttime;

    private String costType;

    private static final long serialVersionUID = 1L;

    public Cost(Integer costId, String costName, Integer costBaseDuration, BigDecimal costBaseCost, BigDecimal costUnitCost, String costStatus, String costDescr, Date costCreattime, Date costStarttime, String costType) {
        this.costId = costId;
        this.costName = costName;
        this.costBaseDuration = costBaseDuration;
        this.costBaseCost = costBaseCost;
        this.costUnitCost = costUnitCost;
        this.costStatus = costStatus;
        this.costDescr = costDescr;
        this.costCreattime = costCreattime;
        this.costStarttime = costStarttime;
        this.costType = costType;
    }

    public Cost() {
        super();
    }

    public Integer getCostId() {
        return costId;
    }

    public void setCostId(Integer costId) {
        this.costId = costId;
    }

    public String getCostName() {
        return costName;
    }

    public void setCostName(String costName) {
        this.costName = costName;
    }

    public Integer getCostBaseDuration() {
        return costBaseDuration;
    }

    public void setCostBaseDuration(Integer costBaseDuration) {
        this.costBaseDuration = costBaseDuration;
    }

    public BigDecimal getCostBaseCost() {
        return costBaseCost;
    }

    public void setCostBaseCost(BigDecimal costBaseCost) {
        this.costBaseCost = costBaseCost;
    }

    public BigDecimal getCostUnitCost() {
        return costUnitCost;
    }

    public void setCostUnitCost(BigDecimal costUnitCost) {
        this.costUnitCost = costUnitCost;
    }

    public String getCostStatus() {
        return costStatus;
    }

    public void setCostStatus(String costStatus) {
        this.costStatus = costStatus;
    }

    public String getCostDescr() {
        return costDescr;
    }

    public void setCostDescr(String costDescr) {
        this.costDescr = costDescr;
    }

    public Date getCostCreattime() {
        return costCreattime;
    }

    public void setCostCreattime(Date costCreattime) {
        this.costCreattime = costCreattime;
    }

    public Date getCostStarttime() {
        return costStarttime;
    }

    public void setCostStarttime(Date costStarttime) {
        this.costStarttime = costStarttime;
    }

    public String getCostType() {
        return costType;
    }

    public void setCostType(String costType) {
        this.costType = costType;
    }
}