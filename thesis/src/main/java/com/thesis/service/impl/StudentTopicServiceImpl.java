package com.thesis.service.impl;

import com.thesis.dao.StudentDao;
import com.thesis.dao.StudentTopicDao;
import com.thesis.dao.TeacherDao;
import com.thesis.dao.TopicDao;
import com.thesis.entity.Student;
import com.thesis.entity.StudentTopic;
import com.thesis.entity.Topic;
import com.thesis.exception.RRException;
import com.thesis.form.StudentTopicForm;
import com.thesis.service.StudentTopicService;
import com.thesis.utils.Query;
import com.thesis.vo.StudentTopicVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author LeiPeng
 * @Date 2020/2/16 - 11:29
 */
@Service
public class StudentTopicServiceImpl implements StudentTopicService {

    @Autowired
    StudentTopicDao studentTopicDao;

    @Autowired
    TopicDao topicDao;

    @Autowired
    StudentDao studentDao;

    @Autowired
    TeacherDao teacherDao;

    @Override
    public void add(StudentTopic studentTopic) {
        Topic topic = topicDao.get(studentTopic.getTopicId());
        if (topic.getSelectedNumber() >= topic.getOptionalNumber()) throw new RRException("选择题目失败，已选择人数大于等于可选择人数");
        if (studentTopic.getIsTutorAgree() == 0){
            studentTopicDao.deleteByStudentId(studentTopic.getStudentId());
        }
        StudentTopic foundStudentTopic = studentTopicDao.getByStudentId(studentTopic.getStudentId());
        if (foundStudentTopic != null) {
            if (studentTopic.getTopicId() == foundStudentTopic.getTopicId()) {
                throw new RRException("选择题目失败，您已经选择了当前题目");
            } else if (studentTopic.getTopicId() != foundStudentTopic.getTopicId()){
                throw new RRException("选择题目失败，您已经选择了其他题目");
            }
        }
        studentTopicDao.add(studentTopic);
        topic.setSelectedNumber(topic.getSelectedNumber() + 1);
        topicDao.update(topic);
    }

    @Override
    public StudentTopic getByStudentId(Integer id) {
        return studentTopicDao.getByStudentId(id);
    }

    @Override
    public List<StudentTopicVO> getByTopicId(Integer id) {
        List<StudentTopic> sts = studentTopicDao.getByTopicId(id);
        List<StudentTopicVO> stVOs = new ArrayList<>();
        for (int i = 0; i < sts.size(); i++) {
            StudentTopic st = sts.get(i);
            StudentTopicVO stVO = new StudentTopicVO();
            stVO.setId(st.getId());
            stVO.setStudent(studentDao.get(st.getStudentId()));
            stVO.setTopic(topicDao.get(st.getTopicId()));
            stVO.setIsTutorAgree(st.getIsTutorAgree());
            stVOs.add(stVO);
        }
        return stVOs;
    }

    @Override
    public void isAgree(StudentTopic studentTopic) {
        studentTopicDao.isAgree(studentTopic.getStudentId(), studentTopic.getIsTutorAgree());
        if (studentTopic.getIsTutorAgree() == 0) {
            Student student = studentDao.get(studentTopic.getStudentId());
            Topic topic = topicDao.get(studentTopic.getTopicId());
            student.setTeacherId(topic.getTeacherId());
            studentDao.update(student);
        }
    }

    @Override
    public List<StudentTopicVO> pageQuery(StudentTopicForm studentTopicForm, Query query) {
        List<StudentTopic> studentTopics = studentTopicDao.pageQuery(studentTopicForm, query);
        List<StudentTopicVO> studentTopicVOS = new ArrayList<>();
        for (int i = 0; i < studentTopics.size(); i++) {
            StudentTopic studentTopic = studentTopics.get(i);
            StudentTopicVO studentTopicVO = new StudentTopicVO();
            studentTopicVO.setId(studentTopic.getId());
            studentTopicVO.setStudent(studentDao.get(studentTopic.getStudentId()));
            studentTopicVO.setTeacher(teacherDao.get(topicDao.get(studentTopic.getTopicId()).getTeacherId()));
            studentTopicVO.setTopic(topicDao.get(studentTopic.getTopicId()));
            studentTopicVO.setIsTutorAgree(studentTopic.getIsTutorAgree());
            studentTopicVOS.add(studentTopicVO);
        }
        return studentTopicVOS;
    }

    @Override
    public int pageQueryCount(StudentTopicForm studentTopicForm, Query query) {
        return studentTopicDao.pageQueryCount(studentTopicForm, query);
    }
}
