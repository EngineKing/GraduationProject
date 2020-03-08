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
    void add(Topic topic);

    Topic get(@Param("topicId") Integer id);

    void delete(@Param("topicId") Integer id);

    void update(Topic topic);

    List<Topic> pageQuery(@Param("topicForm") TopicForm topicForm, @Param("query") Query query);

    int pageQueryCount(@Param("topicForm") TopicForm topicForm, @Param("query") Query query);

    Topic getByName(@Param("topicName") String name);
}
