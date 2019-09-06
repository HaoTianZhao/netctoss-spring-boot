package com.barista.config;

import com.barista.controller.LoginController;
import com.barista.interceptor.AuthorityInterceptor;
import com.barista.interceptor.LoginInterceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * MVC配置
 *
 * @ClassName WebMvcConfig
 * @Author zhaoth
 * @Date 2019/9/3 17:21
 * @Version 1.0
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {


    @Autowired
    private LoginInterceptor loginInterceptor;

    @Autowired
    AuthorityInterceptor authorityInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/page/login.html", "/images/checkCode", "/login")  //登录页
                .excludePathPatterns("/page/images/**", "/page/styles/**", "/page/js/**", "/page/component/**", "/error");//静态资源

        registry.addInterceptor(authorityInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/page/login.html", "/images/checkCode", "/login")  //登录页
                .excludePathPatterns("/page/images/**", "/page/styles/**", "/page/js/**", "/page/component/**") //静态资源
                .excludePathPatterns("/page/index.html", "/page/user/**", "/admin/getAdminInfo", "/admin/updateAdminInfo", "/admin/changePassword") //首页，个人信息，修改个人密码
                .excludePathPatterns("/error", "/page/error.html", "/page/nopower.html");//错误界面
    }


}
