package com.barista.service.impl;

import com.barista.dao.CostMapper;
import com.barista.entity.Cost;
import com.barista.service.CostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

/**
 * @ClassName CostServiceImpl
 * @Author zhaoth
 * @Date 2019/8/28 15:14
 * @Version 1.0
 */
@Service
public class CostServiceImpl implements CostService {
    @Autowired
    private CostMapper costMapper;

    @Override
    public List<Cost> selectByExistField(Cost cost) {
        return costMapper.selectByExistField(cost);
    }

    @Override
    public Integer selectCount() {
        return costMapper.selectCount();
    }

    @Override
    public List<Cost> selectPaging(Integer currentPage, Integer pageSize) {
        return costMapper.selectPaging((currentPage - 1) * pageSize, pageSize);
    }

    @Override
    public List<Cost> selectPagingOrder(Integer currentPage, Integer pageSize, String orderBy) {
        return costMapper.selectPagingOrder((currentPage - 1) * pageSize, pageSize, orderBy);
    }

    @Override
    public int insertCost(Cost cost) {
        return costMapper.insertSelective(cost);
    }

    @Override
    public int startUsing(Integer costId) {
        return costMapper.startUsing(costId);
    }

    @Override
    public int deleteCost(Integer costId) {
        return costMapper.deleteByPrimaryKey(costId);
    }

    @Override
    public int updateCost(Cost cost) {
        return costMapper.updateByPrimaryKeySelective(cost);
    }
}
