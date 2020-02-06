package com.thesis.service;

import com.thesis.entity.Student;
import com.thesis.form.StudentForm;
import com.thesis.utils.Query;
import com.thesis.vo.StudentVO;

import java.util.List;

/**
 * @Author LeiPeng
 * @Date 2020/1/28 - 14:15
 */
public interface StudentService {
    int pageQueryCount(StudentForm studentForm, Query query);

    List<StudentVO> pageQuery(StudentForm studentForm, Query query);

    void updateStudent(Student student);

    void deleteStudentById(Integer id);

    void addStudent(Student student);
}
