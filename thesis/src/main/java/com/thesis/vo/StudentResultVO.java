package com.thesis.vo;

import com.thesis.entity.Student;
import com.thesis.entity.TaskResult;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author LeiPeng
 * @Date 2020/2/27 - 21:24
 */
@Getter
@Setter
public class StudentResultVO {
    private Integer id;
    private Student student;
    private TaskResult taskResult;
}
