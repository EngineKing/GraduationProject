package com.thesis.service.impl;

import com.thesis.dao.DepartmentDao;
import com.thesis.dao.SubjectDao;
import com.thesis.dao.TeacherDao;
import com.thesis.entity.Subject;
import com.thesis.entity.Teacher;
import com.thesis.exception.RRException;
import com.thesis.form.TeacherForm;
import com.thesis.service.TeacherService;
import com.thesis.utils.Query;
import com.thesis.vo.TeacherVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author LeiPeng
 * @Date 2020/1/26 - 13:39
 */
@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    TeacherDao teacherDao;

    @Autowired
    DepartmentDao departmentDao;

    @Autowired
    SubjectDao subjectDao;

    @Override
    public void addTeacher(Teacher teacher) {
        // 教师编号应该自动生成
        teacherDao.addTeacher(teacher);
    }

    @Override
    public void deleteTeacherById(Integer id) {
        Teacher foundTeacher = teacherDao.getTeacherById(id);
        if (foundTeacher == null) throw new RRException("删除教师失败，该教师不存在");
        teacherDao.deleteTeacherById(id);
    }

    @Override
    public void updateTeacher(Teacher teacher) {
        teacherDao.updateTeacher(teacher);
    }

    @Override
    public List<TeacherVO> pageQuery(TeacherForm teacherForm, Query query) {
        List<Teacher> teacherList = teacherDao.pageQuery(teacherForm, query);
        List<TeacherVO> teacherVOList = new ArrayList<>();
        for (int i = 0; i < teacherList.size(); i++) {
            TeacherVO teacherVO = new TeacherVO();
            Teacher teacher = teacherList.get(i);
            teacherVO.setId(teacher.getId());
            teacherVO.setNumber(teacher.getNumber());
            teacherVO.setName(teacher.getName());
            teacherVO.setAge(teacher.getAge());
            teacherVO.setEmail(teacher.getEmail());
            teacherVO.setIntroduction(teacher.getIntroduction());
            teacherVO.setGender(teacher.getGender());
            teacherVO.setJobTitle(teacher.getJobTitle());
            teacherVO.setPhone(teacher.getPhone());
            teacherVO.setDepartment(departmentDao.getDepartmentById(teacher.getDepartmentId()));
            teacherVO.setSubject(subjectDao.getSubjectById(teacher.getSubjectId()));
            teacherVOList.add(teacherVO);
        }
        return teacherVOList;
    }

    @Override
    public int pageQueryCount(TeacherForm teacherForm, Query query) {
        return teacherDao.pageQueryCount(teacherForm, query);
    }
}
