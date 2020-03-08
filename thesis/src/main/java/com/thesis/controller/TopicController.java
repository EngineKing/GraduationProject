package com.thesis.controller;

import com.thesis.entity.Teacher;
import com.thesis.entity.Topic;
import com.thesis.form.StudentTopicForm;
import com.thesis.form.TopicForm;
import com.thesis.service.TeacherService;
import com.thesis.service.TopicService;
import com.thesis.utils.Page;
import com.thesis.utils.Query;
import com.thesis.utils.R;
import com.thesis.validator.Assert;
import com.thesis.validator.ValidatorUtils;
import com.thesis.validator.group.AddGroup;
import com.thesis.validator.group.UpdateGroup;
import com.thesis.vo.StudentVO;
import com.thesis.vo.TopicVO;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author LeiPeng
 * @Date 2020/1/25 - 22:19
 */
@RestController
@RequestMapping("/topic")
public class TopicController {
    @Autowired
    TopicService topicService;

    @Autowired
    TeacherService teacherService;

    /**
     * 新增题目信息
     *
     * @param topic 题目信息
     * @return 是否成功
     */
    @PostMapping("/add")
    public R add(HttpServletRequest request, @RequestBody Topic topic) {
        Integer userId = Integer.valueOf(request.getHeader("userId"));
        topic.setTeacherId(teacherService.getByUserId(userId).getId());
        ValidatorUtils.validate(topic, AddGroup.class);
        topicService.add(topic);
        return R.ok();
    }

    /**
     * 通过题目id删除题目信息
     *
     * @param id 题目id
     * @return 是否成功
     */
    @PostMapping("/delete")
    public R delete(Integer id) {
        Assert.isNull(id, "id不能为空");
        topicService.delete(id);
        return R.ok();
    }

    /**
     * 修改题目信息
     *
     * @param topic 题目信息
     * @return 修改是否成功
     */
    @PostMapping("/update")
    public R update(@RequestBody Topic topic) {
        ValidatorUtils.validate(topic, UpdateGroup.class);
        topicService.update(topic);
        return R.ok();
    }

    /**
     * 分页查询
     *
     * @param topicForm 查询条件
     * @return 返回是否成功
     */
    @PostMapping("/pageQuery")
    public R pageQuery(HttpServletRequest request, @RequestBody TopicForm topicForm) {
        Teacher teacher = teacherService.getByUserId(Integer.valueOf(request.getHeader("userId")));
        topicForm.setTeacherId(teacher == null ? topicForm.getTeacherId() : teacher.getId());
        Query query = new Query(topicForm.getCurPage(), topicForm.getLimit());
        List<TopicVO> topicVOList = topicService.pageQuery(topicForm, query);
        int total = topicService.pageQueryCount(topicForm, query);
        Page topicPage = new Page(query, total, topicVOList);
        return R.ok().put("topicPage", topicPage);
    }


    /**
     * 获取题目信息
     * @param topicForm 条件
     * @return 题目信息
     */
    @PostMapping("/getList")
    public R getList(@RequestBody TopicForm topicForm){
        Query query = new Query(topicForm.getCurPage(), topicForm.getLimit());
        List<TopicVO> topicVOList = topicService.pageQuery(topicForm, query);
        int total = topicService.pageQueryCount(topicForm, query);
        Page topicPage = new Page(query, total, topicVOList);
        return R.ok().put("topicPage", topicPage);
    }

    /**
     * 通过题目id得到学生信息
     * @param topicId 题目id
     * @return 学生信息
     */
    @PostMapping("/getStudentListByTopicId")
    public R getStudentListByTopicId(Integer topicId){
        List<StudentVO> studentVOS = topicService.getStudentListByTopicId(topicId);
        return R.ok().put("studentVOS", studentVOS);
    }

    /**
     * 通过条件查询学生信息
     * @param number
     * @param name
     * @return
     */
    @PostMapping("/getStudentListWithCondition")
    public R getStudentListWithCondition(Integer topicId, String number, String name){
        List<StudentVO> studentVOS = topicService.getStudentListWithCondition(topicId, number, name);
        return R.ok().put("studentVOS", studentVOS);
    }
}
