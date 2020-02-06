package com.thesis.service;

import com.thesis.entity.Topic;
import com.thesis.form.TopicForm;
import com.thesis.utils.Query;
import com.thesis.vo.TopicVO;

import java.util.List;

/**
 * @Author LeiPeng
 * @Date 2020/1/25 - 22:20
 */
public interface TopicService {
    List<TopicVO> pageQuery(TopicForm topicForm, Query query);

    int pageQueryCount(TopicForm topicForm, Query query);

    void updateTopic(Topic topic);

    void deleteTopicById(Integer id);

    void addTopic(Topic topic);
}
