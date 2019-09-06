package com.barista.VO;

import com.barista.entity.Account;

import java.util.List;

/**
 * @ClassName AccountManageVO
 * @Author zhaoth
 * @Date 2019/8/29 18:29
 * @Version 1.0
 */
public class AccountManageVO {
    private int totalCount;
    private List<Account> accountColumns;

    public AccountManageVO() {
    }

    public AccountManageVO(int totalCount, List<Account> accountColumns) {
        for (Account accountColumn : accountColumns) {
            accountColumn.setAccountLoginPasswd(null);
        }
        this.totalCount = totalCount;
        this.accountColumns = accountColumns;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<Account> getAccountColumns() {
        return accountColumns;
    }

    public void setAccountColumns(List<Account> accountColumns) {
        this.accountColumns = accountColumns;
    }
}
