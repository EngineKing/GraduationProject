package com.thesis.dao;

import com.thesis.entity.Topic;
import com.thesis.form.TopicForm;
import com.thesis.utils.Query;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author LeiPeng
 * @Date 2020/1/25 - 22:19
 */
public interface TopicDao {
    void addTopic(Topic topic);

    Topic getTopicById(@Param("topicId") Integer id);

    void deleteTopicById(@Param("topicId") Integer id);

    void updateTopic(Topic topic);

    List<Topic> pageQuery(@Param("topicForm") TopicForm topicForm, @Param("query") Query query);

    int pageQueryCount(@Param("topicForm") TopicForm topicForm, @Param("query") Query query);

    Topic getTopicByName(@Param("topicName") String name);
}
