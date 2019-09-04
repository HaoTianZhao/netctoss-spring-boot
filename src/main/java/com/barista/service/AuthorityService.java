package com.barista.service;

import java.util.List;
import java.util.Set;

/**
 * 权限服务
 *
 * @ClassName AuthorityServicea
 * @Author zhaoth
 * @Date 2019/8/21 10:38
 * @Version 1.0
 */
public interface AuthorityService {
    /**
     * 根据管理员id查找它具有的权限组id
     *
     * @param adminId 管理员id
     * @return java.util.List<java.lang.Integer>
     * @author zhaoth
     */
    List<Integer> selectPermissionGroupIds(Integer adminId);

    /**
     * 返回所有需要权限才能访问的路径
     *
     * @return java.util.Set<java.lang.String> 所有需要权限的路径
     * @author zhaoth
     */
    Set<String> selectAllPermission();

    /**
     * 根据管理员账号查找他可以访问的所有权限
     *
     * @param adminCode 管理员账号
     * @return java.util.Set<java.lang.String> 有权限访问的路径
     * @author zhaoth
     */
    Set<String> selectAllPermission(String adminCode);


}

