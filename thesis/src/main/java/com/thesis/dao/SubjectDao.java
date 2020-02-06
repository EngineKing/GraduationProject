package com.thesis.dao;

import com.thesis.entity.Subject;
import com.thesis.form.SubjectForm;
import com.thesis.utils.Query;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author LeiPeng
 * @Date 2020/1/23 - 9:20
 */
public interface SubjectDao {
    void addSubject(Subject subject);

    Subject getSubjectById(@Param("subjectId") Integer id);

    void deleteSubjectById(@Param("subjectId") Integer id);

    void updateSubject(Subject subject);

    List<Subject> pageQuery(@Param("subjectForm") SubjectForm subjectForm, @Param("query") Query query);

    int pageQueryCount(@Param("subjectForm") SubjectForm subjectForm, @Param("query") Query query);

    Subject getSubjectByName(@Param("subjectName") String name);
}
