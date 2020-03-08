package com.thesis.service;

import com.thesis.entity.Subject;
import com.thesis.form.SubjectForm;
import com.thesis.utils.Query;
import com.thesis.vo.SubjectVO;

import java.util.List;

/**
 * @Author LeiPeng
 * @Date 2020/1/23 - 9:19
 */
public interface SubjectService {
    void add(Subject subject);

    void delete(Integer id);

    void update(Subject subject);

    List<SubjectVO> pageQuery(SubjectForm subjectForm, Query query);

    int pageQueryCount(SubjectForm subjectForm, Query query);

    List<Subject> getAll();
}
