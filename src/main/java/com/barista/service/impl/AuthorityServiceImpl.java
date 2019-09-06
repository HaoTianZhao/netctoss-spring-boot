package com.barista.service.impl;

import com.barista.dao.AuthorityMapper;
import com.barista.service.AuthorityService;
import com.barista.util.Const;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 权限
 * 角色权限有重叠部分，所以要去重
 *
 * @ClassName AuthorityService
 * @Author zhaoth
 * @Date 2019/8/17 15:51
 * @Version 1.0
 */
@Service
public class AuthorityServiceImpl implements AuthorityService {
    @Autowired
    private AuthorityMapper authorityMapper;

    //返回管理员具有的权限id，前端控制视图显示
    @Override
    public List<Integer> selectPermissionGroupIds(Integer adminId) {
        return authorityMapper.selectPermissionGroupIds(adminId);
    }

    @Override
    public Set<String> selectAllPermission() {
        Set<String> set = authorityMapper.selectAllPermission().stream()
                .collect(HashSet::new, (a, b) -> a.add(Const.URL_PREFIX + b), HashSet::addAll);
        return set;
    }

    @Override
    public Set<String> selectAllPermission(String adminCode) {
        Set<String> set = authorityMapper.selectAll(adminCode).stream()
                .collect(HashSet::new, (a, b) -> a.add(Const.URL_PREFIX + b), HashSet::addAll);
        return set;
    }

}
