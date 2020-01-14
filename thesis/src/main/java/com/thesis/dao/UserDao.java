package com.thesis.dao;

import com.thesis.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * @Author LeiPeng
 * @Date 2020/1/13 - 22:14
 */
public interface UserDao {
    void addUser(User user);

    User getUserById(@Param("userId") Integer id);
}
