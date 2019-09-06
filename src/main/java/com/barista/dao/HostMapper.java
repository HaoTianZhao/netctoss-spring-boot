package com.barista.dao;

import com.barista.entity.Host;

import org.springframework.stereotype.Repository;

@Repository
public interface HostMapper {
    int deleteByPrimaryKey(String hostId);

    int insert(Host record);

    int insertSelective(Host record);

    Host selectByPrimaryKey(String hostId);

    int updateByPrimaryKeySelective(Host record);

    int updateByPrimaryKey(Host record);
}