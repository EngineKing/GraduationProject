package com.thesis.vo;

import com.thesis.entity.Department;

/**
 * @Author LeiPeng
 * @Date 2020/1/15 - 16:55
 */
public class DepartmentVO {
    private int id;
    private String name;
    private Department pDept;
    private String description;
    private Integer status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getpDept() {
        return pDept;
    }

    public void setpDept(Department pDept) {
        this.pDept = pDept;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
