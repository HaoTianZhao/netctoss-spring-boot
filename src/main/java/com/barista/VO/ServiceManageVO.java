package com.barista.VO;

import com.barista.entity.Account;
import com.barista.entity.Cost;
import com.barista.entity.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 包含业务账号管理的浏览，详情，修改所需信息
 *
 * @ClassName ServiceManageVO
 * @Author zhaoth
 * @Date 2019/8/30 20:23
 * @Version 1.0
 */
public class ServiceManageVO {
    private int totalCount;
    private List<ServiceColumn> serviceColumns;
    private List<Cost> allCost;//所有开通状态的资费

    public ServiceManageVO() {
    }

    public ServiceManageVO(int totalCount, List<Service> services, List<Account> accounts
            , List<Cost> costs, List<Cost> allCost) {
        this.totalCount = totalCount;
        serviceColumns = new ArrayList<>(services.size());
        for (int i = 0; i < services.size(); i++) {
            Service service = services.get(i);
            service.setLoginPasswd(null);
            Account account = accounts.get(i);
            account.setAccountLoginPasswd(null);
            Cost cost = costs.get(i);
            serviceColumns.add(new ServiceColumn(service, account, cost));
        }
        this.allCost = allCost.stream().filter(t -> t.getCostStatus().equals("0")).collect(Collectors.toList());
    }

    private class ServiceColumn {
        private Service service;
        private Account account;
        private Cost cost;

        public ServiceColumn() {
        }

        private ServiceColumn(Service service, Account account, Cost cost) {
            this.service = service;
            this.account = account;
            this.cost = cost;
        }

        public Service getService() {
            return service;
        }

        public void setService(Service service) {
            this.service = service;
        }

        public Account getAccount() {
            return account;
        }

        public void setAccount(Account account) {
            this.account = account;
        }

        public Cost getCost() {
            return cost;
        }

        public void setCost(Cost cost) {
            this.cost = cost;
        }
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<ServiceColumn> getServiceColumns() {
        return serviceColumns;
    }

    public void setServiceColumns(List<ServiceColumn> serviceColumns) {
        this.serviceColumns = serviceColumns;
    }

    public List<Cost> getAllCost() {
        return allCost;
    }

    public void setAllCost(List<Cost> allCost) {
        this.allCost = allCost;
    }
}
