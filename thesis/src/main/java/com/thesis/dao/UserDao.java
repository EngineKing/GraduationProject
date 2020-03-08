package com.thesis.dao;

import com.thesis.entity.User;
import com.thesis.form.UserForm;
import com.thesis.utils.Query;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author LeiPeng
 * @Date 2020/1/13 - 22:14
 */
public interface UserDao {
    void add(User user);

    User get(@Param("userId") Integer id);

    List<User> getUsersByDepartmentId(@Param("departmentId") Integer id);

    List<User> getUsersByRoleId(@Param("roleId") Integer id);

    User getUserByAccount(@Param("account") String account);

    void delete(@Param("userId") Integer id);

    void update(User user);

    List<User> pageQuery(@Param("userForm") UserForm userForm, @Param("query") Query query);

    int pageQueryCount(@Param("userForm") UserForm userForm, @Param("query") Query query);
}
