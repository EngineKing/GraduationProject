package com.thesis.dao;

import com.thesis.entity.TeachingClass;
import com.thesis.form.TeachingClassForm;
import com.thesis.utils.Query;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author LeiPeng
 * @Date 2020/1/23 - 19:32
 */
public interface TeachingClassDao {
    void addTeachingClass(TeachingClass teachingClass);

    TeachingClass getTeachingClassById(@Param("teachingClassId") Integer id);

    void deleteTeachingClassById(@Param("teachingClassId") Integer id);

    void updateTeachingClass(TeachingClass teachingClass);

    List<TeachingClass> pageQuery(@Param("teachingClassForm") TeachingClassForm teachingClassForm, @Param("query") Query query);

    int pageQueryCount(@Param("teachingClassForm") TeachingClassForm teachingClassForm, @Param("query") Query query);

    TeachingClass getTeachingClassByName(@Param("teachingClassName") String name);
}
