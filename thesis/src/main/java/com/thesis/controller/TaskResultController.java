package com.thesis.controller;

import com.thesis.entity.Student;
import com.thesis.entity.TaskResult;
import com.thesis.form.StudentForm;
import com.thesis.form.TaskResultForm;
import com.thesis.service.TaskResultService;
import com.thesis.utils.Page;
import com.thesis.utils.Query;
import com.thesis.utils.R;
import com.thesis.validator.Assert;
import com.thesis.validator.ValidatorUtils;
import com.thesis.validator.group.AddGroup;
import com.thesis.vo.StudentVO;
import com.thesis.vo.TaskResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author LeiPeng
 * @Date 2020/1/28 - 14:15
 */
@RestController
@RequestMapping("/taskResult")
public class TaskResultController {
    @Autowired
    TaskResultService taskResultService;

    /**
     * 新增任务结果信息
     *
     * @param taskResult 任务结果信息
     * @return 是否成功
     */
    @PostMapping("/addTaskResult")
    public R addTaskResult(@RequestBody TaskResult taskResult) {
        ValidatorUtils.validate(taskResult, AddGroup.class);
        taskResultService.addTaskResult(taskResult);
        return R.ok();
    }

    /**
     * 通过任务结果id删除任务信息
     *
     * @param id 任务id
     * @return 是否成功
     */
    @PostMapping("/deleteTaskResult/{id}")
    public R deleteTaskResultById(@PathVariable(name = "id") Integer id) {
        Assert.isNull(id, "id不能为空");
        taskResultService.deleteTaskResultById(id);
        return R.ok();
    }

    /**
     * 修改任务结果信息
     *
     * @param taskResult 任务结果信息
     * @return 修改是否成功
     */
    @PostMapping("/updateTaskResult")
    public R updateTaskResult(@RequestBody TaskResult taskResult) {
        ValidatorUtils.validate(taskResult, AddGroup.class);
        taskResultService.updateTaskResult(taskResult);
        return R.ok();
    }

    /**
     * 分页查询
     * @param taskResultForm 查询条件
     * @param curPage 当前页
     * @param limit 页面大小
     * @return 返回是否成功
     */
    @PostMapping("/pageQuery")
    public R pageQuery(TaskResultForm taskResultForm, Integer curPage, Integer limit){
        Query query = new Query(curPage, limit);
        List<TaskResultVO> taskResultVOList = taskResultService.pageQuery(taskResultForm, query);
        int total = taskResultService.pageQueryCount(taskResultForm, query);
        Page taskResultPage = new Page(query, total, taskResultVOList);
        return R.ok().put("taskResultPage", taskResultPage);
    }
}
