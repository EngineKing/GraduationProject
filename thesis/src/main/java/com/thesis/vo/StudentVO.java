package com.thesis.vo;

import com.thesis.entity.*;

/**
 * @Author LeiPeng
 * @Date 2020/1/28 - 14:11
 */
public class StudentVO {
    private Integer id;
    private String number;
    private String name;
    private Integer gender;
    private String nation;
    private String hometown;
    private String idCardNo;
    private Integer type;
    private String phone;
    private String email;
    private String position;
    private Integer enrollmentDate;
    private Integer schoolSystem;
    private TeachingClass teachingClass;
    private Grade grade;
    private Subject subject;
    private Department department;
    private Teacher teacher;
    private User user;

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

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(Integer enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public Integer getSchoolSystem() {
        return schoolSystem;
    }

    public void setSchoolSystem(Integer schoolSystem) {
        this.schoolSystem = schoolSystem;
    }

    public TeachingClass getTeachingClass() {
        return teachingClass;
    }

    public void setTeachingClass(TeachingClass teachingClass) {
        this.teachingClass = teachingClass;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
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

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
