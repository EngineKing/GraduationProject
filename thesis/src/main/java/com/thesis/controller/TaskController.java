package com.thesis.controller;

import com.thesis.entity.Task;
import com.thesis.form.TaskForm;
import com.thesis.service.TaskService;
import com.thesis.utils.Page;
import com.thesis.utils.Query;
import com.thesis.utils.R;
import com.thesis.validator.Assert;
import com.thesis.validator.ValidatorUtils;
import com.thesis.validator.group.AddGroup;
import com.thesis.vo.TaskVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author LeiPeng
 * @Date 2020/1/26 - 21:47
 */
@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    TaskService taskService;

    /**
     * 新增任务
     * @param task 任务
     * @return 是否成功
     */
    @PostMapping("/addTask")
    public R addTask(@RequestBody Task task){
        ValidatorUtils.validate(task, AddGroup.class);
        taskService.addDepartment(task);
        return R.ok();
    }

    /**
     * 通过任务id删除任务信息
     *
     * @param id 任务id
     * @return 是否成功
     */
    @PostMapping("/deleteTaskById/{id}")
    public R deleteTaskById(@PathVariable(name = "id") Integer id) {
        Assert.isNull(id, "id不能为空");
        taskService.deleteTaskById(id);
        return R.ok();
    }

    /**
     * 修改任务信息
     *
     * @param task 部门信息
     * @return 修改是否成功
     */
    @PostMapping("/updateTask")
    public R updateTask(@RequestBody Task task) {
        ValidatorUtils.validate(task, AddGroup.class);
        taskService.updateTask(task);
        return R.ok();
    }

    /**
     * 分页查询
     * @param taskForm 查询条件
     * @param curPage 当前页
     * @param limit 页面大小
     * @return 返回是否成功
     */
    @PostMapping("/pageQuery")
    public R pageQuery(TaskForm taskForm, Integer curPage, Integer limit){
        Query query = new Query(curPage, limit);
        List<TaskVO> taskVOList = taskService.pageQuery(taskForm, query);
        int total = taskService.pageQueryCount(taskForm, query);
        Page taskPage = new Page(query, total, taskVOList);
        return R.ok().put("taskPage", taskPage);
    }
}
