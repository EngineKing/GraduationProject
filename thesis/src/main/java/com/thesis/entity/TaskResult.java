package com.thesis.entity;

import lombok.*;

/**
 * @Author LeiPeng
 * @Date 2020/1/28 - 13:36
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class TaskResult {
    private Integer id;
    private String title;
    private String description;
    private Long submitTime;
    private Integer isAuditPassed; // 0 通过 1 不通过
    private String auditOpinion;
    private Integer isResultSubmit;
    private Integer result; // 0 通过 1 不通过 -1 未答辩 2未上传结果
    private Integer repeatRate; // 当任务为查重时，才需要此字段，默认为-1
    private Integer annexId;
    private Integer studentId;
    private Integer teacherId;
    private Integer taskId;

    public TaskResult(String title, String description, Long submitTime, Integer isAuditPassed, String auditOpinion,
                      Integer isResultSubmit, Integer result, Integer repeatRate, Integer annexId, Integer studentId,
                      Integer teacherId, Integer taskId) {
        this.title = title;
        this.description = description;
        this.submitTime = submitTime;
        this.isAuditPassed = isAuditPassed;
        this.auditOpinion = auditOpinion;
        this.isResultSubmit = isResultSubmit;
        this.result = result;
        this.repeatRate = repeatRate;
        this.annexId = annexId;
        this.studentId = studentId;
        this.teacherId = teacherId;
        this.taskId = taskId;
    }
}
