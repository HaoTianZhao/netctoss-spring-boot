package com.barista.dao;

import com.barista.entity.Service;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Repository
public interface ServiceMapper {
    Service selectByPrimaryKey(Integer serviceId);

    List<Service> selectByExistField(Service service);

    int selectCount();

    List<Service> selectPaging(@Param("begin") Integer begin, @Param("pageSize") Integer pageSize);

    int selectCountFilter(@Param("osUsername") String osUsername, @Param("unixHost") String unixHost
            , @Param("accountIdcardNo") String accountIdcardNo, @Param("serviceStatus") String serviceStatus);

    List<Service> selectPagingFilter(@Param("begin") Integer begin, @Param("pageSize") Integer pageSize
            , @Param("osUsername") String osUsername, @Param("unixHost") String unixHost
            , @Param("accountIdcardNo") String accountIdcardNo, @Param("serviceStatus") String serviceStatus);

    int insertSelective(Service record);

    int startUsing(Integer serviceId);

    int pauseUsing(Integer serviceId);

    int pauseAllByAccountId(Integer accountId);

    int deleteByPrimaryKey(Integer serviceId);

    int deleteByAccountId(Integer accountId);

    //给定时任务使用。修改资费之后又删除了账号，还插入吗？我觉得不插入
    int updateAllById(List<Service> serviceList);

    int updateByPrimaryKeySelective(Service record) ;
}