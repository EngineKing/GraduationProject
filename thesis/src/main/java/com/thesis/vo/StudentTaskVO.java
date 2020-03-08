package com.thesis.vo;

import com.thesis.entity.Student;
import com.thesis.entity.Task;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author LeiPeng
 * @Date 2020/2/26 - 15:44
 */
@Getter
@Setter
public class StudentTaskVO {
    private Integer id;
    private Student student;
    private Task task;
}
