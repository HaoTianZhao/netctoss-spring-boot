package com.barista.config;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import javax.sql.DataSource;

/**
 * service层全局事务
 *
 * @ClassName TransactionAdviceConfig
 * @Author zhaoth
 * @Date 2019/9/3 15:39
 * @Version 1.0
 */
@Aspect
@Configuration
public class TransactionAdviceConfig {

    private static final String AOP_POINTCUT_EXPRESSION = "execution(* com..*.service..*.*(..))";

    @Autowired
    private DataSource dataSource;

    //自己指定TransactionManager,实际上用spring-boot-jdbc-starter默认就是这个
    @Bean(name = "platformTransactionManager")
    public PlatformTransactionManager txManager() {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public TransactionInterceptor txAdvice() {

        DefaultTransactionAttribute txAttr_REQUIRED = new DefaultTransactionAttribute();
        txAttr_REQUIRED.setIsolationLevel(TransactionDefinition.ISOLATION_DEFAULT);
        txAttr_REQUIRED.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        DefaultTransactionAttribute txAttr_REQUIRED_READONLY = new DefaultTransactionAttribute();   //事务属性
        txAttr_REQUIRED_READONLY.setIsolationLevel(TransactionDefinition.ISOLATION_DEFAULT);        //设置隔离级别
        txAttr_REQUIRED_READONLY.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);//设置传播级别
        txAttr_REQUIRED_READONLY.setReadOnly(true);                                                 //设置只读

        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();     //匹配源：方法名匹配->事务属性
        source.addTransactionalMethod("insert*", txAttr_REQUIRED);
        source.addTransactionalMethod("add*", txAttr_REQUIRED);
        source.addTransactionalMethod("save*", txAttr_REQUIRED);
        source.addTransactionalMethod("delete*", txAttr_REQUIRED);
        source.addTransactionalMethod("update*", txAttr_REQUIRED);
        source.addTransactionalMethod("exec*", txAttr_REQUIRED);
        source.addTransactionalMethod("set*", txAttr_REQUIRED);

        source.addTransactionalMethod("get*", txAttr_REQUIRED_READONLY);
        source.addTransactionalMethod("query*", txAttr_REQUIRED_READONLY);
        source.addTransactionalMethod("find*", txAttr_REQUIRED_READONLY);
        source.addTransactionalMethod("list*", txAttr_REQUIRED_READONLY);
        source.addTransactionalMethod("count*", txAttr_REQUIRED_READONLY);
        source.addTransactionalMethod("is*", txAttr_REQUIRED_READONLY);

        source.addTransactionalMethod("*", txAttr_REQUIRED);
        return new TransactionInterceptor(txManager(), source);                              //将数据源和匹配源作为advice
    }

    @Bean
    public Advisor txAdviceAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();   //切入点
        pointcut.setExpression(AOP_POINTCUT_EXPRESSION);                        //设置切入点表达式
        return new DefaultPointcutAdvisor(pointcut, txAdvice());                //将切入点和advice组合为aspect
    }

}
