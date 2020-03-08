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
    void add(TeachingClass teachingClass);

    TeachingClass get(@Param("teachingClassId") Integer id);

    void delete(@Param("teachingClassId") Integer id);

    void update(TeachingClass teachingClass);

    List<TeachingClass> pageQuery(@Param("teachingClassForm") TeachingClassForm teachingClassForm, @Param("query") Query query);

    int pageQueryCount(@Param("teachingClassForm") TeachingClassForm teachingClassForm, @Param("query") Query query);

    TeachingClass getByName(@Param("teachingClassName") String name);

    List<TeachingClass> getAll();
}
