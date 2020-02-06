package com.thesis.service;

import com.thesis.entity.Teacher;
import com.thesis.form.TeacherForm;
import com.thesis.utils.Query;
import com.thesis.vo.TeacherVO;

import java.util.List;

/**
 * @Author LeiPeng
 * @Date 2020/1/26 - 11:08
 */
public interface TeacherService {

    void addTeacher(Teacher teacher);

    void deleteTeacherById(Integer id);

    void updateTeacher(Teacher teacher);

    List<TeacherVO> pageQuery(TeacherForm teacherForm, Query query);

    int pageQueryCount(TeacherForm teacherForm, Query query);
}
