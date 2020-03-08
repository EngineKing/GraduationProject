package com.thesis.service;

import com.thesis.entity.TeachingClass;
import com.thesis.form.TeachingClassForm;
import com.thesis.utils.Query;
import com.thesis.vo.TeachingClassVO;

import java.util.List;

/**
 * @Author LeiPeng
 * @Date 2020/1/23 - 19:33
 */
public interface TeachingClassService {
    void add(TeachingClass teachingClass);

    void update(TeachingClass teachingClass);

    void delete(Integer id);

    List<TeachingClassVO> pageQuery(TeachingClassForm teachingClassForm, Query query);

    int pageQueryCount(TeachingClassForm teachingClassForm, Query query);

    List<TeachingClass> getAll();
}
