package com.thesis.controller;

import com.thesis.entity.Department;
import com.thesis.entity.Teacher;
import com.thesis.form.DepartmentForm;
import com.thesis.form.TeacherForm;
import com.thesis.service.TeacherService;
import com.thesis.utils.Page;
import com.thesis.utils.Query;
import com.thesis.utils.R;
import com.thesis.validator.Assert;
import com.thesis.validator.ValidatorUtils;
import com.thesis.validator.group.AddGroup;
import com.thesis.validator.group.UpdateGroup;
import com.thesis.vo.DepartmentVO;
import com.thesis.vo.TeacherVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Validator;
import java.util.List;

/**
 * @Author LeiPeng
 * @Date 2020/1/26 - 11:07
 */
@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    TeacherService teacherService;

    /**
     * 新增教师
     * @param teacher 教师信息
     * @return 是否成功
     */
    @PostMapping("/addTeacher")
    public R addTeacher(@RequestBody Teacher teacher){
        ValidatorUtils.validate(teacher, AddGroup.class);
        teacherService.addTeacher(teacher);
        return R.ok();
    }

    /**
     * 通过教师id删除教师信息
     *
     * @param id 教师id
     * @return 是否成功
     */
    @PostMapping("/deleteTeacherById/{id}")
    public R deleteTeacherById(@PathVariable(name = "id") Integer id) {
        Assert.isNull(id, "id不能为空");
        teacherService.deleteTeacherById(id);
        return R.ok();
    }

    /**
     * 修改教师信息
     *
     * @param teacher 教师信息
     * @return 修改是否成功
     */
    @PostMapping("/updateTeacher")
    public R updateTeacher(@RequestBody Teacher teacher) {
        ValidatorUtils.validate(teacher, UpdateGroup.class);
        teacherService.updateTeacher(teacher);
        return R.ok();
    }

    /**
     * 分页查询
     * @param teacherForm 查询条件
     * @param curPage 当前页
     * @param limit 页面大小
     * @return 返回是否成功
     */
    @PostMapping("/pageQuery")
    public R pageQuery(TeacherForm teacherForm, Integer curPage, Integer limit){
        Query query = new Query(curPage, limit);
        List<TeacherVO> teacherVOList = teacherService.pageQuery(teacherForm, query);
        int total = teacherService.pageQueryCount(teacherForm, query);
        Page teacherPage = new Page(query, total, teacherVOList);
        return R.ok().put("teacherPage", teacherPage);
    }
}
