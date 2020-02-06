package com.thesis.service.impl;

import com.thesis.dao.*;
import com.thesis.entity.Grade;
import com.thesis.entity.Student;
import com.thesis.form.StudentForm;
import com.thesis.service.StudentService;
import com.thesis.utils.Query;
import com.thesis.vo.StudentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author LeiPeng
 * @Date 2020/1/28 - 14:16
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentDao studentDao;

    @Autowired
    TeachingClassDao teachingClassDao;

    @Autowired
    GradeDao gradeDao;

    @Autowired
    SubjectDao subjectDao;

    @Autowired
    DepartmentDao departmentDao;

    @Autowired
    TeacherDao teacherDao;

    @Autowired
    UserDao userDao;

    @Override
    public int pageQueryCount(StudentForm studentForm, Query query) {
        return studentDao.pageQueryCount(studentForm, query);
    }

    @Override
    public List<StudentVO> pageQuery(StudentForm studentForm, Query query) {
        List<Student> studentList = studentDao.pageQuery(studentForm, query);
        List<StudentVO> studentVOList = new ArrayList<>();
        for (int i = 0; i < studentList.size(); i++) {
            Student student = studentList.get(i);
            StudentVO studentVO = new StudentVO();
            studentVO.setId(student.getId());
            studentVO.setNumber(student.getNumber());
            studentVO.setName(student.getName());
            studentVO.setGender(student.getGender());
            studentVO.setNation(student.getNation());
            studentVO.setHometown(student.getHometown());
            studentVO.setType(student.getType());
            studentVO.setPhone(student.getPhone());
            studentVO.setEmail(student.getEmail());
            studentVO.setPosition(student.getPosition());
            studentVO.setEnrollmentDate(student.getEnrollmentDate());
            studentVO.setSchoolSystem(student.getSchoolSystem());
            studentVO.setTeachingClass(teachingClassDao.getTeachingClassById(student.getTeacherId()));
            studentVO.setGrade(gradeDao.getGradeById(student.getGradeId()));
            studentVO.setSubject(subjectDao.getSubjectById(student.getSubjectId()));
            studentVO.setDepartment(departmentDao.getDepartmentById(student.getDepartmentId()));
            studentVO.setTeacher(teacherDao.getTeacherById(student.getTeacherId()));
            studentVO.setUser(userDao.getUserById(student.getUserId()));
            studentVOList.add(studentVO);
        }
        return studentVOList;
    }

    @Override
    public void updateStudent(Student student) {
        studentDao.updateStudent(student);
    }

    @Override
    public void deleteStudentById(Integer id) {
        studentDao.deleteStudentById(id);
    }

    @Override
    public void addStudent(Student student) {
        studentDao.addStudent(student);
    }
}
