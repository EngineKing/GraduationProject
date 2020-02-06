package com.thesis.service;

import com.thesis.entity.Department;
import com.thesis.form.DepartmentForm;
import com.thesis.utils.Query;
import com.thesis.utils.R;
import com.thesis.vo.DepartmentVO;

import java.util.List;

/**
 * @Author LeiPeng
 * @Date 2020/1/14 - 13:57
 */
public interface DepartmentService {
    void addDepartment(Department department);

    void updateDepartment(Department department);

    void deleteDepartmentById(Integer id);

    List<DepartmentVO> pageQuery(DepartmentForm departmentForm, Query query);

    int pageQueryCount(DepartmentForm departmentForm, Query query);
}
