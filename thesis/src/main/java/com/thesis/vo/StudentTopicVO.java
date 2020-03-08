package com.thesis.vo;

import com.thesis.entity.Student;
import com.thesis.entity.Teacher;
import com.thesis.entity.Topic;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author LeiPeng
 * @Date 2020/2/21 - 16:11
 */
@Getter
@Setter
public class StudentTopicVO {
    private Integer id;
    private Student student;
    private Topic topic;
    private Teacher teacher;
    private Integer isTutorAgree;
}
