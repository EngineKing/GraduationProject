package com.thesis.controller;

import com.alibaba.fastjson.JSONObject;
import com.thesis.entity.Annex;
import com.thesis.entity.TaskResult;
import com.thesis.form.StudentForm;
import com.thesis.form.TaskResultForm;
import com.thesis.service.TaskResultService;
import com.thesis.utils.FileUtil;
import com.thesis.utils.Page;
import com.thesis.utils.Query;
import com.thesis.utils.R;
import com.thesis.validator.Assert;
import com.thesis.validator.ValidatorUtils;
import com.thesis.validator.group.AddGroup;
import com.thesis.vo.StudentResultVO;
import com.thesis.vo.TaskResultVO;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
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
    @PostMapping("/add")
    public R add(@RequestBody TaskResult taskResult) {
        ValidatorUtils.validate(taskResult, AddGroup.class);
        taskResultService.add(taskResult);
        return R.ok();
    }

//    /**
//     * 通过任务结果id删除任务信息
//     *
//     * @param id 任务id
//     * @return 是否成功
//     */
//    @PostMapping("/delete")
//    public R delete(Integer id) {
//        Assert.isNull(id, "id不能为空");
//        taskResultService.delete(id);
//        return R.ok();
//    }

//    /**
//     * 修改任务结果
//     * @param taskResultId 任务结果id
//     * @param result 任务结果
//     * @return 是否成功
//     */
//    @PostMapping("/update")
//    public R update(Integer taskResultId, Integer result) {
//        taskResultService.update(taskResultId, result);
//        return R.ok();
//    }

    /**
     * 分页查询
     *
     * @param taskResultForm 查询条件
     * @return 返回是否成功
     */
//    @PostMapping("/pageQuery")
//    public R pageQuery(@RequestBody TaskResultForm taskResultForm) {
//        Query query = new Query(taskResultForm.getCurPage(), taskResultForm.getLimit());
//        List<TaskResultVO> taskResultVOList = taskResultService.pageQuery(taskResultForm, query);
//        int total = taskResultService.pageQueryCount(taskResultForm, query);
//        Page taskResultPage = new Page(query, total, taskResultVOList);
//        return R.ok().put("taskResultPage", taskResultPage);
//    }

    /**
     * 根据任务结果id获取学生提交的结果
     * @param taskResultId 任务结果id
     * @param response 响应
     * @param request 请求
     */
    @GetMapping("/getAnnex")
    public void getAnnex(Integer taskResultId, HttpServletResponse response, HttpServletRequest request) {
        Annex annex = taskResultService.getAnnex(taskResultId);
        File file = new File(annex.getUrl());
        FileUtil.exist(file);
        FileUtil.responseToFile(file, response, request);
    }

    /**
     * 审核学生任务结果信息 通过
     * 通过任务结果id
     *
     * @param taskResultId 任务结果id
     * @return 返回是否成功
     */
    @PostMapping("/pass")
    public R pass(@RequestParam Integer taskResultId) {
        taskResultService.pass(taskResultId);
        return R.ok();
    }

    /**
     * 审核学生任务结果信息 不通过
     * 通过任务结果id
     *
     * @param taskResultId 任务结果id
     * @return 返回是否成功
     */
    @PostMapping("/fail")
    public R fail(Integer taskResultId) {
        taskResultService.fail(taskResultId);
        return R.ok();
    }

//    /**
//     * 通过任务id获取提交了结果学生任务信息
//     * @param taskId 任务id
//     * @return 学生结果信息
//     */
//    @PostMapping("/getTaskResult")
//    public R getTaskResult(Integer taskId){
//        List<StudentResultVO> studentResultVOs = taskResultService.getTaskResult(taskId);
//        return R.ok().put("studentResultVOs", studentResultVOs);
//    }
//    /**
//     * 有条件的查询学生提交的结果信息
//     * @param taskId 任务id
//     * @param number 学生学号
//     * @param name 学生姓名
//     * @return 查询结果
//     */
//    @PostMapping("/getTaskResultWithCondition")
//    public R getTaskResultWithCondition(Integer taskId, String number, String name){
//        List<StudentResultVO> studentResultVOS = taskResultService.getTaskResultWithCondition(taskId, number, name);
//        return R.ok().put("studentResultVOS", studentResultVOS);
//    }

    @PostMapping("/pageQuery")
    public R pageQuery(@RequestBody TaskResultForm taskResultForm){
        Query query = new Query(taskResultForm.getCurPage(), taskResultForm.getLimit());
        List<TaskResultVO> taskResultVOList = taskResultService.pageQuery(taskResultForm, query);
        int total = taskResultService.pageQueryCount(taskResultForm, query);
        Page taskResultPage = new Page(query, total, taskResultVOList);
        return R.ok().put("taskResultPage", taskResultPage);
    }

    /**
     * 导出选中的学生信息到Excel
     *
     * @param response   响应
     * @param taskResultIds 订单id组成的字符串
     */
    @GetMapping("/export")
    public void export(HttpServletResponse response, HttpServletRequest request, String taskResultIds) {
        String fileName = "学生信息";
        Workbook workbook = taskResultService.export(taskResultIds);
        FileUtil.responseToExcel(fileName, workbook, response, request);
    }

    /**
     * 查询后全部导出
     *
     * @param response  响应
     * @param request   请求
     * @param condition 条件
     */
    @GetMapping("/allExport")
    public void allExport(HttpServletResponse response, HttpServletRequest request, String condition) {
        TaskResultForm taskResultForm = JSONObject.parseObject(condition, TaskResultForm.class);
        String fileName = "学生信息";
        Workbook workbook = taskResultService.allExport(taskResultForm);
        FileUtil.responseToExcel(fileName, workbook, response, request);
    }
}
