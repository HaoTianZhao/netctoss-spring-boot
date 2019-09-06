package com.barista.interceptor;

import com.alibaba.fastjson.JSON;
import com.barista.result.Result;
import com.barista.result.ResultCode;
import com.barista.service.AuthorityService;
import com.barista.util.JwtUtil;
import com.barista.util.RedisUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录及权限校验
 *
 * @ClassName AuthorityInterceptor
 * @Author zhaoth
 * @Date 2019/8/16 18:44
 * @Version 1.0
 */

@Component
public class AuthorityInterceptor extends HandlerInterceptorAdapter {
    private Logger logger = LoggerFactory.getLogger(AuthorityInterceptor.class);
    public static final String AUTH_KEY = "authority";

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.debug("访问进入权限拦截器" + request.getRequestURL());

/*
          权限验证，有两种实现方式。
          一、用户登录时查表，把所有权限路径保存在session，每个请求都拦截，查是否有权限访问。
          二、自定义注解，加在controller类或方法上，拦截每个请求，根据注解中的对角色的要求查询当前用户是否有权限。
*/
        //从redis或数据库中获取权限，检查请求路径是否在权限中
        String userName = (String) request.getAttribute(LoginInterceptor.USER_KEY);
        if (Objects.isNull(userName)) {
            userName = (String) request.getSession().getAttribute(LoginInterceptor.USER_KEY);
        }
        String url = request.getRequestURL().toString();
        if (Objects.nonNull(userName)) {
            Set<String> authority = (Set<String>) redisUtil.get(AUTH_KEY + userName);
            if (Objects.isNull(authority)) {
                authority = authorityService.selectAllPermission(userName);
                redisUtil.set(AUTH_KEY + userName, authority);
            }
            //有权限访问
            for (String s : authority) {
                if (url.matches(s)) {
                    return true;
                }
            }
        }
        logger.debug(userName + "访问" + url + "失败，没有权限。");
        if (request.getRequestURL().toString().contains(".html")) {
            response.sendRedirect("/page/nopower.html");
        } else {
            response.setContentType("application/json;charset=UTF-8");
            String json = JSON.toJSONString(Result.fail(ResultCode.NO_PERMISSION));
            response.getWriter().append(json);
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            Annotation annotation = handlerMethod.getMethodAnnotation(RequestMapping.class);
            MethodParameter[] parameters = handlerMethod.getMethodParameters();
            Class clazz = handlerMethod.getBeanType();
            Object object = handlerMethod.getBean();
            Object castObject = clazz.cast(object);
        }
        int i = 0;
    }


}
