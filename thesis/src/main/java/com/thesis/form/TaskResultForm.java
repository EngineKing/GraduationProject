package com.thesis.form;

/**
 * @Author LeiPeng
 * @Date 2020/1/28 - 14:07
 */
public class TaskResultForm {
    private String title;
    private Integer isAuditPassed;
    private Integer isResultSubmit;
    private Integer result;
    private Integer studentId;
    private Integer teacherId;
    private Integer taskId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getIsAuditPassed() {
        return isAuditPassed;
    }

    public void setIsAuditPassed(Integer isAuditPassed) {
        this.isAuditPassed = isAuditPassed;
    }

    public Integer getIsResultSubmit() {
        return isResultSubmit;
    }

    public void setIsResultSubmit(Integer isResultSubmit) {
        this.isResultSubmit = isResultSubmit;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
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
