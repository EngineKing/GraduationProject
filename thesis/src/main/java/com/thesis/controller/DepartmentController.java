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

import java.util.ArrayList;
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
    @PostMapping("/add")
    public R add(@RequestBody Department department) {
        ValidatorUtils.validate(department, AddGroup.class);
        departmentService.add(department);
        return R.ok();
    }

    /**
     * 通过部门id删除部门信息
     *
     * @param id 部门id
     * @return 是否成功
     */
    @PostMapping("/delete")
    public R delete(Integer id) {
        Assert.isNull(id, "id不能为空");
        departmentService.delete(id);
        return R.ok();
    }

    /**
     * 修改部门信息
     *
     * @param department 部门信息
     * @return 修改是否成功
     */
    @PostMapping("/update")
    public R update(@RequestBody Department department) {
        ValidatorUtils.validate(department, AddGroup.class);
        departmentService.update(department);
        return R.ok();
    }

    /**
     * 分页查询
     * @param departmentForm 查询条件
     * @return 返回是否成功
     */
    @PostMapping("/pageQuery")
    public R pageQuery(@RequestBody DepartmentForm departmentForm){
        Query query = new Query(departmentForm.getCurPage(), departmentForm.getLimit());
        List<DepartmentVO> departmentList = departmentService.pageQuery(departmentForm, query);
        int total = departmentService.pageQueryCount(departmentForm, query);
        Page departmentPage = new Page(query, total, departmentList);
        return R.ok().put("departmentPage", departmentPage);
    }

    @PostMapping("/getAll")
    public R getAll(){
        List<Department> departments = departmentService.getAll();
        return R.ok().put("departments", departments);
    }
}
