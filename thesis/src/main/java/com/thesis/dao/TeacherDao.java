package com.thesis.dao;

import com.thesis.entity.Teacher;
import com.thesis.form.TeacherForm;
import com.thesis.utils.Query;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author LeiPeng
 * @Date 2020/1/26 - 11:07
 */
public interface TeacherDao {
    void addTeacher(Teacher teacher);

    Teacher getTeacherById(@Param("teacherId") Integer id);

    void deleteTeacherById(@Param("teacherId") Integer id);

    void updateTeacher(Teacher teacher);

    List<Teacher> pageQuery(@Param("teacherForm") TeacherForm teacherForm, @Param("query") Query query);

    int pageQueryCount(@Param("teacherForm") TeacherForm teacherForm, @Param("query") Query query);
}
