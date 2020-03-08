package com.thesis.dao;

import com.thesis.entity.StudentTopic;
import com.thesis.form.StudentTopicForm;
import com.thesis.utils.Query;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author LeiPeng
 * @Date 2020/2/16 - 11:28
 */
public interface StudentTopicDao {
    void add(StudentTopic studentTopic);

    StudentTopic getByStudentId(@Param("studentId") Integer studentId);

    List<StudentTopic> getByTopicId(@Param("topicId") Integer id);

    void isAgree(@Param("studentId") Integer studentId, @Param("isTutorAgree") Integer isTutorAgree);

    List<StudentTopic> pageQuery(@Param("studentTopicForm") StudentTopicForm studentTopicForm, @Param("query") Query query);

    int pageQueryCount(@Param("studentTopicForm") StudentTopicForm studentTopicForm, @Param("query") Query query);

    void deleteByStudentId(@Param("studentId") Integer studentId);
}
