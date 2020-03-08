package com.thesis.controller;

import com.alibaba.fastjson.JSONObject;
import com.thesis.entity.Student;
import com.thesis.entity.UserAndStudent;
import com.thesis.entity.UserAndTeacher;
import com.thesis.form.StudentForm;
import com.thesis.form.StudentTaskForm;
import com.thesis.form.TeacherForm;
import com.thesis.service.StudentService;
import com.thesis.utils.*;
import com.thesis.validator.Assert;
import com.thesis.validator.ValidatorUtils;
import com.thesis.validator.group.AddGroup;
import com.thesis.vo.StudentVO;
import com.thesis.vo.TaskVO;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Author LeiPeng
 * @Date 2020/1/28 - 14:14
 */
@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;

    private static String[] templateTitle = new String[]{"账号", "学号", "姓名", "性别", "身份证号", "类型", "电话号码", "邮箱", "注册日期",
            "学制(年)", "班级", "学科"};
    /**
     * 新增学生信息
     *
     * @param userAndStudent 学生信息
     * @return 是否成功
     */
    @PostMapping("/add")
    public R add(@RequestBody UserAndStudent userAndStudent) {
        ValidatorUtils.validate(userAndStudent, AddGroup.class);
        studentService.add(userAndStudent);
        return R.ok();
    }

    /**
     * 通过学生id删除学生信息
     *
     * @param id 学生id
     * @return 是否成功
     */
    @PostMapping("/delete")
    public R delete(Integer id) {
        Assert.isNull(id, "id不能为空");
        studentService.delete(id);
        return R.ok();
    }

    /**
     * 修改学生信息
     *
     * @param student 学生信息
     * @return 修改是否成功
     */
    @PostMapping("/update")
    public R update(@RequestBody Student student) {
        ValidatorUtils.validate(student, AddGroup.class);
        studentService.update(student);
        return R.ok();
    }

    /**
     * 分页查询
     *
     * @param studentForm 查询条件
     * @return 返回是否成功
     */
    @PostMapping("/pageQuery")
    public R pageQuery(@RequestBody StudentForm studentForm) {
        Query query = new Query(studentForm.getCurPage(), studentForm.getLimit());
        List<StudentVO> studentVOList = studentService.pageQuery(studentForm, query);
        int total = studentService.pageQueryCount(studentForm, query);
        Page studentPage = new Page(query, total, studentVOList);
        return R.ok().put("studentPage", studentPage);
    }

    /**
     * 将任务与学生关联起来
     *
     * @param studentTaskForm 任务与学生id
     * @return
     */
    @PostMapping("/related2Task")
    public R related2Task(@RequestBody StudentTaskForm studentTaskForm) {
        studentService.related2Task(studentTaskForm);
        return R.ok();
    }

    /**
     * 下载导入学生与任务关联的模板模板
     *
     * @param request  请求
     * @param response 响应
     */
    @GetMapping("/downloadTemplate2Related")
    public void downloadTemplate2Related(HttpServletRequest request, HttpServletResponse response) {
        String fileName = "关联学生信息模板";
        List<List<String>> contents = new ArrayList<>();
        String[] templateTitle2Related = new String[]{"学号", "姓名", "性别", "身份证号", "类型", "电话号码", "邮箱", "注册日期",
                "学制(年)", "班级", "学科"};
        String[] content = new String[]{"20160811xx", "zhangsan", "男", "511025199810255956", "硕士研究生",
                "17796407483", "zhangsan@qq.com", "", "三年", "2016级4班", "外科"};
        contents.add(Arrays.asList(content));
        Workbook workbook = ExcelUtil.getTemplate(fileName, ExcelUtil.getTitle(templateTitle2Related), contents);
        FileUtil.responseToExcel(fileName, workbook, response, request);
    }

    /**
     * 通过导入的方式关联学生与任务
     * @param studentTaskForm 任务id
     * @return
     */
    @PostMapping("/related2TaskByExcel")
    public R related2TaskByExcel(@RequestBody StudentTaskForm studentTaskForm){
        studentService.related2TaskByExcel(studentTaskForm);
        return R.ok();
    }

    /**
     * 学生提交任务结果
     *
     * @param map  任务id、用户id
     * @param file 结果
     * @return 是否成功
     */
    @PostMapping("/submit")
    public R submit(@RequestParam Map<String, String> map, @RequestParam("file") MultipartFile file) {
        studentService.submit(map, file);
        return R.ok();
    }

    /**
     * 任务提交的页面查询
     *
     * @param userId 用户id
     * @param title  任务标题
     * @return 查询结果
     */
    @PostMapping("/query")
    public R query(@RequestParam Integer userId, @RequestParam String title) {
        List<TaskVO> taskVOS = studentService.query(userId, title);
        return R.ok().put("taskVOS", taskVOS);
    }

    @RequestMapping("/get")
    public R get(@RequestParam Integer studentId) {
        Student student = studentService.get(studentId);
        return R.ok().put("student", student);
    }

    /**
     * 下载模板
     *
     * @param request  请求
     * @param response 响应
     */
    @GetMapping("/downloadTemplate")
    public void downloadTemplate(HttpServletRequest request, HttpServletResponse response) {
        String fileName = "导入学生信息模板";
        List<List<String>> contents = new ArrayList<>();
        String[] content = new String[]{"adminxx", "20160811xx", "zhangsan", "男", "511025199810255956", "硕士研究生",
                "17796407483", "zhangsan@qq.com", "", "三年", "2016级4班", "外科"};
        contents.add(Arrays.asList(content));
        Workbook workbook = ExcelUtil.getTemplate(fileName, ExcelUtil.getTitle(templateTitle), contents);
        FileUtil.responseToExcel(fileName, workbook, response, request);
    }

    /**
     * 批量导入
     *
     * @param userAndStudents 用户学生信息
     * @return 是否成功
     */
    @PostMapping("/batchInsert")
    public R batchInsert(@RequestBody List<UserAndStudent> userAndStudents) {
        studentService.batchInsert(userAndStudents);
        return R.ok();
    }

    /**
     * 导出选中的学生信息到Excel
     *
     * @param response   响应
     * @param studentIds 订单id组成的字符串
     */
    @GetMapping("/export")
    public void export(HttpServletResponse response, HttpServletRequest request, String studentIds) {
        String fileName = "学生信息";
        Workbook workbook = studentService.export(studentIds);
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
        StudentForm studentForm = JSONObject.parseObject(condition, StudentForm.class);
        String fileName = "学生信息";
        Workbook workbook = studentService.allExport(studentForm);
        FileUtil.responseToExcel(fileName, workbook, response, request);
    }
}
