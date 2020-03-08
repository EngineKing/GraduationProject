package com.thesis.service;

import com.thesis.entity.Role;
import com.thesis.form.RoleForm;
import com.thesis.utils.Query;
import com.thesis.utils.R;
import com.thesis.vo.RoleVO;

import java.util.List;

/**
 * @Author LeiPeng
 * @Date 2020/1/15 - 20:36
 */
public interface RoleService {
    void add(Role role);

    void delete(Integer id);

    void update(Role role);

    List<RoleVO> pageQuery(RoleForm roleForm, Query query);

    int pageQueryCount(RoleForm roleForm, Query query);

    List<Role> getAll();
}
