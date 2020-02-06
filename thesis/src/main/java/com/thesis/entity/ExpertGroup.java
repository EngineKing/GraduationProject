package com.thesis.entity;

/**
 * @Author LeiPeng
 * @Date 2020/1/27 - 17:17
 */
public class ExpertGroup {
    private Integer id;
    private String name;
    private Integer teacherId;

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

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }
}
