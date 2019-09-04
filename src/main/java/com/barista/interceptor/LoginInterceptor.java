package com.barista.interceptor;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.barista.result.Result;
import com.barista.result.ResultCode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("访问" + request.getRequestURL());

        //todo 用SHA私钥解密，与session比较是否相同，验证时间戳是否过期，并获得用户名
        String token = (String) request.getSession().getAttribute("token");

        //用户未登录
        if (StringUtils.isEmpty(token) || !token.equals(request.getSession().getAttribute("token"))) {
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
        return true;
    }

}
