package com.barista.controller;

import com.barista.entity.AdminInfo;
import com.barista.interceptor.LoginInterceptor;
import com.barista.result.Result;
import com.barista.result.ResultCode;
import com.barista.service.AdminRoleService;
import com.barista.service.AdminService;
import com.barista.service.AuthorityService;
import com.barista.util.JwtUtil;
import com.barista.util.RedisUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.servlet.http.HttpSession;

import static com.barista.interceptor.AuthorityInterceptor.AUTH_KEY;
import static com.barista.interceptor.LoginInterceptor.USER_KEY;

/**
 * 登录和退出登录
 *
 * @ClassName LoginController
 * @Author zhaoth
 * @Date 2019/8/21 15:33
 * @Version 1.0
 */
@RestController
@RequestMapping("/")
public class LoginController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private AdminRoleService adminRoleService;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping("login")
    public Result login(AdminInfo admin, String checkCode, HttpSession session) {
        String adminCode = admin.getAdminCode();
        if (adminCode == null || admin.getAdminPassword() == null) {
            return Result.fail(ResultCode.ILLEGAL_PARAM);
        }
        AdminInfo dbAdmin = adminService.selectByAdminCode(adminCode);
        String sessionCode = Objects.toString(session.getAttribute("sessionCode"), "");

        //校验验证码
        if (!Objects.equals(checkCode, sessionCode)) {
            return Result.fail(ResultCode.CHECK_CODE_ERROR);
        }
        if (dbAdmin == null) {
            return Result.fail(ResultCode.PASSWORD_ERROR);
        }
        if (adminService.checkPassword(admin.getAdminPassword(), dbAdmin.getAdminPassword())) {
            String token = jwtUtil.generateToken(admin.getAdminCode());
            List<Integer> adminPrivileges = authorityService.selectPermissionGroupIds(dbAdmin.getAdminId());
            Set<Integer> set = new HashSet<>(adminPrivileges);
            session.setAttribute(USER_KEY, token);
            return Result.success(set, token);
        } else {
            return Result.fail(ResultCode.PASSWORD_ERROR);
        }

    }

    @RequestMapping("logout")
    public Result logout(HttpSession session) {
        String userName = (String) session.getAttribute(USER_KEY);
        redisUtil.del(AUTH_KEY + userName);
        session.removeAttribute("token");
        return Result.success("退出登录成功");
    }

}
