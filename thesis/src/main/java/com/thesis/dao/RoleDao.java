package com.thesis.dao;

import com.thesis.entity.Role;
import com.thesis.form.RoleForm;
import com.thesis.utils.Query;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author LeiPeng
 * @Date 2020/1/15 - 20:37
 */
public interface RoleDao {
    void add(Role role);

    Role get(@Param("roleId") Integer id);

    void delete(@Param("roleId") Integer id);

    Role getByName(@Param("roleName") String name);

    void update(Role role);

    List<Role> pageQuery(@Param("roleForm") RoleForm roleForm, @Param("query") Query query);

    int pageQueryCount(@Param("roleForm") RoleForm roleForm, @Param("query") Query query);

    List<Role> getAllRoles();
}
