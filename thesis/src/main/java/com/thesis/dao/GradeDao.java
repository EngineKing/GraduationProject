package com.thesis.dao;

import com.thesis.entity.Grade;
import com.thesis.form.GradeForm;
import com.thesis.utils.Query;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author LeiPeng
 * @Date 2020/1/23 - 14:34
 */
public interface GradeDao {
    void add(Grade grade);

    Grade get(@Param("gradeId") Integer id);
//
//    void deleteGradeById(@Param("gradeId") Integer id);
//
//    void updateGrade(Grade grade);
//
//    List<Grade> pageQuery(@Param("gradeForm") GradeForm gradeForm, @Param("query") Query query);
//
//    int pageQueryCount(@Param("gradeForm") GradeForm gradeForm, @Param("query") Query query);

    Grade getByName(@Param("gradeName") String name);

    List<Grade> getAll();
}
