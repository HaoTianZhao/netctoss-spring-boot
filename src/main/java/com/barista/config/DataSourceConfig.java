package com.barista.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * 数据源
 *
 * @ClassName DataSourceConfig
 * @Author zhaoth
 * @Date 2019/9/3 10:56
 * @Version 1.0
 */
@Configuration
@MapperScan("com.barista.dao")
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druidDataSource() {
        DruidDataSource datasource = new DruidDataSource();
        return datasource;
    }

    @Bean
    public ServletRegistrationBean<StatViewServlet> statViewServlet() {
        //创建servlet注册实体
        ServletRegistrationBean<StatViewServlet> servletRegistrationBean
                = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        //设置ip白名单
        servletRegistrationBean.addInitParameter("allow", "127.0.0.1");
        //设置ip黑名单
        servletRegistrationBean.addInitParameter("deny", "192.168.0.2");
        //设置控制台管理用户__登录用户名和密码
        servletRegistrationBean.addInitParameter("loginUsername", "druid");
        servletRegistrationBean.addInitParameter("loginPassword", "123123");
        //是否可以重置数据
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<WebStatFilter> statFilter() {
        //创建过滤器
        FilterRegistrationBean<WebStatFilter> filterRegistrationBean
                = new FilterRegistrationBean<>(new WebStatFilter());
        //设置过滤器过滤路径
        filterRegistrationBean.addUrlPatterns("/*");
        //忽略过滤的形式
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.jpg,*.gif,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }
}
