package com.barista.interceptor;

import com.alibaba.fastjson.JSON;
import com.barista.result.Result;
import com.barista.result.ResultCode;
import com.barista.util.JwtUtil;
import com.barista.util.RedisUtil;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.jsonwebtoken.Claims;

/**
 * 修改拦截器的字符编码，不然返回的json中文乱码
 *
 * @ClassName LoginInterceptor
 * @Author zhaoth
 * @Date 2019/8/17 17:49
 * @Version 1.0
 */
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {
    private Logger logger = LoggerFactory.getLogger(AuthorityInterceptor.class);

    public static final String USER_KEY = "userName";

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("访问" + request.getRequestURL());

        //优先从请求头获取token，其次从请求参数获取
        String token = request.getHeader(jwtUtil.getHeader());
        if (StringUtils.isEmpty(token)) {
            token = request.getParameter(jwtUtil.getHeader());
        }

        Claims claim;
        String userName = null;
        if (StringUtils.isEmpty(token)) {
            userName = (String) request.getSession().getAttribute(LoginInterceptor.USER_KEY);
        }else{
            claim = jwtUtil.getClaimByToken(token);
            userName = claim.getSubject();
        }

        //用户未登录
        if (StringUtils.isEmpty(userName)) {
            logger.debug("未登录，不可以访问" + request.getRequestURL());
            //这是访问页面时的重定向
            if (request.getRequestURL().toString().contains(".html")) {
                response.sendRedirect(request.getContextPath() + "/page/login.html");
            } else {
                //这是访问接口时返回json的未登录信息
                response.setContentType("application/json;charset=UTF-8");
                String json = JSON.toJSONString(Result.fail(ResultCode.NEED_LOGIN));
                response.getWriter().append(json);
            }
            return false;
        }
        request.setAttribute(USER_KEY, userName);
        request.getSession().setAttribute(USER_KEY, userName);
        return true;
    }

}
