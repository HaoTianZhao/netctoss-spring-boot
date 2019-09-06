package com.barista.service;

import com.alibaba.fastjson.JSON;
import com.barista.dao.RolePrivilegeMapper;
import com.barista.entity.Role;
import com.barista.util.ValueUtil;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @ClassName RolePrivilegeServiceTest
 * @Author zhaoth
 * @Date 2019/8/24 13:56
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RolePrivilegeServiceTest {
    @Autowired
    private RolePrivilegeService rolePrivilegeService;

    @Autowired
    private RolePrivilegeMapper mapper;

    private Role role;
    private List<Integer> list;

    {
        role = new Role();
        role.setRoleId(20);
        role.setRoleName("赵天昊");
        list = new ArrayList<>(Arrays.asList(1, 2, 3));
        list.add(4);
    }

    @Test
    public void a_insertUpdateAndDelete() {
        System.out.println(rolePrivilegeService.insertRolePrivilege(role, list));
        //        selectAllRolePrivileges();
    }


    @Test
    public void b_updateRoleAndPrivileges() {
        //        role.setRoleName("haah");
        //        list.add(10);
        System.out.println(rolePrivilegeService.updateRoleAndPrivileges(role, list));
        //        selectAllRolePrivileges();
    }

    @Test
    public void c_deleteRole() {
        System.out.println(rolePrivilegeService.deleteRole(role.getRoleId()));
        //        selectAllRolePrivileges();
    }

    @Test
    public void selectAllRolePrivileges() {
        System.out.println(JSON.toJSONString(rolePrivilegeService.selectAllRolePrivileges(1,10)));
    }

    @Test
    public void test(){
        System.out.println(ValueUtil.haveNullOrBlack(new Integer(10), "", 10, null));
    }


}
