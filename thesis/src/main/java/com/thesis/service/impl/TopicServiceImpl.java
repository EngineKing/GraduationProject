package com.thesis.service.impl;

import com.thesis.dao.*;
import com.thesis.entity.*;
import com.thesis.exception.RRException;
import com.thesis.form.StudentTopicForm;
import com.thesis.form.TopicForm;
import com.thesis.service.TopicService;
import com.thesis.utils.Query;
import com.thesis.vo.StudentVO;
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

    @Autowired
    StudentTaskDao studentTaskDao;

    @Autowired
    StudentTopicDao studentTopicDao;

    @Autowired
    StudentDao studentDao;

    @Autowired
    UserDao userDao;

    @Autowired
    TeachingClassDao teachingClassDao;

    @Autowired
    GradeDao gradeDao;

    @Autowired
    SubjectDao subjectDao;

    @Autowired
    DepartmentDao departmentDao;

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
            topicVO.setTask(taskDao.get(topic.getTaskId()));
            topicVO.setTeacher(teacherDao.get(topic.getTeacherId()));
            topicVO.setStatus(topic.getStatus());
            topicVOList.add(topicVO);
        }
        return topicVOList;
    }

    @Override
    public int pageQueryCount(TopicForm topicForm, Query query) {
        return topicDao.pageQueryCount(topicForm, query);
    }

    @Override
    public void update(Topic topic) {
        Topic foundTopic = topicDao.getByName(topic.getName());
        if (foundTopic != null && foundTopic.getId() != topic.getId()) throw new RRException("更新题目失败，该题目名称已经存在");
        topicDao.update(topic);
    }

    @Override
    public void delete(Integer id) {
        Topic foundTopic = topicDao.get(id);
        if (foundTopic == null) throw new RRException("删除题目失败，该题目不存在");
        // 需要判断该题目是否有学生选择，如果有则不能删除
        topicDao.delete(id);
    }

    @Override
    public void add(Topic topic) {
        Topic foundTopic = topicDao.getByName(topic.getName());
        if (foundTopic != null) throw new RRException("新增题目失败，该题目名称已经存在");
        System.out.println(topic.getStatus());
        topicDao.add(topic);
    }

//    @Override
//    public List<Topic> getListByStudentId(StudentTopicForm studentTopicForm) {
//        Integer studentId = studentTopicForm.getStudentId();
//        List<Integer> taskId = studentTaskDao.getTaskIdByStudentId(studentId);
//        return null;
//    }

    @Override
    public List<StudentVO> getStudentListByTopicId(Integer topicId) {
        List<StudentTopic> studentTopics = studentTopicDao.getByTopicId(topicId);
        List<StudentVO> studentVOS = new ArrayList<>();
        for (int i = 0; i < studentTopics.size(); i++) {
            StudentTopic studentTopic = studentTopics.get(i);
            if (studentTopic.getIsTutorAgree() == 0){
                Student student = studentDao.get(studentTopic.getStudentId());
                StudentVO studentVO = new StudentVO();
                studentVO.setId(student.getId());
                studentVO.setAccount(userDao.get(student.getUserId()).getAccount());
                studentVO.setNumber(student.getNumber());
                studentVO.setName(student.getName());
                studentVO.setGender(student.getGender());
                studentVO.setType(student.getType());
                studentVO.setPhone(student.getPhone());
                studentVO.setEmail(student.getEmail());
                studentVO.setIdCardNo(student.getIdCardNo());
                studentVO.setEnrollmentDate(student.getEnrollmentDate());
                studentVO.setSchoolSystem(student.getSchoolSystem());
                studentVO.setTeachingClass(teachingClassDao.get(student.getClassId()));
                studentVO.setGrade(gradeDao.get(student.getGradeId()));
                studentVO.setSubject(subjectDao.get(student.getSubjectId()));
                studentVO.setDepartment(departmentDao.get(student.getDepartmentId()));
                studentVO.setTeacher(student.getTeacherId() == null ? new Teacher("暂无") :
                        teacherDao.get(student.getDepartmentId()));
                studentVO.setUser(userDao.get(student.getUserId()));
                studentVO.setStatus(student.getStatus());
                studentVOS.add(studentVO);
            }
        }
        return studentVOS;
    }

    @Override
    public List<StudentVO> getStudentListWithCondition(Integer topicId, String number, String name) {
        List<StudentTopic> studentTopics = studentTopicDao.getByTopicId(topicId);
        List<StudentVO> studentVOS = new ArrayList<>();
        for (int i = 0; i < studentTopics.size(); i++) {
            StudentTopic studentTopic = studentTopics.get(i);
            if (studentTopic.getIsTutorAgree() == 0){
                Student student = studentDao.get(studentTopic.getStudentId());
                StudentVO studentVO = new StudentVO();
                studentVO.setId(student.getId());
                studentVO.setAccount(userDao.get(student.getUserId()).getAccount());
                studentVO.setNumber(student.getNumber());
                studentVO.setName(student.getName());
                studentVO.setGender(student.getGender());
                studentVO.setType(student.getType());
                studentVO.setPhone(student.getPhone());
                studentVO.setEmail(student.getEmail());
                studentVO.setIdCardNo(student.getIdCardNo());
                studentVO.setEnrollmentDate(student.getEnrollmentDate());
                studentVO.setSchoolSystem(student.getSchoolSystem());
                studentVO.setTeachingClass(teachingClassDao.get(student.getClassId()));
                studentVO.setGrade(gradeDao.get(student.getGradeId()));
                studentVO.setSubject(subjectDao.get(student.getSubjectId()));
                studentVO.setDepartment(departmentDao.get(student.getDepartmentId()));
                studentVO.setTeacher(student.getTeacherId() == null ? new Teacher("暂无") :
                        teacherDao.get(student.getDepartmentId()));
                studentVO.setUser(userDao.get(student.getUserId()));
                studentVO.setStatus(student.getStatus());
                studentVOS.add(studentVO);
            }
        }
        if (number != null && !number.isEmpty()){
            for (int i = 0; i < studentVOS.size(); i++) {
                if (!studentVOS.get(i).getNumber().contains(number)){
                    studentVOS.remove(i);
                }
            }
        }
        if (name != null && !name.isEmpty()){
            for (int i = 0; i < studentVOS.size(); i++) {
                if (!studentVOS.get(i).getName().contains(name)){
                    studentVOS.remove(i);
                }
            }
        }
        return studentVOS;
    }
}
