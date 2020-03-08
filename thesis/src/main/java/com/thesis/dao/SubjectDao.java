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
    void add(Subject subject);

    Subject get(@Param("subjectId") Integer id);

    void delete(@Param("subjectId") Integer id);

    void update(Subject subject);

    List<Subject> pageQuery(@Param("subjectForm") SubjectForm subjectForm, @Param("query") Query query);

    int pageQueryCount(@Param("subjectForm") SubjectForm subjectForm, @Param("query") Query query);

    Subject getByName(@Param("subjectName") String name);

    List<Subject> getAll();
}
