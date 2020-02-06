package com.thesis.vo;

import com.thesis.entity.Teacher;

/**
 * @Author LeiPeng
 * @Date 2020/1/27 - 17:22
 */
public class ExpertGroupVO {
    private Integer id;
    private String name;
    private Teacher teacher;

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

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
