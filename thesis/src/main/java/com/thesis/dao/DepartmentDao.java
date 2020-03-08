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
    void add(Department department);

    Department get(@Param("departmentId") Integer id);

    void delete(@Param("departmentId") Integer id);

    void update(Department department);

    List<Department> pageQuery(@Param("departmentForm") DepartmentForm departmentForm, @Param("query") Query query);

    int pageQueryCount(@Param("departmentForm") DepartmentForm departmentForm, @Param("query") Query query);

    Department getByName(@Param("departmentName") String name);

    List<Department> getAll();
}
