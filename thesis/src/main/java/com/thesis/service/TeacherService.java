package com.thesis.service;

import com.thesis.vo.StudentResultVO;
import com.thesis.entity.Teacher;
import com.thesis.entity.UserAndTeacher;
import com.thesis.form.TeacherForm;
import com.thesis.form.TeacherTaskForm;
import com.thesis.utils.Query;
import com.thesis.vo.TaskVO;
import com.thesis.vo.TeacherVO;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

/**
 * @Author LeiPeng
 * @Date 2020/1/26 - 11:08
 */
public interface TeacherService {

    void add(UserAndTeacher userAndTeacher);

    void delete(Integer id);

    void update(Teacher teacher);

    List<TeacherVO> pageQuery(TeacherForm teacherForm, Query query);

    int pageQueryCount(TeacherForm teacherForm, Query query);

    List<Teacher> getAll();

    Teacher getByUserId(Integer id);

    void related2Task(TeacherTaskForm teacherTaskForm);

    List<TaskVO> query(Integer userId, String title);

    List<StudentResultVO> getTaskResult(Integer userId, Integer taskId);

    void pass(Integer taskResultId);

    void fail(Integer taskResultId, String auditOpinion);

    List<StudentResultVO> getTaskResultWithCondition(Integer userId, Integer taskId, String number, String name);

    Workbook export(String teacherIds);

    Workbook allExport(TeacherForm teacherForm);

    void batchInsert(List<UserAndTeacher> userAndTeachers);

    void related2TaskByExcel(TeacherTaskForm teacherTaskForm);
}
