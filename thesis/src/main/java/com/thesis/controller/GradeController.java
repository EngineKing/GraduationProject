package com.thesis.controller;

import com.thesis.entity.Department;
import com.thesis.entity.Grade;
import com.thesis.form.DepartmentForm;
import com.thesis.form.GradeForm;
import com.thesis.service.GradeService;
import com.thesis.utils.Page;
import com.thesis.utils.Query;
import com.thesis.utils.R;
import com.thesis.validator.Assert;
import com.thesis.validator.ValidatorUtils;
import com.thesis.validator.group.AddGroup;
import com.thesis.vo.DepartmentVO;
import com.thesis.vo.GradeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author LeiPeng
 * @Date 2020/1/23 - 11:28
 */
@RestController
@RequestMapping("/grade")
public class GradeController {
    @Autowired
    GradeService gradeService;

    /**
     * 新增班级信息
     *
     * @param grade 班级信息
     * @return 是否成功
     */
    @PostMapping("/add")
    public R add(@RequestBody Grade grade) {
        ValidatorUtils.validate(grade, AddGroup.class);
        gradeService.add(grade);
        return R.ok();
    }

//    /**
//     * 通过班级id删除班级信息
//     *
//     * @param id 班级id
//     * @return 是否成功
//     */
//    @PostMapping("/deleteGradeById/{id}")
//    public R deleteGradeById(@PathVariable(name = "id") Integer id) {
//        Assert.isNull(id, "id不能为空");
//        gradeService.deleteGradeById(id);
//        return R.ok();
//    }
//
//    /**
//     * 修改班级信息
//     *
//     * @param grade 部门信息
//     * @return 修改是否成功
//     */
//    @PostMapping("/updateGrade")
//    public R updateGrade(@RequestBody Grade grade) {
//        ValidatorUtils.validate(grade, AddGroup.class);
//        gradeService.updateGrade(grade);
//        return R.ok();
//    }
//
//    /**
//     * 分页查询
//     * @param gradeForm 查询条件
//     * @param curPage 当前页
//     * @param limit 页面大小
//     * @return 返回是否成功
//     */
//    @PostMapping("/pageQuery")
//    public R pageQuery(GradeForm gradeForm, Integer curPage, Integer limit){
//        Query query = new Query(curPage, limit);
//        List<GradeVO> gradeVOList = gradeService.pageQuery(gradeForm, query);
//        int total = gradeService.pageQueryCount(gradeForm, query);
//        Page gradePage = new Page(query, total, gradeVOList);
//        return R.ok().put("gradePage", gradePage);
//    }

    @PostMapping("/getAll")
    public R getAll(){
        List<Grade> grades = gradeService.getAll();
        return R.ok().put("grades", grades);
    }
}
