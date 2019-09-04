package com.barista.service.impl;

import com.barista.dao.AdminInfoMapper;
import com.barista.entity.AdminInfo;
import com.barista.service.AdminService;
import com.barista.util.MD5Util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName AdminServiceImpl
 * @Author zhaoth
 * @Date 2019/8/21 15:43
 * @Version 1.0
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminInfoMapper adminInfoMapper;

    @Override
    public AdminInfo selectByAdminCode(String adminCode) {
        AdminInfo adminInfo = adminInfoMapper.selectByAdminCode(adminCode);
        return adminInfo;
    }

    @Override
    public Integer selectCount() {
        return adminInfoMapper.selectCount();
    }

    @Override
    public List<AdminInfo> selectPaging(Integer currentPage, Integer pageSize) {
        return adminInfoMapper.selectPaging((currentPage - 1) * pageSize, pageSize);
    }

    @Override
    public boolean checkPassword(String password, String dbPassword) {
        password = MD5Util.encode(password);
        if (StringUtils.equals(password, dbPassword)) {
            return true;
        }
        return false;
    }

    @Override
    public int changePassword(String adminCode, String password) {
        AdminInfo adminInfo = new AdminInfo();
        adminInfo.setAdminCode(adminCode);
        adminInfo.setAdminPassword(MD5Util.encode(password));
        return adminInfoMapper.updatePartInfoByAdminCodeSelective(adminInfo);
    }

    @Override
    public int updateAdminInfo(AdminInfo adminInfo) {
        return adminInfoMapper.updatePartInfoByAdminCodeSelective(adminInfo);
    }

    @Override
    public int resetPasswords(List<Integer> adminIds, List<String> passwords) {
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < adminIds.size(); i++) {
            Map<String,Object> map = new HashMap<>();
            map.put("id", adminIds.get(i));
            map.put("password", passwords.get(i));
            list.add(map);
        }
        return adminInfoMapper.resetPasswords(list);
    }
}
