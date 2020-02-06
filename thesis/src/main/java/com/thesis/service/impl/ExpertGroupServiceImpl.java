package com.thesis.service.impl;

import com.thesis.dao.ExpertGroupDao;
import com.thesis.dao.TeacherDao;
import com.thesis.entity.ExpertGroup;
import com.thesis.exception.RRException;
import com.thesis.form.ExpertGroupForm;
import com.thesis.service.ExpertGroupService;
import com.thesis.utils.Query;
import com.thesis.vo.ExpertGroupVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author LeiPeng
 * @Date 2020/1/27 - 17:25
 */
@Service
public class ExpertGroupServiceImpl implements ExpertGroupService {
    @Autowired
    ExpertGroupDao expertGroupDao;

    @Autowired
    TeacherDao teacherDao;

    @Override
    public List<ExpertGroupVO> pageQuery(ExpertGroupForm expertGroupForm, Query query) {
        List<ExpertGroup> expertGroupList = expertGroupDao.pageQuery(expertGroupForm, query);
        List<ExpertGroupVO> expertGroupVOList = new ArrayList<>();
        for (int i = 0; i < expertGroupList.size(); i++) {
            ExpertGroup expertGroup = expertGroupList.get(i);
            ExpertGroupVO expertGroupVO = new ExpertGroupVO();
            expertGroupVO.setId(expertGroup.getId());
            expertGroupVO.setName(expertGroup.getName());
            expertGroupVO.setTeacher(teacherDao.getTeacherById(expertGroup.getTeacherId()));
            expertGroupVOList.add(expertGroupVO);
        }
        return expertGroupVOList;
    }

    @Override
    public int pageQueryCount(ExpertGroupForm expertGroupForm, Query query) {
        return expertGroupDao.pageQueryCount(expertGroupForm, query);
    }

    @Override
    public void updateExpertGroup(ExpertGroup expertGroup) {
        ExpertGroup foundExpertGroup = expertGroupDao.getExpertGroupByName(expertGroup.getName());
        if (foundExpertGroup != null && foundExpertGroup.getId() != expertGroup.getId()) throw new RRException("更新专家组失败，该专家组名称已经存在");
        expertGroupDao.updateExpertGroup(expertGroup);
    }

    @Override
    public void deleteExpertGroupById(Integer id) {
        ExpertGroup foundExpertGroup = expertGroupDao.getExpertGroupById(id);
        if (foundExpertGroup == null) throw new RRException("删除专家组失败，该专家组不存在");
        expertGroupDao.deleteExpertGroupById(id);
    }

    @Override
    public void addExpertGroup(ExpertGroup expertGroup) {
        ExpertGroup foundExpertGroup = expertGroupDao.getExpertGroupByName(expertGroup.getName());
        if (foundExpertGroup != null) throw new RRException("新增专家组失败，该专家组名称已经存在");
        expertGroupDao.addExpertGroup(expertGroup);
    }
}
