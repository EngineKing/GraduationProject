package com.thesis.entity;

/**
 * @Author LeiPeng
 * @Date 2020/1/28 - 13:36
 */
public class TaskResult {
    private Integer id;
    private String title;
    private String description;
    private Long submitTime;
    private Integer isAuditPassed;
    private String auditOpinion;
    private Integer isResultSubmit;
    private Integer result;
    private Integer repeatRate; // 当任务为查重时，才需要此字段，默认为-1
    private Integer annexId;
    private Integer studentId;
    private Integer teacherId;
    private Integer taskId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Long submitTime) {
        this.submitTime = submitTime;
    }

    public Integer getIsAuditPassed() {
        return isAuditPassed;
    }

    public void setIsAuditPassed(Integer isAuditPassed) {
        this.isAuditPassed = isAuditPassed;
    }

    public String getAuditOpinion() {
        return auditOpinion;
    }

    public void setAuditOpinion(String auditOpinion) {
        this.auditOpinion = auditOpinion;
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

    public Integer getRepeatRate() {
        return repeatRate;
    }

    public void setRepeatRate(Integer repeatRate) {
        this.repeatRate = repeatRate;
    }

    public Integer getAnnexId() {
        return annexId;
    }

    public void setAnnexId(Integer annexId) {
        this.annexId = annexId;
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
