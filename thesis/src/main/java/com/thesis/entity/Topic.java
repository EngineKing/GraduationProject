package com.thesis.entity;

/**
 * @Author LeiPeng
 * @Date 2020/1/25 - 22:17
 */
public class Topic {
    private Integer id;
    private String name;
    private String description;
    private Integer optionalNumber;
    private Integer selectedNumber;
    private Integer teacherId;
    private Integer taskId;

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

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }
}
