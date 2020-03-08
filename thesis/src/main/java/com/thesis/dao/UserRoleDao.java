package com.thesis.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author LeiPeng
 * @Date 2020/2/8 - 15:48
 */
public interface UserRoleDao {
    List<Integer> getByUserId(@Param("userId") Integer id);

    List<Integer> getByRoleId(@Param("roleId") Integer id);

    void add(@Param("userId") Integer userId, @Param("roleId") Integer roleId);

    void delete(@Param("userId") Integer userId, @Param("roleId") Integer roleId);
}
