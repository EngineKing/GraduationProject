package com.thesis.controller;

import com.thesis.entity.Topic;
import com.thesis.form.TopicForm;
import com.thesis.service.TopicService;
import com.thesis.utils.Page;
import com.thesis.utils.Query;
import com.thesis.utils.R;
import com.thesis.validator.Assert;
import com.thesis.validator.ValidatorUtils;
import com.thesis.validator.group.AddGroup;
import com.thesis.validator.group.UpdateGroup;
import com.thesis.vo.TopicVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 新增题目信息
     *
     * @param topic 题目信息
     * @return 是否成功
     */
    @PostMapping("/addTopic")
    public R addTopic(@RequestBody Topic topic) {
        ValidatorUtils.validate(topic, AddGroup.class);
        topicService.addTopic(topic);
        return R.ok();
    }

    /**
     * 通过题目id删除题目信息
     *
     * @param id 题目id
     * @return 是否成功
     */
    @PostMapping("/deleteTopic/{id}")
    public R deleteTopicById(@PathVariable(name = "id") Integer id) {
        Assert.isNull(id, "id不能为空");
        topicService.deleteTopicById(id);
        return R.ok();
    }

    /**
     * 修改题目信息
     *
     * @param topic 题目信息
     * @return 修改是否成功
     */
    @PostMapping("/updateTopic")
    public R updateTopic(@RequestBody Topic topic) {
        ValidatorUtils.validate(topic, UpdateGroup.class);
        topicService.updateTopic(topic);
        return R.ok();
    }

    /**
     * 分页查询
     * @param topicForm 查询条件
     * @param curPage 当前页
     * @param limit 页面大小
     * @return 返回是否成功
     */
    @PostMapping("/pageQuery")
    public R pageQuery(TopicForm topicForm, Integer curPage, Integer limit){
        Query query = new Query(curPage, limit);
        List<TopicVO> topicVOList = topicService.pageQuery(topicForm, query);
        int total = topicService.pageQueryCount(topicForm, query);
        Page topicPage = new Page(query, total, topicVOList);
        return R.ok().put("topicPage", topicPage);
    }
}
