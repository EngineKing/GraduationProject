package com.thesis.vo;

import com.thesis.entity.Department;
import com.thesis.entity.Subject;

/**
 * @Author LeiPeng
 * @Date 2020/1/23 - 14:31
 */
public class GradeVO {
    private Integer id;
    private String name;
    private Subject subject;
    private Department department;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
