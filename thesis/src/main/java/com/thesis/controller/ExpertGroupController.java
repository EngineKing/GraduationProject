package com.thesis.controller;

import com.thesis.entity.Department;
import com.thesis.entity.ExpertGroup;
import com.thesis.entity.Teacher;
import com.thesis.form.DepartmentForm;
import com.thesis.form.ExpertGroupForm;
import com.thesis.service.ExpertGroupService;
import com.thesis.service.TeacherService;
import com.thesis.utils.Page;
import com.thesis.utils.Query;
import com.thesis.utils.R;
import com.thesis.validator.Assert;
import com.thesis.validator.ValidatorUtils;
import com.thesis.validator.group.AddGroup;
import com.thesis.vo.DepartmentVO;
import com.thesis.vo.ExpertGroupVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author LeiPeng
 * @Date 2020/1/27 - 17:23
 */
@RestController
@RequestMapping("/expertGroup")
public class ExpertGroupController {
    @Autowired
    private ExpertGroupService expertGroupService;

    /**
     * 新增专家组信息
     *
     * @param expertGroup 专家组信息
     * @return 是否成功
     */
    @PostMapping("/add")
    public R add(@RequestBody ExpertGroup expertGroup) {
        ValidatorUtils.validate(expertGroup, AddGroup.class);
        expertGroupService.add(expertGroup);
        return R.ok();
    }

    /**
     * 通过专家组id删除专家组信息
     *
     * @param id 专家组id
     * @return 是否成功
     */
    @PostMapping("/delete")
    public R delete(Integer id) {
        Assert.isNull(id, "id不能为空");
        expertGroupService.delete(id);
        return R.ok();
    }

    /**
     * 修改专家组信息
     *
     * @param expertGroup 专家组信息
     * @return 修改是否成功
     */
    @PostMapping("/update")
    public R update(@RequestBody ExpertGroup expertGroup) {
        ValidatorUtils.validate(expertGroup, AddGroup.class);
        expertGroupService.update(expertGroup);
        return R.ok();
    }

    /**
     * 分页查询
     * @param expertGroupForm 查询条件
     * @return 返回是否成功
     */
    @PostMapping("/pageQuery")
    public R pageQuery(@RequestBody ExpertGroupForm expertGroupForm){
        Query query = new Query(expertGroupForm.getCurPage(), expertGroupForm.getLimit());
        List<ExpertGroupVO> expertGroupVOList = expertGroupService.pageQuery(expertGroupForm, query);
        int total = expertGroupService.pageQueryCount(expertGroupForm, query);
        Page expertGroupPage = new Page(query, total, expertGroupVOList);
        return R.ok().put("expertGroupPage", expertGroupPage);
    }

    @GetMapping("/getLeaders")
    public R getLeaders(){
        List<Teacher> leaders = expertGroupService.getLeaders();
        return R.ok().put("leaders", leaders);
    }
}
