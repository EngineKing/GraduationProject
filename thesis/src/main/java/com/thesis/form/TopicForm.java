package com.thesis.form;

/**
 * @Author LeiPeng
 * @Date 2020/1/25 - 22:20
 */
public class TopicForm {
    private String name;
    private Integer teacherId;
    private Integer taskId;

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

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }
}
