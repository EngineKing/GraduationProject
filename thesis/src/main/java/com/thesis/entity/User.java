package com.thesis.entity;

/**
 * @Author LeiPeng
 * @Date 2020/1/13 - 21:57
 */
public class User {
    private Integer id;
    private String account;
    private String password;
    private Integer gender;
    private String email;
    private String phone;
    private Long createTime;
    private Long updateTime;
    private Integer departmentId;
    private Integer loginTimes;
    private Integer status;

    public User() {
    }

    public User(String account, String password, Integer gender, String email, String phone,
                Long createTime, Long updateTime, Integer departmentId, Integer loginTimes, Integer status) {
        this.account = account;
        this.password = password;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.departmentId = departmentId;
        this.loginTimes = loginTimes;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getLoginTimes() {
        return loginTimes;
    }

    public void setLoginTimes(Integer loginTimes) {
        this.loginTimes = loginTimes;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }
}
