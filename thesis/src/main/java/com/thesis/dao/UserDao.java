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
    void addUser(User user);

    User getUserById(@Param("userId") Integer id);

    List<User> getUsersByDepartmentId(@Param("departmentId") Integer id);

    List<User> getUsersByRoleId(@Param("roleId") Integer id);

    User getUserByAccount(@Param("account") String account);

    void deleteUserById(@Param("userId") Integer id);

    void updateUser(User user);

    List<User> pageQuery(@Param("userForm") UserForm userForm, @Param("query") Query query);

    List<Integer> getRoleIdsByUserId(@Param("userId") Integer id);

    int pageQueryCount(@Param("userForm") UserForm userForm, @Param("query") Query query);
}
