package com.thesis.service.impl;

import com.thesis.dao.DepartmentDao;
import com.thesis.dao.GradeDao;
import com.thesis.dao.SubjectDao;
import com.thesis.entity.Grade;
import com.thesis.exception.RRException;
import com.thesis.form.GradeForm;
import com.thesis.service.GradeService;
import com.thesis.utils.Query;
import com.thesis.vo.GradeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author LeiPeng
 * @Date 2020/1/23 - 14:33
 */
@Service
public class GradeServiceImpl implements GradeService {
    @Autowired
    GradeDao gradeDao;

    @Autowired
    SubjectDao subjectDao;

    @Autowired
    DepartmentDao departmentDao;

    @Override
    public void addGrade(Grade grade) {
        Grade foundGrade = gradeDao.getGradeByName(grade.getName());
        if (foundGrade != null) throw new RRException("新增班级失败，该班级名称已经存在");
        gradeDao.addGrade(grade);
    }

    @Override
    public void updateGrade(Grade grade) {
        Grade foundGrade = gradeDao.getGradeByName(grade.getName());
        if (foundGrade != null && foundGrade.getId() != grade.getId()) throw new RRException("更新班级失败，该班级名称已经存在");
        gradeDao.updateGrade(grade);
    }

    @Override
    public void deleteGradeById(Integer id) {
        Grade foundGrade = gradeDao.getGradeById(id);
        if (foundGrade == null) throw new RRException("删除班级失败，该班级不存在");
        // 需要判断班级下是否还存在学生
        gradeDao.deleteGradeById(id);
    }

    @Override
    public List<GradeVO> pageQuery(GradeForm gradeForm, Query query) {
        List<Grade> gradeList = gradeDao.pageQuery(gradeForm, query);
        List<GradeVO> gradeVOList = new ArrayList<>();
        for (int i = 0; i < gradeList.size(); i++) {
            GradeVO gradeVO = new GradeVO();
            Grade grade = gradeList.get(i);
            gradeVO.setId(grade.getId());
            gradeVO.setName(grade.getName());
            gradeVO.setSubject(subjectDao.getSubjectById(grade.getId()));
            gradeVO.setDepartment(departmentDao.getDepartmentById(grade.getId()));
            gradeVOList.add(gradeVO);
        }
        return gradeVOList;
    }

    @Override
    public int pageQueryCount(GradeForm gradeForm, Query query) {
        return gradeDao.pageQueryCount(gradeForm, query);
    }
}
