package com.thesis.controller;

import com.thesis.entity.Subject;
import com.thesis.form.SubjectForm;
import com.thesis.service.SubjectService;
import com.thesis.utils.Page;
import com.thesis.utils.Query;
import com.thesis.utils.R;
import com.thesis.validator.Assert;
import com.thesis.validator.ValidatorUtils;
import com.thesis.validator.group.AddGroup;
import com.thesis.validator.group.UpdateGroup;
import com.thesis.vo.SubjectVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author LeiPeng
 * @Date 2020/1/23 - 9:18
 */
@RestController
@RequestMapping("/subject")
public class SubjectController {
    @Autowired
    SubjectService subjectService;

    /**
     * 新增学科信息
     *
     * @param subject 学科信息
     * @return 是否成功
     */
    @PostMapping("/addSubject")
    public R addSubject(@RequestBody Subject subject) {
        ValidatorUtils.validate(subject, AddGroup.class);
        subjectService.addSubject(subject);
        return R.ok();
    }

    /**
     * 通过学科id删除学科信息
     *
     * @param id 学科id
     * @return 是否成功
     */
    @PostMapping("/deleteSubjectById/{id}")
    public R deleteSubjectById(@PathVariable(name = "id") Integer id) {
        Assert.isNull(id, "id不能为空");
        subjectService.deleteSubjectById(id);
        return R.ok();
    }

    /**
     * 修改学科信息
     *
     * @param subject 信息
     * @return 修改是否成功
     */
    @PostMapping("/updateSubject")
    public R updateSubject(@RequestBody Subject subject) {
        ValidatorUtils.validate(subject, UpdateGroup.class);
        subjectService.updateSubject(subject);
        return R.ok();
    }

    /**
     * 分页查询
     *
     * @param subjectForm 查询条件
     * @param curPage     当前页
     * @param limit       页面大小
     * @return 返回是否成功
     */
    @PostMapping("/pageQuery")
    public R pageQuery(SubjectForm subjectForm, Integer curPage, Integer limit) {
        Query query = new Query(curPage, limit);
        List<SubjectVO> subjectVOList = subjectService.pageQuery(subjectForm, query);
        int total = subjectService.pageQueryCount(subjectForm, query);
        Page subjectPage = new Page(query, total, subjectVOList);
        return R.ok().put("subjectPage", subjectPage);
    }
}
