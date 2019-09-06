package com.barista.dao;

import com.barista.entity.Bill;

import org.springframework.stereotype.Repository;

@Repository
public interface BillMapper {
    int deleteByPrimaryKey(Integer billId);

    int insert(Bill record);

    int insertSelective(Bill record);

    Bill selectByPrimaryKey(Integer billId);

    int updateByPrimaryKeySelective(Bill record);

    int updateByPrimaryKey(Bill record);
}