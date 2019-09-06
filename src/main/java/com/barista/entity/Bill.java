package com.barista.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class Bill implements Serializable {
    private Integer billId;

    private Integer accountId;

    private String billMonth;

    private BigDecimal cost;

    private String paymentMode;

    private String payState;

    private static final long serialVersionUID = 1L;

    public Bill(Integer billId, Integer accountId, String billMonth, BigDecimal cost, String paymentMode, String payState) {
        this.billId = billId;
        this.accountId = accountId;
        this.billMonth = billMonth;
        this.cost = cost;
        this.paymentMode = paymentMode;
        this.payState = payState;
    }

    public Bill() {
        super();
    }

    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getBillMonth() {
        return billMonth;
    }

    public void setBillMonth(String billMonth) {
        this.billMonth = billMonth;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getPayState() {
        return payState;
    }

    public void setPayState(String payState) {
        this.payState = payState;
    }
}