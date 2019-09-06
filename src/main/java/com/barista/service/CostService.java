package com.barista.service;

import com.barista.entity.Cost;

import java.util.List;

/**
 * 资费状态为1(暂停)时，才可以修改和删除
 * 新增的资费为暂停状态，需手动开启
 *
 * @ClassName CostService
 * @Author zhaoth
 * @Date 2019/8/28 15:13
 * @Version 1.0
 */
public interface CostService {

    List<Cost> selectByExistField(Cost cost);

    /**
     * 查询资费总数
     */
    Integer selectCount();

    /**
     * 分页查询
     *
     * @param currentPage    第几页，从0开始
     * @param pageSize 每页行数
     * @return java.util.List<com.barista.entity.Cost>
     * @author zhaoth
     */
    List<Cost> selectPaging(Integer currentPage, Integer pageSize);

    /**
     * 按指定字段指定顺序排列
     *
     * @param currentPage    第几页，从0开始
     * @param pageSize 每页行数
     * @param orderBy  如何排序
     * @return java.util.List<com.barista.entity.Cost>
     * @author zhaoth
     */
    List<Cost> selectPagingOrder(Integer currentPage, Integer pageSize, String orderBy);

    /**
     * 新增资费信息
     *
     * @param cost 资费信息
     * @return int
     * @author zhaoth
     */
    int insertCost(Cost cost);

    /**
     * 启用资费，将指定资费的启用字段置一
     *
     * @param costId 资费id
     * @return int
     * @author zhaoth
     */
    int startUsing(Integer costId);

    /**
     * 删除资费
     *
     * @param costId 资费id
     * @return int
     * @author zhaoth
     */
    int deleteCost(Integer costId);

    /**
     * 更改资费信息
     *
     * @param cost 资费信息
     * @return int
     * @author zhaoth
     */
    int updateCost(Cost cost);
}
