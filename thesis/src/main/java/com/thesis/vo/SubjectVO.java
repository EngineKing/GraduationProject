package com.thesis.vo;

import com.thesis.entity.Department;

import java.util.List;

/**
 * @Author LeiPeng
 * @Date 2020/1/23 - 9:28
 */
public class SubjectVO {
    private Integer id;
    private String name;
    private String description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
