package com.thesis.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * (TbStudentTask)实体类
 *
 * @author makejava
 * @since 2020-02-26 15:48:50
 */
@Getter
@Setter
public class StudentTask{
    private Integer id;
    private Integer studentId;
    private Integer taskId;
}