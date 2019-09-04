package com.barista.dao;

import com.barista.entity.Privilege;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegeMapper {
    int deleteByPrimaryKey(@Param("privilegeGroupId") Integer privilegeGroupId, @Param("privilegeId") Integer privilegeId);

    int insert(Privilege record);

    int insertSelective(Privilege record);

    Privilege selectByPrimaryKey(@Param("privilegeGroupId") Integer privilegeGroupId, @Param("privilegeId") Integer privilegeId);

    int updateByPrimaryKeySelective(Privilege record);

    int updateByPrimaryKey(Privilege record);
}