package com.thesis.vo;

import com.thesis.entity.Task;
import com.thesis.entity.Teacher;

/**
 * @Author LeiPeng
 * @Date 2020/1/25 - 22:47
 */
public class TopicVO {
    private Integer id;
    private String name;
    private String description;
    private Integer optionalNumber;
    private Integer selectedNumber;
    private Teacher teacher;
    private Task task;
    private Integer status;

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

    public Integer getOptionalNumber() {
        return optionalNumber;
    }

    public void setOptionalNumber(Integer optionalNumber) {
        this.optionalNumber = optionalNumber;
    }

    public Integer getSelectedNumber() {
        return selectedNumber;
    }

    public void setSelectedNumber(Integer selectedNumber) {
        this.selectedNumber = selectedNumber;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
