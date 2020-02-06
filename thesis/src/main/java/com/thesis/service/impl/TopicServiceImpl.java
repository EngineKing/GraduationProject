package com.thesis.service.impl;

import com.thesis.dao.TaskDao;
import com.thesis.dao.TeacherDao;
import com.thesis.dao.TopicDao;
import com.thesis.entity.Topic;
import com.thesis.exception.RRException;
import com.thesis.form.TopicForm;
import com.thesis.service.TopicService;
import com.thesis.utils.Query;
import com.thesis.vo.TopicVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author LeiPeng
 * @Date 2020/1/25 - 22:21
 */
@Service
public class TopicServiceImpl implements TopicService{
    @Autowired
    TopicDao topicDao;

    @Autowired
    TeacherDao teacherDao;

    @Autowired
    TaskDao taskDao;

    @Override
    public List<TopicVO> pageQuery(TopicForm topicForm, Query query) {
        List<Topic> topicList = topicDao.pageQuery(topicForm, query);
        List<TopicVO> topicVOList = new ArrayList<>();
        for (int i = 0; i < topicList.size(); i++) {
            Topic topic = topicList.get(i);
            TopicVO topicVO = new TopicVO();
            topicVO.setId(topic.getId());
            topicVO.setName(topic.getName());
            topicVO.setDescription(topic.getDescription());
            topicVO.setOptionalNumber(topic.getOptionalNumber());
            topicVO.setSelectedNumber(topic.getSelectedNumber());
            topicVO.setTask(taskDao.getTaskById(topic.getTaskId()));
            topicVO.setTeacher(teacherDao.getTeacherById(topic.getTeacherId()));
            topicVOList.add(topicVO);
        }
        return topicVOList;
    }

    @Override
    public int pageQueryCount(TopicForm topicForm, Query query) {
        return topicDao.pageQueryCount(topicForm, query);
    }

    @Override
    public void updateTopic(Topic topic) {
        Topic foundTopic = topicDao.getTopicByName(topic.getName());
        if (foundTopic != null || foundTopic.getId() != topic.getId()) throw new RRException("更新题目失败，该题目名称已经存在");
        topicDao.updateTopic(topic);
    }

    @Override
    public void deleteTopicById(Integer id) {
        Topic foundTopic = topicDao.getTopicById(id);
        if (foundTopic == null) throw new RRException("删除题目失败，该题目不存在");
        // 需要判断该题目是否有学生选择，如果有则不能删除
        topicDao.deleteTopicById(id);
    }

    @Override
    public void addTopic(Topic topic) {
        Topic foundTopic = topicDao.getTopicByName(topic.getName());
        if (foundTopic != null) throw new RRException("新增题目失败，该题目名称已经存在");
        topicDao.addTopic(topic);
    }
}
