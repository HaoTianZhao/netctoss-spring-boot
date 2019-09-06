package com.barista.dao;

import com.barista.entity.ServiceUpdate;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ServiceUpdateMapper {

    int updateOrInsertSelective(ServiceUpdate record);

    List<ServiceUpdate> selectPaging(@Param("id") Integer id, @Param("pageSize") Integer pageSize, @Param("timePoint") Date timePoint);

    int deleteUsedUpdateInfo(Integer lastId);


    //    int deleteByPrimaryKey(Integer id);
    //
    //    int insert(ServiceUpdate record);
    //
    //    int insertSelective(ServiceUpdate record);

    //    ServiceUpdate selectByPrimaryKey(Integer id);
    //
    //    int updateByPrimaryKeySelective(ServiceUpdate record);
    //
    //    int updateByPrimaryKey(ServiceUpdate record);
}