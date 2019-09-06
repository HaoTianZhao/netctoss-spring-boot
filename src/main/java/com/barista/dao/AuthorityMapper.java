package com.barista.dao;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 根据管理员账号查询其具有的所有权限
 *
 * @ClassName AuthorityMapper
 * @Author zhaoth
 * @Date 2019/8/17 15:26
 * @Version 1.0
 */

@Repository
public interface AuthorityMapper {
    List<String> selectAllPermission();

    List<String> selectAll(String adminCode);

    List<Integer> selectPermissionGroupIds(Integer adminId);
}
