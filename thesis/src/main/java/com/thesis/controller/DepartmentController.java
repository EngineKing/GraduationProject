package com.thesis.controller;

import com.thesis.entity.Department;
import com.thesis.form.DepartmentForm;
import com.thesis.service.DepartmentService;
import com.thesis.utils.Page;
import com.thesis.utils.Query;
import com.thesis.utils.R;
import com.thesis.validator.Assert;
import com.thesis.validator.ValidatorUtils;
import com.thesis.validator.group.AddGroup;
import com.thesis.vo.DepartmentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author LeiPeng
 * @Date 2020/1/14 - 13:46
 */
@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    /**
     * 新增部门信息
     *
     * @param department 部门信息
     * @return 是否成功
     */
    @PostMapping("/addDepartment")
    public R addDepartment(@RequestBody Department department) {
        ValidatorUtils.validate(department, AddGroup.class);
        departmentService.addDepartment(department);
        return R.ok();
    }

    /**
     * 通过部门id删除部门信息
     *
     * @param id 部门id
     * @return 是否成功
     */
    @PostMapping("/deleteDepartment/{id}")
    public R deleteDepartmentById(@PathVariable(name = "id") Integer id) {
        Assert.isNull(id, "id不能为空");
        departmentService.deleteDepartmentById(id);
        return R.ok();
    }

    /**
     * 修改部门信息
     *
     * @param department 部门信息
     * @return 修改是否成功
     */
    @PostMapping("/updateDepartment")
    public R updateDepartment(@RequestBody Department department) {
        ValidatorUtils.validate(department, AddGroup.class);
        departmentService.updateDepartment(department);
        return R.ok();
    }

    /**
     * 分页查询
     * @param departmentForm 查询条件
     * @param curPage 当前页
     * @param limit 页面大小
     * @return 返回是否成功
     */
    @PostMapping("/pageQuery")
    public R pageQuery(DepartmentForm departmentForm, Integer curPage, Integer limit){
        Query query = new Query(curPage, limit);
        List<DepartmentVO> departmentList = departmentService.pageQuery(departmentForm, query);
        int total = departmentService.pageQueryCount(departmentForm, query);
        Page departmentPage = new Page(query, total, departmentList);
        return R.ok().put("departmentPage", departmentPage);
    }
}
