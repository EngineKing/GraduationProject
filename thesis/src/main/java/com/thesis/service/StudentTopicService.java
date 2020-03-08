package com.thesis.service;

import com.thesis.entity.StudentTopic;
import com.thesis.form.StudentTopicForm;
import com.thesis.utils.Query;
import com.thesis.vo.StudentTopicVO;

import java.util.List;

/**
 * @Author LeiPeng
 * @Date 2020/2/16 - 11:29
 */
public interface StudentTopicService {
    void add(StudentTopic studentTopic);

    StudentTopic getByStudentId(Integer id);

    List<StudentTopicVO> getByTopicId(Integer id);

    void isAgree(StudentTopic studentTopic);

    List<StudentTopicVO> pageQuery(StudentTopicForm studentTopicForm, Query query);

    int pageQueryCount(StudentTopicForm studentTopicForm, Query query);
}
