package com.thesis.service.impl;

import com.thesis.dao.DepartmentDao;
import com.thesis.dao.SubjectDao;
import com.thesis.entity.Subject;
import com.thesis.exception.RRException;
import com.thesis.form.SubjectForm;
import com.thesis.service.SubjectService;
import com.thesis.utils.Query;
import com.thesis.vo.SubjectVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author LeiPeng
 * @Date 2020/1/23 - 9:19
 */
@Service
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    SubjectDao subjectDao;

    @Autowired
    DepartmentDao departmentDao;
//
    /**
     * 新增学科
     *
     * @param subject 学科信息
     */
    @Override
    public void add(Subject subject) {
        Subject foundSubject = subjectDao.getByName(subject.getName());
        if (foundSubject != null) throw new RRException("新增学科失败，该学科名称已经存在");
        subjectDao.add(subject);
    }

    /**
     * 根据学科id删除学科
     *
     * @param id 学科id
     */
    @Override
    public void delete(Integer id) {
        Subject foundSubject = subjectDao.get(id);
        if (foundSubject == null) throw new RRException("删除学科失败，该学科不存在");
//        List<Student> studentList = studentDao.getStudentsBySubjectId(id);
        subjectDao.delete(id);
    }

    /**
     * 更新学科
     *
     * @param subject 学科信息
     */
    @Override
    public void update(Subject subject) {
        Subject foundSubject = subjectDao.getByName(subject.getName());
        if (foundSubject != null && foundSubject.getId() != subject.getId()) throw new RRException("更新学科失败，该学科名称已经存在");
        subjectDao.update(subject);
    }

    /**
     * 分页查询
     *
     * @param subjectForm 查询条件
     * @param query       页
     * @return 查询结果
     */
    @Override
    public List<SubjectVO> pageQuery(SubjectForm subjectForm, Query query) {
        List<Subject> subjectList = subjectDao.pageQuery(subjectForm, query);
        List<SubjectVO> subjectVOList = new ArrayList<>();
        for (int i = 0; i < subjectList.size(); i++) {
            SubjectVO subjectVO = new SubjectVO();
            Subject subject = subjectList.get(i);
            subjectVO.setId(subject.getId());
            subjectVO.setName(subject.getName());
            subjectVO.setDescription(subject.getDescription());
            subjectVO.setDepartment(departmentDao.get(subject.getDepartmentId()));
            subjectVO.setStatus(subject.getStatus());
            subjectVOList.add(subjectVO);
        }
        return subjectVOList;
    }

    /**
     * 查询满足查询条件的数量
     *
     * @param subjectForm 查询条件
     * @param query       页
     * @return 总数
     */
    @Override
    public int pageQueryCount(SubjectForm subjectForm, Query query) {
        return subjectDao.pageQueryCount(subjectForm, query);
    }

    @Override
    public List<Subject> getAll() {
        return subjectDao.getAll();
    }
}
