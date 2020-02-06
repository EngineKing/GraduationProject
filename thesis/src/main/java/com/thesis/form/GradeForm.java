package com.thesis.form;

/**
 * @Author LeiPeng
 * @Date 2020/1/23 - 14:30
 */
public class GradeForm {
    private String name;
    private Integer subjectId;
    private Integer departmentId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
