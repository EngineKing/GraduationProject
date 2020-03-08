package com.thesis.form;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author LeiPeng
 * @Date 2020/1/28 - 14:07
 */
@Getter
@Setter
public class TaskResultForm {
//    private String title;
//    private Integer isAuditPassed;
//    private Integer isResultSubmit;
//    private Integer result;
//    private Integer studentId;
//    private Integer teacherId;
    private String number; // 学生学号
    private String name; // 学生姓名
    private Integer result; // 任务结果
    private Integer taskId; // 任务id
    private Integer curPage;
    private Integer limit;
}
