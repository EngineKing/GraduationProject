package com.thesis.service;

import com.thesis.entity.Student;
import com.thesis.entity.UserAndStudent;
import com.thesis.form.StudentForm;
import com.thesis.form.StudentTaskForm;
import com.thesis.utils.Query;
import com.thesis.vo.StudentVO;
import com.thesis.vo.TaskVO;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @Author LeiPeng
 * @Date 2020/1/28 - 14:15
 */
public interface StudentService {
    int pageQueryCount(StudentForm studentForm, Query query);

    List<StudentVO> pageQuery(StudentForm studentForm, Query query);

    void update(Student student);

    void delete(Integer id);

    void add(UserAndStudent userAndStudent);

    Student getByUserId(Integer id);

    void related2Task(StudentTaskForm studentTaskForm);

    void submit(Map<String, String> map, MultipartFile file);

    List<TaskVO> query(Integer userId, String title);

    Student get(Integer studentId);

    void batchInsert(List<UserAndStudent> userAndStudents);

    Workbook export(String studentIds);

    Workbook allExport(StudentForm studentForm);

    void related2TaskByExcel(StudentTaskForm studentTaskForm);
}
