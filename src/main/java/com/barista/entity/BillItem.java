package com.barista.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class BillItem implements Serializable {
    private Integer itemId;

    private Integer billId;

    private Integer serviceId;

    private BigDecimal cost;

    private static final long serialVersionUID = 1L;

    public BillItem(Integer itemId, Integer billId, Integer serviceId, BigDecimal cost) {
        this.itemId = itemId;
        this.billId = billId;
        this.serviceId = serviceId;
        this.cost = cost;
    }

    public BillItem() {
        super();
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
}