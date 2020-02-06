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
    void addRole(Role role);

    Role getRoleById(@Param("roleId") Integer id);

    void deleteRoleById(@Param("roleId") Integer id);

    Role getRoleByName(@Param("roleName") String name);

    void updateRole(Role role);

    List<Role> pageQuery(@Param("roleForm") RoleForm roleForm, @Param("query") Query query);

    int pageQueryCount(@Param("roleForm") RoleForm roleForm, @Param("query") Query query);

    List<Role> getAllRoles();
}
