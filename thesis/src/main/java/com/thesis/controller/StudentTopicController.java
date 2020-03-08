package com.thesis.controller;

import com.thesis.entity.Student;
import com.thesis.entity.StudentTopic;
import com.thesis.form.StudentTopicForm;
import com.thesis.service.StudentService;
import com.thesis.service.StudentTopicService;
import com.thesis.utils.Page;
import com.thesis.utils.Query;
import com.thesis.utils.R;
import com.thesis.validator.Assert;
import com.thesis.vo.StudentTopicVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author LeiPeng
 * @Date 2020/2/16 - 11:27
 * <p>
 * 学生选题管理
 */
@RestController
@RequestMapping("/studentTopic")
public class StudentTopicController {
    @Autowired
    private StudentTopicService studentTopicService;

    @Autowired
    private StudentService studentService;

    /**
     * 新增学生与课题的联系
     *
     * @param request      带有学生id
     * @param studentTopic 学生选题信息
     * @return 是否成功
     */
    @PostMapping("/add")
    public R add(HttpServletRequest request, @RequestBody StudentTopic studentTopic) {
        if (studentTopic.getIsTutorAgree() == 2) {
            Integer userId = Integer.valueOf(request.getHeader("userId"));
            studentTopic.setStudentId(studentService.getByUserId(userId).getId());
        }
        studentTopicService.add(studentTopic);
        return R.ok();
    }

    /**
     * 通过学生id获取学生选题信息
     *
     * @param request 带有学生信息
     * @return 选题信息
     */
    @GetMapping("/getByStudentId")
    public R getByStudentId(HttpServletRequest request) {
        Integer userId = Integer.valueOf(request.getHeader("userId"));
        Student student = studentService.getByUserId(userId);
        StudentTopic studentTopic = new StudentTopic();
        if (student != null) {
            studentTopic = studentTopicService.getByStudentId(student.getId());
        }
        return R.ok().put("studentTopic", studentTopic);
    }

    /**
     * 通过题目id获取学生选题信息
     *
     * @param id 题目id
     * @return 选题信息
     */
    @PostMapping("/getByTopicId")
    public R getByTopicId(Integer id) {
        List<StudentTopicVO> studentTopicVOs = studentTopicService.getByTopicId(id);
        return R.ok().put("studentTopicVOs", studentTopicVOs);
    }

    /**
     * 导师是否同意
     *
     * @param studentTopic 学生选题信息
     * @return 是否成功
     */
    @PostMapping("/isAgree")
    public R isAgree(@RequestBody StudentTopic studentTopic) {
        Assert.isNull(studentTopic, "学生题目信息不能为空");
        studentTopicService.isAgree(studentTopic);
        return R.ok();
    }

    /**
     * 分页查询学生信息
     *
     * @param studentTopicForm 学生条件
     * @return 查询结果
     */
    @RequestMapping("/pageQuery")
    public R pageQuery(@RequestBody StudentTopicForm studentTopicForm) {
        Query query = new Query(studentTopicForm.getCurPage(), studentTopicForm.getLimit());
        List<StudentTopicVO> studentTopicVOS = studentTopicService.pageQuery(studentTopicForm, query);
        int total = studentTopicService.pageQueryCount(studentTopicForm, query);
        Page studentTopicPage = new Page(query, total, studentTopicVOS);
        return R.ok().put("studentTopicPage", studentTopicPage);
    }
}
