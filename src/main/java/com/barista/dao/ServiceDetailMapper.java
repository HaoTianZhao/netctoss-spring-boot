package com.barista.dao;

import com.barista.entity.ServiceDetail;

import org.springframework.stereotype.Repository;

@Repository
public interface ServiceDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ServiceDetail record);

    int insertSelective(ServiceDetail record);

    ServiceDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ServiceDetail record);

    int updateByPrimaryKey(ServiceDetail record);
}