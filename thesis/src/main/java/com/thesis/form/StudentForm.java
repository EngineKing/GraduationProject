package com.thesis.form;

/**
 * @Author LeiPeng
 * @Date 2020/1/28 - 14:07
 */
public class StudentForm {
    private String number;
    private String name;
    private Integer gender;
    private Integer type;
    private Integer enrollmentDate;
    private Integer classId;
    private Integer teacherId;

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(Integer enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }
}
