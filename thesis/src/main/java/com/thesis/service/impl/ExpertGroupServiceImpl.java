package com.thesis.service.impl;

import com.thesis.dao.ExpertGroupDao;
import com.thesis.dao.TeacherDao;
import com.thesis.entity.ExpertGroup;
import com.thesis.entity.Teacher;
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
//            expertGroupVO.setTeacher(teacherDao.get(expertGroup.getTeacherId()));
            expertGroupVO.setTeacher(expertGroup.getTeacherId() == null ? new Teacher("无") :
                    teacherDao.get(expertGroup.getTeacherId()));
            expertGroupVO.setStatus(expertGroup.getStatus());
            expertGroupVOList.add(expertGroupVO);
        }
        return expertGroupVOList;
    }

    @Override
    public int pageQueryCount(ExpertGroupForm expertGroupForm, Query query) {
        return expertGroupDao.pageQueryCount(expertGroupForm, query);
    }

    @Override
    public void update(ExpertGroup expertGroup) {
        ExpertGroup foundExpertGroup = expertGroupDao.getByName(expertGroup.getName());
        if (foundExpertGroup != null && foundExpertGroup.getId() != expertGroup.getId())
            throw new RRException("更新专家组失败，该专家组名称已经存在");
        expertGroupDao.update(expertGroup);
    }

    @Override
    public void delete(Integer id) {
        ExpertGroup foundExpertGroup = expertGroupDao.get(id);
        if (foundExpertGroup == null) throw new RRException("删除专家组失败，该专家组不存在");
        expertGroupDao.delete(id);
    }

    @Override
    public void add(ExpertGroup expertGroup) {
        ExpertGroup foundExpertGroup = expertGroupDao.getByName(expertGroup.getName());
        if (foundExpertGroup != null) throw new RRException("新增专家组失败，该专家组名称已经存在");
        expertGroupDao.add(expertGroup);
    }

    @Override
    public List<Teacher> getLeaders() {
        List<Integer> leaders = teacherDao.getLeaderIds();
        List<Integer> foundLeaderIds = expertGroupDao.getLeaderIds();
        leaders.removeAll(foundLeaderIds);
        List<Teacher> result = new ArrayList<>();
        for (int i = 0; i < leaders.size(); i++) {
            Teacher teacher = teacherDao.get(leaders.get(i));
            result.add(teacher);
        }
        return result;
    }
}
