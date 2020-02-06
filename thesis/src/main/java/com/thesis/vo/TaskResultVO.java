package com.thesis.vo;

import com.thesis.entity.Annex;
import com.thesis.entity.Student;
import com.thesis.entity.Task;
import com.thesis.entity.Teacher;

/**
 * @Author LeiPeng
 * @Date 2020/1/28 - 14:11
 */
public class TaskResultVO {
    private Integer id;
    private String title;
    private String description;
    private Long submitTime;
    private Integer isAuditPassed;
    private String auditOpinion;
    private Integer isResultSubmit;
    private Integer result;
    private Integer repeatRate; // 当任务为查重时，才需要此字段，默认为-1
    private Annex annex;
    private Student student;
    private Teacher teacher;
    private Task task;

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

    public Annex getAnnex() {
        return annex;
    }

    public void setAnnex(Annex annex) {
        this.annex = annex;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
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
}
