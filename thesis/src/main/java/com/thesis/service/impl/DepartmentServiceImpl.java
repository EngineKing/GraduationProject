package com.thesis.service.impl;

import com.thesis.dao.DepartmentDao;
import com.thesis.dao.UserDao;
import com.thesis.entity.Department;
import com.thesis.entity.User;
import com.thesis.exception.RRException;
import com.thesis.form.DepartmentForm;
import com.thesis.service.DepartmentService;
import com.thesis.utils.Query;
import com.thesis.utils.R;
import com.thesis.validator.Assert;
import com.thesis.vo.DepartmentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author LeiPeng
 * @Date 2020/1/14 - 14:03
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    DepartmentDao departmentDao;

    @Autowired
    UserDao userDao;

    /**
     * 新增部门
     *
     * @param department 部门信息
     */
    @Override
    public void add(Department department) {
        Department foundDepartment = departmentDao.getByName(department.getName());
        if (foundDepartment != null) throw new RRException("新增部门失败，该部门名称已经存在");
        departmentDao.add(department);
    }

    /**
     * 通过部门id删除部门信息
     *
     * @param id 部门id
     */
    @Override
    public void delete(Integer id) {
        Department foundDepartment = departmentDao.get(id);
        if (foundDepartment == null) throw new RRException("删除部门失败，该部门不存在");
        List<User> users = userDao.getUsersByDepartmentId(id);
        if (!users.isEmpty()) throw new RRException("删除部门失败，该部门存在用户");
        departmentDao.delete(id);
    }

    /**
     * 分页查询
     *
     * @param departmentForm 查询条件
     * @param query          页面限制
     * @return 查询结果
     */
    @Override
    public List<DepartmentVO> pageQuery(DepartmentForm departmentForm, Query query) {
        List<Department> departments = departmentDao.pageQuery(departmentForm, query);
        List<DepartmentVO> departmentVOS = new ArrayList<>();
        for (int i = 0; i < departments.size(); i++) {
            DepartmentVO departmentVO = new DepartmentVO();
            Department department = departments.get(i);
            departmentVO.setId(department.getId());
            departmentVO.setName(department.getName());
            if (department.getPid() == 0) {
                departmentVO.setpDept(new Department("无"));
            } else {
                departmentVO.setpDept(departmentDao.get(department.getPid()));
            }
            departmentVO.setDescription(department.getDescription());
            departmentVO.setStatus(department.getStatus());
            departmentVOS.add(departmentVO);
        }
        return departmentVOS;
    }

    /**
     * 查询满足查询条件的数量
     *
     * @param departmentForm 查询条件
     * @param query          页面限制
     * @return 总数
     */
    @Override
    public int pageQueryCount(DepartmentForm departmentForm, Query query) {
        return departmentDao.pageQueryCount(departmentForm, query);
    }

    @Override
    public List<Department> getAll() {
        return departmentDao.getAll();
    }

    /**
     * 修改部门信息
     *
     * @param department 部门信息
     */
    @Override
    public void update(Department department) {
        Department foundDepartment = departmentDao.getByName(department.getName());
        if (foundDepartment != null && foundDepartment.getId() != department.getId())
            throw new RRException("修改部门失败，该部门名称已存在");
        departmentDao.update(department);
    }
}
