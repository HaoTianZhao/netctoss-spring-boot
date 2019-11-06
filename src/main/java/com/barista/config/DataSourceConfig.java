package com.barista.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

import org.apache.ibatis.session.AutoMappingBehavior;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

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

    @Value("${mybatis.type-aliases-package}")
    private String typeAliasesPackage;

    @Value("${mybatis.mapper-locations}")
    private String mapperLocations;

    @Bean
    public SqlSessionFactory mybatis(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();

        //设置数据源
        sqlSessionFactoryBean.setDataSource(dataSource);

        PathMatchingResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        //设置实体类的位置
        sqlSessionFactoryBean.setTypeAliasesPackage(typeAliasesPackage);

        //读配置文件中的mapper位置,并配置进去
        sqlSessionFactoryBean.setMapperLocations(resourcePatternResolver.getResources(mapperLocations));

        //1.mybatis配置类,如果使用tk.mybatis.mapper,要改成tk开头的Configuration:
        //        tk.apache.ibatis.session.Configuration configuration;
        //2.设置MapperHelper:
        //        configuration.setMapperHelper(new MapperHelper());
        //3.解决查询返回Map时设置值为null的字段没有在结果集中
        //        configuration.setCallSettersOnNulls(true);
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setAutoMappingBehavior(AutoMappingBehavior.FULL);//配置所有resultMap为autoMapping,自动映射复杂类型
        configuration.setMapUnderscoreToCamelCase(true);//开启下划线和驼峰风格的转换
        sqlSessionFactoryBean.setConfiguration(configuration);

        return sqlSessionFactoryBean.getObject();
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
