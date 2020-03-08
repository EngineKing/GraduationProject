package com.thesis.service;

import com.thesis.entity.ExpertGroup;
import com.thesis.entity.Teacher;
import com.thesis.form.ExpertGroupForm;
import com.thesis.utils.Query;
import com.thesis.vo.ExpertGroupVO;

import java.util.List;

/**
 * @Author LeiPeng
 * @Date 2020/1/27 - 17:24
 */
public interface ExpertGroupService {
    List<ExpertGroupVO> pageQuery(ExpertGroupForm expertGroupForm, Query query);

    int pageQueryCount(ExpertGroupForm expertGroupForm, Query query);

    void update(ExpertGroup expertGroup);

    void delete(Integer id);

    void add(ExpertGroup expertGroup);

    List<Teacher> getLeaders();
}
