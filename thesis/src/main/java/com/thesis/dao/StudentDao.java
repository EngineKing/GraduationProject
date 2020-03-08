package com.thesis.dao;

import com.thesis.entity.Student;
import com.thesis.form.StudentForm;
import com.thesis.utils.Query;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author LeiPeng
 * @Date 2020/1/28 - 14:17
 */
public interface StudentDao {
    void add(Student student);

    Student get(@Param("studentId") Integer id);

    void delete(@Param("studentId") Integer id);

    void update(Student student);

    List<Student> pageQuery(@Param("studentForm") StudentForm studentForm, @Param("query") Query query);

    int pageQueryCount(@Param("studentForm") StudentForm studentForm, @Param("query") Query query);

    Student getByNumber(@Param("number") String number);

    Student getByUserId(@Param("userId") Integer id);

    List<Student> getByTeacherId(@Param("teacherId") Integer teacherId);

    List<Student> getListByIds(@Param("studentIds") List<Integer> sIds);
}
