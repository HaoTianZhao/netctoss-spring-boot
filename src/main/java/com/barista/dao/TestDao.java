package com.barista.dao;

import com.barista.entity.Test;

import org.springframework.stereotype.Repository;

/**
 * TODO
 *
 * @ClassName TestDao
 * @Author zhaoth
 * @Date 2019/9/18 18:04
 * @Version 1.0
 */
@Repository
public interface TestDao {
    public Test selectOne(int adminId);
}
