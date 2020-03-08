package com.thesis.entity;

import lombok.ToString;

/**
 * @Author LeiPeng
 * @Date 2020/1/26 - 10:55
 */
@ToString
public class Teacher {
    private Integer id;
    private String number;
    private String name;
    private Integer gender;
    private Integer age;
    private String phone;
    private String email;
    private Integer jobTitle;
    private String introduction;
    private Integer subjectId;
    private Integer departmentId;
    private Integer userId;
    private Integer status;

    public Teacher() {
    }

    public Teacher(String name) {
        this.name = name;
    }

    public Teacher(String number, String name, Integer gender, Integer age, String phone, String email,
                   Integer jobTitle, String introduction, Integer subjectId, Integer departmentId, Integer userId,
                   Integer status) {
        this.number = number;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.phone = phone;
        this.email = email;
        this.jobTitle = jobTitle;
        this.introduction = introduction;
        this.subjectId = subjectId;
        this.departmentId = departmentId;
        this.userId = userId;
        this.status = status;
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

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
