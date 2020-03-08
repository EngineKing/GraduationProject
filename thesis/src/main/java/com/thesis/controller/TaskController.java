package com.thesis.controller;

import com.thesis.entity.Annex;
import com.thesis.entity.Task;
import com.thesis.form.TaskForm;
import com.thesis.service.TaskService;
import com.thesis.utils.*;
import com.thesis.validator.Assert;
import com.thesis.validator.ValidatorUtils;
import com.thesis.validator.group.AddGroup;
import com.thesis.vo.TaskVO;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
    @PostMapping("/add")
    public R add(@RequestBody Task task){
        ValidatorUtils.validate(task, AddGroup.class);
        taskService.add(task);
        return R.ok();
    }

    /**
     * 通过任务id删除任务信息
     *
     * @param id 任务id
     * @return 是否成功
     */
    @PostMapping("/delete")
    public R delete(Integer id) {
        Assert.isNull(id, "id不能为空");
        taskService.delete(id);
        return R.ok();
    }

    /**
     * 修改任务信息
     *
     * @param task 部门信息
     * @return 修改是否成功
     */
    @PostMapping("/update")
    public R update(@RequestBody Task task) {
        ValidatorUtils.validate(task, AddGroup.class);
        taskService.update(task);
        return R.ok();
    }

    /**
     * 分页查询
     * @param taskForm 查询条件
     * @return 返回是否成功
     */
    @PostMapping("/pageQuery")
    public R pageQuery(@RequestBody TaskForm taskForm){
        Query query = new Query(taskForm.getCurPage(), taskForm.getLimit());
        List<TaskVO> taskVOList = taskService.pageQuery(taskForm, query);
        int total = taskService.pageQueryCount(taskForm, query);
        Page taskPage = new Page(query, total, taskVOList);
        return R.ok().put("taskPage", taskPage);
    }

    /**
     * 获取所有的任务
     * @return
     */
    @GetMapping("/getAll")
    public R getAll(){
        List<Task> tasks = taskService.getAll();
        return R.ok().put("tasks", tasks);
    }

    /**
     * 获取所有的毕业设计任务
     * @return
     */
    @GetMapping("/getAllPTasks")
    public R getAllPTasks(){
        List<Task> pTasks = taskService.getAllPTasks();
        return R.ok().put("pTasks", pTasks);
    }

    /**
     * 通过任务id获取关联的导师id
     * @param id 任务id
     * @return 导师ids
     */
    @PostMapping("/getTeacherIdByTaskId")
    public R getTeacherIdByTaskId(Integer id){
        Assert.isNull(id, "任务ID不能为空");
        List<Integer> teacherIds = taskService.getTeacherIdByTaskId(id);
        return R.ok().put("teacherIds", teacherIds);
    }

    /**
     * 通过任务id获取关联的学生id
     * @param id 任务id
     * @return 学生ids
     */
    @PostMapping("/getStudentIdByTaskId")
    public R getStudentIdByTaskId(Integer id){
        Assert.isNull(id, "任务ID不能为空");
        List<Integer> studentIds = taskService.getStudentIdByTaskId(id);
        return R.ok().put("studentIds", studentIds);
    }

    /**
     * 上传附件
     * @param file 附件
     * @return 是否成功
     */
    @PostMapping("/upload")
    public R upload(@RequestParam Map<String, String> map, @RequestParam("file") MultipartFile file){
        Integer taskId = Integer.valueOf(map.get("taskId"));
        taskService.upload(taskId, file);
        return R.ok();
    }

    /**
     * 根据任务id查询附件
     * @param taskId 任务id
     * @return 附件信息
     */
    @PostMapping("/getAnnex")
    public R getAnnex(Integer taskId) {
        Annex annex = taskService.getAnnex(taskId);
        return R.ok().put("annex", annex);
    }

    /**
     * 下载附件
     *
     * @param taskId   任务id
     * @param response response
     * @param request  请求
     */
    @GetMapping("/download")
    public void download(Integer taskId, HttpServletResponse response, HttpServletRequest request) {
        Annex annex = taskService.getAnnex(taskId);
        File file = new File(annex.getUrl());
        FileUtil.exist(file);
        FileUtil.responseToFile(file, response, request);
    }
}
