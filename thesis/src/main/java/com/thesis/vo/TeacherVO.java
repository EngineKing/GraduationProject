package com.thesis.vo;

import com.thesis.entity.Department;
import com.thesis.entity.Subject;

/**
 * @Author LeiPeng
 * @Date 2020/1/26 - 20:07
 */
public class TeacherVO {
    private Integer id;
    private String account; // 用户账号
    private String number;
    private String name;
    private Integer gender;
    private Integer age;
    private String phone;
    private String email;
    private Integer jobTitle;
    private String introduction;
    private Subject subject;
    private Department department;
    private Integer status;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(Integer jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
