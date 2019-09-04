package com.barista.VO;

import com.barista.entity.Cost;

import java.util.List;

/**
 * 资费管理页面
 *
 * @ClassName CostManageVO
 * @Author zhaoth
 * @Date 2019/8/28 15:36
 * @Version 1.0
 */
public class CostManageVO {
    private int totalCount;
    private List<Cost> costColumns;

    public CostManageVO() {
    }

    public CostManageVO(int totalCount,List<Cost> costColumns) {
        this.totalCount = totalCount;
        this.costColumns = costColumns;
    }

    public List<Cost> getCostColumns() {
        return costColumns;
    }

    public void setCostColumns(List<Cost> costColumns) {
        this.costColumns = costColumns;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}
