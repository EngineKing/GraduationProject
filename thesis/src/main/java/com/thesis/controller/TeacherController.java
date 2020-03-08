package com.thesis.controller;


import com.alibaba.fastjson.JSONObject;
import com.thesis.entity.*;
import com.thesis.form.StudentTaskForm;
import com.thesis.form.TeacherForm;
import com.thesis.form.TeacherTaskForm;
import com.thesis.service.TeacherService;
import com.thesis.utils.*;
import com.thesis.validator.Assert;
import com.thesis.validator.ValidatorUtils;
import com.thesis.validator.group.AddGroup;
import com.thesis.validator.group.UpdateGroup;
import com.thesis.vo.StudentResultVO;
import com.thesis.vo.TaskVO;
import com.thesis.vo.TeacherVO;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
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

    private static String[] templateTitle = new String[]{"账号", "编号", "姓名", "性别", "年龄", "电话号码", "邮箱", "职称", "简介",
            "所属学科"};

    /**
     * 新增教师
     *
     * @param userAndTeacher 教师信息
     * @return 是否成功
     */
    @PostMapping("/add")
    public R add(@RequestBody UserAndTeacher userAndTeacher) {
        ValidatorUtils.validate(userAndTeacher, AddGroup.class);
        teacherService.add(userAndTeacher);
        return R.ok();
    }

    /**
     * 通过教师id删除教师信息
     *
     * @param id 教师id
     * @return 是否成功
     */
    @PostMapping("/delete")
    public R delete(Integer id) {
        Assert.isNull(id, "id不能为空");
        teacherService.delete(id);
        return R.ok();
    }

    /**
     * 修改教师信息
     *
     * @param teacher 教师信息
     * @return 修改是否成功
     */
    @PostMapping("/update")
    public R update(@RequestBody Teacher teacher) {
        ValidatorUtils.validate(teacher, UpdateGroup.class);
        teacherService.update(teacher);
        return R.ok();
    }

    /**
     * 分页查询
     *
     * @param teacherForm 查询条件
     * @return 返回是否成功
     */
    @PostMapping("/pageQuery")
    public R pageQuery(@RequestBody TeacherForm teacherForm) {
        Query query = new Query(teacherForm.getCurPage(), teacherForm.getLimit());
        List<TeacherVO> teacherVOList = teacherService.pageQuery(teacherForm, query);
        int total = teacherService.pageQueryCount(teacherForm, query);
        Page teacherPage = new Page(query, total, teacherVOList);
        return R.ok().put("teacherPage", teacherPage);
    }

    /**
     * 获取所有导师信息
     *
     * @return 导师信息
     */
    @GetMapping("/getAll")
    public R getAll() {
        List<Teacher> teachers = teacherService.getAll();
        return R.ok().put("teachers", teachers);
    }

    /**
     * 将导师与任务关联起来
     *
     * @param teacherTaskForm 导师id和任务id
     * @return 是否成功
     */
    @PostMapping("/related2Task")
    public R related2Task(@RequestBody TeacherTaskForm teacherTaskForm) {
        Assert.isNull(teacherTaskForm, "导师信息不能为空");
        teacherService.related2Task(teacherTaskForm);
        return R.ok();
    }

    /**
     * 下载导入导师与任务关联的模板模板
     *
     * @param request  请求
     * @param response 响应
     */
    @GetMapping("/downloadTemplate2Related")
    public void downloadTemplate2Related(HttpServletRequest request, HttpServletResponse response) {
        String fileName = "关联导师信息模板";
        List<List<String>> contents = new ArrayList<>();
        String[] templateTitle2Related = new String[]{"编号", "姓名", "性别", "年龄", "电话号码", "邮箱", "职称", "简介",
                "所属学科"};
        String[] content = new String[]{"100xx", "zhangsan", "男", "34", "17796407483", "zhangsan@qq.com", "教授", "2016" +
                "级4班", "外科"};
        contents.add(Arrays.asList(content));
        Workbook workbook = ExcelUtil.getTemplate(fileName, ExcelUtil.getTitle(templateTitle2Related), contents);
        FileUtil.responseToExcel(fileName, workbook, response, request);
    }

    /**
     * 通过导入的方式关联学生与任务
     *
     * @param teacherTaskForm 教师任务信息
     * @return
     */
    @PostMapping("/related2TaskByExcel")
    public R related2TaskByExcel(@RequestBody TeacherTaskForm teacherTaskForm) {
        teacherService.related2TaskByExcel(teacherTaskForm);
        return R.ok();
    }

    /**
     * 任务审核的页面查询
     *
     * @param userId 用户id
     * @param title  任务标题
     * @return 查询结果
     */
    @PostMapping("/query")
    public R query(@RequestParam Integer userId, @RequestParam String title) {
        List<TaskVO> taskVOS = teacherService.query(userId, title);
        return R.ok().put("taskVOS", taskVOS);
    }

    /**
     * 通过任务id和导师id获取学生提交的结果信息
     *
     * @param request 包含导师id
     * @param taskId  任务id
     * @return 学生提交结果信息
     */
    @PostMapping("/getTaskResult")
    public R getTaskResult(HttpServletRequest request, Integer taskId) {
        Integer userId = Integer.valueOf(request.getHeader("userId"));
        List<StudentResultVO> studentResultVOS = teacherService.getTaskResult(userId, taskId);
        return R.ok().put("studentResultVOS", studentResultVOS);
    }

    /**
     * 有条件的查询学生提交的结果信息
     *
     * @param request 包含导师id
     * @param taskId  任务id
     * @param number  学生学号
     * @param name    学生姓名
     * @return 查询结果
     */
    @PostMapping("/getTaskResultWithCondition")
    public R getTaskResultWithCondition(HttpServletRequest request, Integer taskId, String number, String name) {
        Integer userId = Integer.valueOf(request.getHeader("userId"));
        List<StudentResultVO> studentResultVOS = teacherService.getTaskResultWithCondition(userId, taskId, number,
                name);
        return R.ok().put("studentResultVOS", studentResultVOS);
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
        teacherService.pass(taskResultId);
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
    public R fail(Integer taskResultId, String auditOpinion) {
        teacherService.fail(taskResultId, auditOpinion);
        return R.ok();
    }

    /**
     * 下载模板
     *
     * @param request  请求
     * @param response 响应
     */
    @GetMapping("/downloadTemplate")
    public void downloadTemplate(HttpServletRequest request, HttpServletResponse response) {
        String fileName = "导入导师信息模板";
        List<List<String>> contents = new ArrayList<>();
        String[] content = new String[]{"adminxx", "10010", "张三", "男", "32", "138XXXXXXXX", "zhangsan@qq.com", "讲师",
                "张三导师研究..."
                , "外科"};
        contents.add(Arrays.asList(content));
        Workbook workbook = ExcelUtil.getTemplate(fileName, ExcelUtil.getTitle(templateTitle), contents);
        FileUtil.responseToExcel(fileName, workbook, response, request);
    }

    /**
     * 批量导入
     *
     * @param userAndTeachers 用户导师信息
     * @return 是否成功
     */
    @PostMapping("/batchInsert")
    public R batchInsert(@RequestBody List<UserAndTeacher> userAndTeachers) {
        teacherService.batchInsert(userAndTeachers);
        return R.ok();
    }

    /**
     * 导出选中的导师信息到Excel
     *
     * @param response   响应
     * @param teacherIds 订单id组成的字符串
     */
    @GetMapping("/export")
    public void export(HttpServletResponse response, HttpServletRequest request, String teacherIds) {
        String fileName = "导师信息";
        Workbook workbook = teacherService.export(teacherIds);
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
        TeacherForm teacherForm = JSONObject.parseObject(condition, TeacherForm.class);
        String fileName = "导师信息";
        Workbook workbook = teacherService.allExport(teacherForm);
        FileUtil.responseToExcel(fileName, workbook, response, request);
    }
}
