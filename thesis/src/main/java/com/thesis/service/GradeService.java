package com.thesis.service;

import com.thesis.entity.Grade;
import com.thesis.form.GradeForm;
import com.thesis.utils.Query;
import com.thesis.vo.GradeVO;

import java.util.List;

/**
 * @Author LeiPeng
 * @Date 2020/1/23 - 14:33
 */
public interface GradeService {
    void addGrade(Grade grade);

    void updateGrade(Grade grade);

    void deleteGradeById(Integer id);

    List<GradeVO> pageQuery(GradeForm gradeForm, Query query);

    int pageQueryCount(GradeForm gradeForm, Query query);
}
