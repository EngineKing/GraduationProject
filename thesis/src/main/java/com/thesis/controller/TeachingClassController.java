package com.thesis.controller;

import com.thesis.entity.TeachingClass;
import com.thesis.form.TeachingClassForm;
import com.thesis.service.TeachingClassService;
import com.thesis.utils.Page;
import com.thesis.utils.Query;
import com.thesis.utils.R;
import com.thesis.validator.Assert;
import com.thesis.validator.ValidatorUtils;
import com.thesis.validator.group.AddGroup;
import com.thesis.vo.TeachingClassVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author LeiPeng
 * @Date 2020/1/23 - 19:31
 */
@RestController
@RequestMapping("/teachingClass")
public class TeachingClassController {
    @Autowired
    TeachingClassService teachingClassService;

    /**
     * 新增班级信息
     *
     * @param teachingClass 班级信息
     * @return 是否成功
     */
    @PostMapping("/addTeachingClass")
    public R addTeachingClass(@RequestBody TeachingClass teachingClass) {
        ValidatorUtils.validate(teachingClass, AddGroup.class);
        teachingClassService.addTeachingClass(teachingClass);
        return R.ok();
    }

    /**
     * 通过班级id删除班级信息
     *
     * @param id 班级id
     * @return 是否成功
     */
    @PostMapping("/deleteDepartment/{id}")
    public R deleteTeachingClassById(@PathVariable(name = "id") Integer id) {
        Assert.isNull(id, "id不能为空");
        teachingClassService.deleteTeachingClassById(id);
        return R.ok();
    }

    /**
     * 修改班级信息
     *
     * @param teachingClass 班级信息
     * @return 修改是否成功
     */
    @PostMapping("/updateTeachingClass")
    public R updateTeachingClass(@RequestBody TeachingClass teachingClass) {
        ValidatorUtils.validate(teachingClass, AddGroup.class);
        teachingClassService.updateTeachingClass(teachingClass);
        return R.ok();
    }

    /**
     * 分页查询
     * @param teachingClassForm 查询条件
     * @param curPage 当前页
     * @param limit 页面大小
     * @return 返回是否成功
     */
    @PostMapping("/pageQuery")
    public R pageQuery(TeachingClassForm teachingClassForm, Integer curPage, Integer limit){
        Query query = new Query(curPage, limit);
        List<TeachingClassVO> teachingClassVOList = teachingClassService.pageQuery(teachingClassForm, query);
        int total = teachingClassService.pageQueryCount(teachingClassForm, query);
        Page teachingClassPage = new Page(query, total, teachingClassVOList);
        return R.ok().put("teachingClassPage", teachingClassPage);
    }
}
