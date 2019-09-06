package com.barista.entity;

import com.alibaba.fastjson.annotation.JSONField;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class Account implements Serializable {
    private Integer accountId;

    private Integer accountRecommenderId;

    private String accountLoginName;

    private String accountLoginPasswd;

    private String accountStatus;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date accountCreateDate;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date accountPauseDate;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date accountCloseDate;

    private String accountRealName;

    private String accountIdcardNo;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date accountBirthdate;

    private String accountGender;

    private String accountOccupation;

    private String accountTelephone;

    private String accountEmail;

    private String accountMailAddress;

    private String accountZipcode;

    private String accountQq;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date accountLastLoginTime;

    private String accountLastLoginIp;

    private static final long serialVersionUID = 1L;

    public Account(Integer accountId, Integer accountRecommenderId, String accountLoginName, String accountLoginPasswd, String accountStatus, Date accountCreateDate, Date accountPauseDate, Date accountCloseDate, String accountRealName, String accountIdcardNo, Date accountBirthdate, String accountGender, String accountOccupation, String accountTelephone, String accountEmail, String accountMailAddress, String accountZipcode, String accountQq, Date accountLastLoginTime, String accountLastLoginIp) {
        this.accountId = accountId;
        this.accountRecommenderId = accountRecommenderId;
        this.accountLoginName = accountLoginName;
        this.accountLoginPasswd = accountLoginPasswd;
        this.accountStatus = accountStatus;
        this.accountCreateDate = accountCreateDate;
        this.accountPauseDate = accountPauseDate;
        this.accountCloseDate = accountCloseDate;
        this.accountRealName = accountRealName;
        this.accountIdcardNo = accountIdcardNo;
        this.accountBirthdate = accountBirthdate;
        this.accountGender = accountGender;
        this.accountOccupation = accountOccupation;
        this.accountTelephone = accountTelephone;
        this.accountEmail = accountEmail;
        this.accountMailAddress = accountMailAddress;
        this.accountZipcode = accountZipcode;
        this.accountQq = accountQq;
        this.accountLastLoginTime = accountLastLoginTime;
        this.accountLastLoginIp = accountLastLoginIp;
    }

    public Account() {
        super();
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getAccountRecommenderId() {
        return accountRecommenderId;
    }

    public void setAccountRecommenderId(Integer accountRecommenderId) {
        this.accountRecommenderId = accountRecommenderId;
    }

    public String getAccountLoginName() {
        return accountLoginName;
    }

    public void setAccountLoginName(String accountLoginName) {
        this.accountLoginName = accountLoginName;
    }

    public String getAccountLoginPasswd() {
        return accountLoginPasswd;
    }

    public void setAccountLoginPasswd(String accountLoginPasswd) {
        this.accountLoginPasswd = accountLoginPasswd;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public Date getAccountCreateDate() {
        return accountCreateDate;
    }

    public void setAccountCreateDate(Date accountCreateDate) {
        this.accountCreateDate = accountCreateDate;
    }

    public Date getAccountPauseDate() {
        return accountPauseDate;
    }

    public void setAccountPauseDate(Date accountPauseDate) {
        this.accountPauseDate = accountPauseDate;
    }

    public Date getAccountCloseDate() {
        return accountCloseDate;
    }

    public void setAccountCloseDate(Date accountCloseDate) {
        this.accountCloseDate = accountCloseDate;
    }

    public String getAccountRealName() {
        return accountRealName;
    }

    public void setAccountRealName(String accountRealName) {
        this.accountRealName = accountRealName;
    }

    public String getAccountIdcardNo() {
        return accountIdcardNo;
    }

    public void setAccountIdcardNo(String accountIdcardNo) {
        this.accountIdcardNo = accountIdcardNo;
    }

    public Date getAccountBirthdate() {
        return accountBirthdate;
    }

    public void setAccountBirthdate(Date accountBirthdate) {
        this.accountBirthdate = accountBirthdate;
    }

    public String getAccountGender() {
        return accountGender;
    }

    public void setAccountGender(String accountGender) {
        this.accountGender = accountGender;
    }

    public String getAccountOccupation() {
        return accountOccupation;
    }

    public void setAccountOccupation(String accountOccupation) {
        this.accountOccupation = accountOccupation;
    }

    public String getAccountTelephone() {
        return accountTelephone;
    }

    public void setAccountTelephone(String accountTelephone) {
        this.accountTelephone = accountTelephone;
    }

    public String getAccountEmail() {
        return accountEmail;
    }

    public void setAccountEmail(String accountEmail) {
        this.accountEmail = accountEmail;
    }

    public String getAccountMailAddress() {
        return accountMailAddress;
    }

    public void setAccountMailAddress(String accountMailAddress) {
        this.accountMailAddress = accountMailAddress;
    }

    public String getAccountZipcode() {
        return accountZipcode;
    }

    public void setAccountZipcode(String accountZipcode) {
        this.accountZipcode = accountZipcode;
    }

    public String getAccountQq() {
        return accountQq;
    }

    public void setAccountQq(String accountQq) {
        this.accountQq = accountQq;
    }

    public Date getAccountLastLoginTime() {
        return accountLastLoginTime;
    }

    public void setAccountLastLoginTime(Date accountLastLoginTime) {
        this.accountLastLoginTime = accountLastLoginTime;
    }

    public String getAccountLastLoginIp() {
        return accountLastLoginIp;
    }

    public void setAccountLastLoginIp(String accountLastLoginIp) {
        this.accountLastLoginIp = accountLastLoginIp;
    }
}