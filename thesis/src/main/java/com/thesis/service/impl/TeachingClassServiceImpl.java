package com.thesis.service.impl;

import com.thesis.dao.DepartmentDao;
import com.thesis.dao.GradeDao;
import com.thesis.dao.SubjectDao;
import com.thesis.dao.TeachingClassDao;
import com.thesis.entity.TeachingClass;
import com.thesis.exception.RRException;
import com.thesis.form.TeachingClassForm;
import com.thesis.service.TeachingClassService;
import com.thesis.utils.Query;
import com.thesis.vo.TeachingClassVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author LeiPeng
 * @Date 2020/1/23 - 19:33
 */
@Service
public class TeachingClassServiceImpl implements TeachingClassService {
    @Autowired
    TeachingClassDao teachingClassDao;

    @Autowired
    GradeDao gradeDao;

    @Autowired
    SubjectDao subjectDao;

    @Autowired
    DepartmentDao departmentDao;


    @Override
    public void add(TeachingClass teachingClass) {
        TeachingClass foundTeachingClass = teachingClassDao.getByName(teachingClass.getName());
        if (foundTeachingClass != null) throw new RRException("新增班级失败，该班级名称已存在");
        teachingClass.setDepartmentId(subjectDao.get(teachingClass.getSubjectId()).getDepartmentId());
        teachingClassDao.add(teachingClass);
    }

    @Override
    public void update(TeachingClass teachingClass) {
        TeachingClass foundTeachingClass = teachingClassDao.getByName(teachingClass.getName());
        if (foundTeachingClass != null && foundTeachingClass.getId() != teachingClass.getId()) throw new RRException("更新班级失败，该班级名称已存在");
        teachingClass.setDepartmentId(subjectDao.get(teachingClass.getSubjectId()).getDepartmentId());
        teachingClassDao.update(teachingClass);
    }

    @Override
    public void delete(Integer id) {
        TeachingClass foundTeachingClass = teachingClassDao.get(id);
        if (foundTeachingClass == null) throw new RRException("删除班级失败，该班级不存在");
        // 还要判断该班级下是否存在学生
        teachingClassDao.delete(id);
    }

    @Override
    public List<TeachingClassVO> pageQuery(TeachingClassForm teachingClassForm, Query query) {
        List<TeachingClass> teachingClassList = teachingClassDao.pageQuery(teachingClassForm, query);
        List<TeachingClassVO> teachingClassVOList = new ArrayList<>();
        for (int i = 0; i < teachingClassList.size(); i++) {
            TeachingClassVO teachingClassVO = new TeachingClassVO();
            TeachingClass teachingClass = teachingClassList.get(i);
            teachingClassVO.setId(teachingClass.getId());
            teachingClassVO.setName(teachingClass.getName());
            teachingClassVO.setGrade(gradeDao.get(teachingClass.getGradeId()));
            teachingClassVO.setSubject(subjectDao.get(teachingClass.getSubjectId()));
            teachingClassVO.setDepartment(departmentDao.get(teachingClass.getDepartmentId()));
            teachingClassVO.setStatus(teachingClass.getStatus());
            teachingClassVOList.add(teachingClassVO);
        }
        return teachingClassVOList;
    }

    @Override
    public int pageQueryCount(TeachingClassForm teachingClassForm, Query query) {
        return teachingClassDao.pageQueryCount(teachingClassForm, query);
    }

    @Override
    public List<TeachingClass> getAll() {
        return teachingClassDao.getAll();
    }
}
