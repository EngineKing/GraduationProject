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
    void addStudent(Student student);

    Student getStudentById(@Param("studentId") Integer id);

    void deleteStudentById(@Param("studentId") Integer id);

    void updateStudent(Student student);

    List<Student> pageQuery(@Param("studentForm") StudentForm studentForm, @Param("query") Query query);

    int pageQueryCount(@Param("studentForm") StudentForm studentForm, @Param("query") Query query);
}
