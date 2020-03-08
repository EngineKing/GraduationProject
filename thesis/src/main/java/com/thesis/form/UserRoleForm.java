package com.thesis.form;

/**
 * @Author LeiPeng
 * @Date 2020/2/10 - 10:39
 */
public class UserRoleForm {
    private Integer userId;
    private Integer[] roleIds;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer[] getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(Integer[] roleIds) {
        this.roleIds = roleIds;
    }
}
