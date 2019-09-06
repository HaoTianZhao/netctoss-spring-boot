package com.barista.dao;

import com.barista.entity.BillItem;

import org.springframework.stereotype.Repository;

@Repository
public interface BillItemMapper {
    int deleteByPrimaryKey(Integer itemId);

    int insert(BillItem record);

    int insertSelective(BillItem record);

    BillItem selectByPrimaryKey(Integer itemId);

    int updateByPrimaryKeySelective(BillItem record);

    int updateByPrimaryKey(BillItem record);
}