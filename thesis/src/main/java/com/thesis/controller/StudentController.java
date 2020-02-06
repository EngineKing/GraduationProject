package com.thesis.controller;

import com.thesis.entity.Student;
import com.thesis.form.StudentForm;
import com.thesis.service.StudentService;
import com.thesis.utils.Page;
import com.thesis.utils.Query;
import com.thesis.utils.R;
import com.thesis.validator.Assert;
import com.thesis.validator.ValidatorUtils;
import com.thesis.validator.group.AddGroup;
import com.thesis.vo.StudentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author LeiPeng
 * @Date 2020/1/28 - 14:14
 */
@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;

    /**
     * 新增学生信息
     *
     * @param student 学生信息
     * @return 是否成功
     */
    @PostMapping("/addStudent")
    public R addStudent(@RequestBody Student student) {
        ValidatorUtils.validate(student, AddGroup.class);
        studentService.addStudent(student);
        return R.ok();
    }

    /**
     * 通过学生id删除学生信息
     *
     * @param id 学生id
     * @return 是否成功
     */
    @PostMapping("/deleteStudent/{id}")
    public R deleteStudentById(@PathVariable(name = "id") Integer id) {
        Assert.isNull(id, "id不能为空");
        studentService.deleteStudentById(id);
        return R.ok();
    }

    /**
     * 修改学生信息
     *
     * @param student 学生信息
     * @return 修改是否成功
     */
    @PostMapping("/updateStudent")
    public R updateStudent(@RequestBody Student student) {
        ValidatorUtils.validate(student, AddGroup.class);
        studentService.updateStudent(student);
        return R.ok();
    }

    /**
     * 分页查询
     * @param studentForm 查询条件
     * @param curPage 当前页
     * @param limit 页面大小
     * @return 返回是否成功
     */
    @PostMapping("/pageQuery")
    public R pageQuery(StudentForm studentForm, Integer curPage, Integer limit){
        Query query = new Query(curPage, limit);
        List<StudentVO> studentVOList = studentService.pageQuery(studentForm, query);
        int total = studentService.pageQueryCount(studentForm, query);
        Page studentPage = new Page(query, total, studentVOList);
        return R.ok().put("studentPage", studentPage);
    }
}
