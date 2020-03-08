package com.thesis.service;

import com.thesis.form.UserRoleForm;

import java.util.List;

/**
 * @Author LeiPeng
 * @Date 2020/2/9 - 16:34
 */
public interface UserRoleService {
    List<Integer> getByUserId(Integer id);

    void assign(UserRoleForm userRoleForm);
}
