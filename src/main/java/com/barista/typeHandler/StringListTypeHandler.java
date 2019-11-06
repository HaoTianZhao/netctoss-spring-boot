package com.barista.typeHandler;

import com.sun.deploy.util.StringUtils;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 自定义mybatis字段类型转换
 * 将'1,2,3,4' 与 ['1','2','3','4']之间的转换
 *
 * @ClassName StringListTypeHandler
 * @Author zhaoth
 * @Date 2019/9/24 20:09
 * @Version 1.0
 */

@SuppressWarnings("all")
//这个注解定义的是JdbcType类型，这里的类型不可自己随意定义，必须要是枚举类org.apache.ibatis.type.JdbcType所枚举的数据类型
@MappedJdbcTypes({JdbcType.VARCHAR})
//这里定义的是JavaType的数据类型，描述了哪些Java类型可被拦截
@MappedTypes({List.class})
public class StringListTypeHandler extends BaseTypeHandler<List<String>> {
    private final static Logger logger = LoggerFactory.getLogger(StringListTypeHandler.class);

    public StringListTypeHandler() {
        logger.info("initialization StringListTypeHandler ok");
        System.out.println("initialization StringListTypeHandler ok");
    }

    /**
     * 用来设置查询参数
     */
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<String> parameter, JdbcType jdbcType) throws SQLException {
        //这个使用的是 commons.lang3 的包，用来添加 ',' 也可以直接 toString() 后去掉两端的 [] 即可
        ps.setString(i, StringUtils.join(parameter, ","));
    }

    /**
     * 用来将查询结果转换成指定实体类,依旧是返回结果的列名
     */
    @Override
    public List<String> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String str = rs.getString(columnName);
        if (rs.wasNull()) {
            return null;
        }
        //解决增加异常，这样做是因为直接放入时操作的是原数组，导致不能增删操作，所以要重新创建一个
        return new ArrayList<String>(Arrays.asList(str.split(",")));
    }

    /**
     * 用来将查询结果转换成指定实体类,依旧是返回结果列的顺序号
     */
    @Override
    public List<String> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String str = rs.getString(columnIndex);
        if (rs.wasNull()) {
            return null;
        }

        return new ArrayList<String>(Arrays.asList(str.split(",")));
    }

    /**
     * 用来将查询结果转换成指定实体类,依旧是返回结果列的顺序号,
     * 只是使用了不同的返回接收接口,写过原生jdbc的应该有印象
     */
    @Override
    public List<String> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String str = cs.getString(columnIndex);
        if (cs.wasNull()) {
            return null;
        }

        return new ArrayList<String>(Arrays.asList(str.split(",")));
    }
}
