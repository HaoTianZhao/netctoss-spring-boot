package com.barista.dao;

import com.barista.entity.AdminInfo;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Map;

@Repository
public interface AdminInfoMapper {
    int deleteByPrimaryKey(Integer adminId);

    //    int insert(AdminInfo record);

    int insertSelective(AdminInfo record) ;

    //    AdminInfo selectByPrimaryKey(Integer adminId);

    AdminInfo selectByAdminCode(String adminCode);

    int selectCount();

    List<AdminInfo> selectPaging(@Param("begin") Integer begin, @Param("pageSize") Integer pageSize);

    int updateByPrimaryKeySelective(AdminInfo record) ;

    /**
     * 只修改密码，用户名，电话，邮箱
     *
     * @param record new空对象，只放入要修改的值和adminCode作为where条件
     * @return int
     * @author zhaoth
     */
    int updatePartInfoByAdminCodeSelective(AdminInfo record);

    //    int updateByPrimaryKey(AdminInfo record) ;

    /**
     * 一一对应地重置密码为传入值
     *
     * @param list Map中的键为id和password
     * @return int
     * @author zhaoth
     */
    int resetPasswords(List<Map<String, Object>> list);
}