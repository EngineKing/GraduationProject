package com.thesis.dao;

import com.thesis.entity.Department;
import com.thesis.form.DepartmentForm;
import com.thesis.utils.Query;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author LeiPeng
 * @Date 2020/1/14 - 13:57
 */
public interface DepartmentDao {
    void addDepartment(Department department);

    Department getDepartmentById(@Param("departmentId") Integer id);

    void deleteDepartmentById(@Param("departmentId") Integer id);

    void updateDepartment(Department department);

    List<Department> pageQuery(@Param("departmentForm") DepartmentForm departmentForm, @Param("query") Query query);

    int pageQueryCount(@Param("departmentForm") DepartmentForm departmentForm, @Param("query") Query query);

    Department getDepartmentByName(@Param("departmentName") String name);
}
