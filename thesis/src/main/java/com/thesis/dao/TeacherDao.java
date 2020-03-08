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
    void add(Teacher teacher);

    Teacher get(@Param("teacherId") Integer id);

    void delete(@Param("teacherId") Integer id);

    void update(Teacher teacher);

    List<Teacher> pageQuery(@Param("teacherForm") TeacherForm teacherForm, @Param("query") Query query);

    int pageQueryCount(@Param("teacherForm") TeacherForm teacherForm, @Param("query") Query query);

    Teacher getByNumber(@Param("number") String number);

    List<Teacher> getAll();

    List<Integer> getLeaderIds();

    Teacher getByUserId(@Param("userId") Integer id);

    List<Teacher> getListByIds(@Param("teacherIds") List<Integer> tIds);
}
