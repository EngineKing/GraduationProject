package com.thesis.entity;

import lombok.ToString;

/**
 * @Author LeiPeng
 * @Date 2020/1/28 - 13:35
 */
@ToString
public class Student {
    private Integer id;
    private String number;
    private String name;
    private Integer gender;
    private String idCardNo;
    private Integer type;
    private String phone;
    private String email;
    private Long enrollmentDate;
    private Integer schoolSystem;
    private Integer classId;
    private Integer gradeId;
    private Integer subjectId;
    private Integer departmentId;
    private Integer teacherId;
    private Integer userId;
    private Integer status;

    public Student() {
    }

    public Student(String number, String name, Integer gender, String idCardNo,
                   Integer type, String phone, String email, Long enrollmentDate, Integer schoolSystem,
                   Integer classId, Integer gradeId, Integer subjectId, Integer departmentId, Integer userId, Integer status) {
        this.number = number;
        this.name = name;
        this.gender = gender;
        this.idCardNo = idCardNo;
        this.type = type;
        this.phone = phone;
        this.email = email;
        this.enrollmentDate = enrollmentDate;
        this.schoolSystem = schoolSystem;
        this.classId = classId;
        this.gradeId = gradeId;
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

    public Long getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(Long enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public Integer getSchoolSystem() {
        return schoolSystem;
    }

    public void setSchoolSystem(Integer schoolSystem) {
        this.schoolSystem = schoolSystem;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
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

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
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
