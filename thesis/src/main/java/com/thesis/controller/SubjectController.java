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
    @PostMapping("/add")
    public R add(@RequestBody Subject subject) {
        ValidatorUtils.validate(subject, AddGroup.class);
        subjectService.add(subject);
        return R.ok();
    }

    /**
     * 通过学科id删除学科信息
     *
     * @param id 学科id
     * @return 是否成功
     */
    @PostMapping("/delete")
    public R delete(Integer id) {
        Assert.isNull(id, "id不能为空");
        subjectService.delete(id);
        return R.ok();
    }

    /**
     * 修改学科信息
     *
     * @param subject 信息
     * @return 修改是否成功
     */
    @PostMapping("/update")
    public R update(@RequestBody Subject subject) {
        ValidatorUtils.validate(subject, UpdateGroup.class);
        subjectService.update(subject);
        return R.ok();
    }

    /**
     * 分页查询
     *
     * @param subjectForm 查询条件
     * @return 返回是否成功
     */
    @PostMapping("/pageQuery")
    public R pageQuery(@RequestBody SubjectForm subjectForm) {
        Query query = new Query(subjectForm.getCurPage(), subjectForm.getLimit());
        List<SubjectVO> subjectVOList = subjectService.pageQuery(subjectForm, query);
        int total = subjectService.pageQueryCount(subjectForm, query);
        Page subjectPage = new Page(query, total, subjectVOList);
        return R.ok().put("subjectPage", subjectPage);
    }

    /**
     * 获取学科信息
     * @return
     */
    @PostMapping("/getAll")
    public R getAll(){
        List<Subject> subjects = subjectService.getAll();
        return R.ok().put("subjects", subjects);
    }
}
