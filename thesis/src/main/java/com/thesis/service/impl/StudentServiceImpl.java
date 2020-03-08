package com.thesis.service.impl;

import com.thesis.dao.*;
import com.thesis.entity.*;
import com.thesis.exception.RRException;
import com.thesis.form.StudentForm;
import com.thesis.form.StudentTaskForm;
import com.thesis.service.StudentService;
import com.thesis.utils.DataUtil;
import com.thesis.utils.DateUtil;
import com.thesis.utils.ExcelUtil;
import com.thesis.utils.Query;
import com.thesis.vo.StudentTaskVO;
import com.thesis.vo.StudentVO;
import com.thesis.vo.TaskVO;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

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

    @Autowired
    StudentTaskDao studentTaskDao;

    @Autowired
    AnnexDao annexDao;

    @Autowired
    TaskDao taskDao;

    @Autowired
    TaskResultDao taskResultDao;

    @Value("${base_result_url}")
    private String base_result_url;

    private static String[] excelTitle = new String[]{"学号", "姓名", "性别", "身份证号", "类型", "电话号码", "邮箱", "注册日期", "学制(年)",
            "班级", "年级", "学科", "学院"};

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
            studentVOList.add(studentVO);
        }
        return studentVOList;
    }

    @Override
    public void update(Student student) {
        student.setDepartmentId(subjectDao.get(student.getSubjectId()).getDepartmentId());
        student.setGradeId(teachingClassDao.get(student.getClassId()).getGradeId());
        studentDao.update(student);
    }

    @Override
    public void delete(Integer id) {
        studentDao.delete(id);
    }

    @Override
    public void add(UserAndStudent us) {
        User foundUser = userDao.getUserByAccount(us.getAccount());
        if (foundUser != null) throw new RRException("新增学生失败，该学生账号已经存在");
        Student foundStudent = studentDao.getByNumber(us.getNumber());
        if (foundStudent != null) throw new RRException("新增学生失败，该学生学号已经存在");
        Subject subject = subjectDao.get(us.getSubjectId());
        TeachingClass tc = teachingClassDao.get(us.getClassId());
        User user = new User(us.getAccount(), us.getPassword(), us.getGender(), us.getEmail(), us.getPhone(),
                us.getCreateTime(), us.getUpdateTime(), subject.getDepartmentId(), us.getLoginTimes(), us.getStatus());
        userDao.add(user);
        Student student = new Student(us.getNumber(), us.getName(), us.getGender(), us.getIdCardNo(),
                us.getType(), us.getPhone(), us.getEmail(), us.getEnrollmentDate(), us.getSchoolSystem(),
                us.getClassId(), tc.getGradeId(), us.getSubjectId(), subject.getDepartmentId(), user.getId(),
                us.getStatus());
        studentDao.add(student);
    }

    @Override
    public Student getByUserId(Integer id) {
        return studentDao.getByUserId(id);
    }

    /**
     * 学生与任务关联
     *
     * @param studentTaskForm 学生任务信息
     */
    @Override
    public void related2Task(StudentTaskForm studentTaskForm) {
        Integer taskId = studentTaskForm.getTaskId();
        String[] numbers = studentTaskForm.getNumbers();
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < numbers.length; i++) {
            students.add(studentDao.getByNumber(numbers[i]));
        }
        studentTaskDao.deleteByTaskId(taskId);
        for (Student student : students) {
            studentTaskDao.add(taskId, student.getId());
        }
    }

    @Override
    public void related2TaskByExcel(StudentTaskForm studentTaskForm) {
        Integer taskId = studentTaskForm.getTaskId();
        List<Integer> foundSIds = studentTaskDao.getStudentIdByTaskId(taskId);
        List<Student> students = new ArrayList<>();
        String[] numbers = studentTaskForm.getNumbers();
        for (int i = 0; i < numbers.length; i++) {
            students.add(studentDao.getByNumber(numbers[i]));
        }
        Set<Integer> studentIdsSet = new HashSet<>();
        for (int i = 0; i < students.size(); i++) {
            studentIdsSet.add(students.get(i).getId());
        }
        for (int i = 0; i < foundSIds.size(); i++) {
            studentIdsSet.add(foundSIds.get(i));
        }
        studentTaskDao.deleteByTaskId(taskId);
        for(Integer studentId : studentIdsSet){
            studentTaskDao.add(taskId, studentId);
        }
    }

    /**
     * 学生提交任务结果
     *
     * @param map  学生信息
     * @param file 任务结果
     */
    @Override
    public void submit(Map<String, String> map, MultipartFile file) {
        Integer userId = Integer.valueOf(map.get("userId"));
        Integer taskId = Integer.valueOf(map.get("taskId"));
        File destFile = new File(base_result_url + file.getOriginalFilename());
        try {
            file.transferTo(destFile);
            Annex annex = new Annex(file.getOriginalFilename(), base_result_url + file.getOriginalFilename(), "");
            annexDao.add(annex);
            Student student = studentDao.getByUserId(userId);
            Task task = taskDao.get(taskId);
            TaskResult foundTaskResult = taskResultDao.getBySIdAndTId(student.getId(), task.getId());
            if (foundTaskResult == null) {
                TaskResult taskResult = new TaskResult(task.getTitle(), task.getDescription(), DateUtil.timeStamp(),
                        -1, "",
                        0, -1, -1, annex.getId(), student.getId(), student.getTeacherId(), taskId);
                taskResultDao.add(taskResult);
            } else {
                taskResultDao.updateAnnexIdBySIdAndTId(annex.getId(), student.getId(), task.getId());
            }
        } catch (IOException e) {
            throw new RRException("文件保存失败");
        }
    }

    /**
     * 查询
     *
     * @param userId 用户id
     * @param title  任务标题
     * @return 查询结果
     */
    @Override
    public List<TaskVO> query(Integer userId, String title) {
        Student student = studentDao.getByUserId(userId);
        List<StudentTask> studentTasks = studentTaskDao.query(student.getId());
        List<TaskVO> taskVOS = new ArrayList<>();
        boolean flag = title == null || title.length() == 0;
        for (int i = 0; i < studentTasks.size(); i++) {
            TaskVO taskVO = new TaskVO();
            StudentTask studentTask = studentTasks.get(i);
            Task task = taskDao.get(studentTask.getTaskId());
            taskVO.setId(studentTask.getId());
            taskVO.setId(task.getId());
            taskVO.setTitle(task.getTitle());
            taskVO.setDescription(task.getDescription());
            taskVO.setType(task.getType());
            taskVO.setBeginTime(task.getBeginTime());
            taskVO.setEndTime(task.getEndTime());
            taskVO.setResultsEndTime(task.getResultsEndTime());
            taskVO.setAnnex(annexDao.get(task.getAnnexId()));
            taskVO.setpTask(task.getpTaskId() == 0 ? taskDao.get(task.getId()) : taskDao.get(task.getpTaskId()));
            taskVO.setStatus(task.getStatus());
            taskVOS.add(taskVO);
        }
        if (!flag) {
            for (int i = 0; i < taskVOS.size(); i++) {
                if (!taskVOS.get(i).getTitle().contains(title)) {
                    taskVOS.remove(taskVOS.get(i));
                }
            }
        }
        return taskVOS;
    }

    @Override
    public Student get(Integer studentId) {
        return studentDao.get(studentId);
    }

    @Override
    public void batchInsert(List<UserAndStudent> userAndStudents) {
        for (int i = 0; i < userAndStudents.size(); i++) {
            UserAndStudent us = userAndStudents.get(i);
            us.setCreateTime(DateUtil.timeStamp());
            us.setUpdateTime(DateUtil.timeStamp());
            us.setLoginTimes(0);
            us.setStatus(0);
            User foundUser = userDao.getUserByAccount(us.getAccount());
            if (foundUser != null) throw new RRException("导入学生失败，该学生账号" + us.getAccount() + "已经存在");
            Student foundStudent = studentDao.getByNumber(us.getNumber());
            if (foundStudent != null) throw new RRException("导入学生失败，该学生学号" + us.getNumber() + "已经存在");
            Subject subject = subjectDao.get(us.getSubjectId());
            TeachingClass tc = teachingClassDao.get(us.getClassId());
            User user = new User(us.getAccount(), "123", us.getGender(), us.getEmail(), us.getPhone(),
                    us.getCreateTime(), us.getUpdateTime(), subject.getDepartmentId(), us.getLoginTimes(),
                    us.getStatus());
            userDao.add(user);
            Student student = new Student(us.getNumber(), us.getName(), us.getGender(), us.getIdCardNo(),
                    us.getType(), us.getPhone(), us.getEmail(), us.getEnrollmentDate(), us.getSchoolSystem(),
                    us.getClassId(), tc.getGradeId(), us.getSubjectId(), subject.getDepartmentId(), user.getId(),
                    us.getStatus());
            studentDao.add(student);
        }
    }

    @Override
    public Workbook export(String studentIds) {
        String[] ids = studentIds.split(",");
        List<Integer> sIds = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            sIds.add(Integer.valueOf(ids[i]));
        }
        List<Student> studentList = studentDao.getListByIds(sIds);
        List<List<String>> contentList = new ArrayList<List<String>>();
        for (Student student : studentList) {
            List<String> content = new ArrayList<>();
            content.add(student.getNumber());
            content.add(student.getName());
            content.add(student.getGender() == 0 ? "男" : "女");
            content.add(student.getIdCardNo());
            content.add(student.getType() == 0 ? "硕士研究生" : "博士研究生");
            content.add(student.getPhone());
            content.add(student.getEmail());
            content.add(DateUtil.timeStamp2Date(student.getEnrollmentDate(), "yyyy-MM-dd HH:mm:ss"));
            content.add(student.getSchoolSystem() == 0 ? "两年" : "三年" );
            content.add(teachingClassDao.get(student.getClassId()).getName());
            content.add(gradeDao.get(student.getGradeId()).getName());
            content.add(subjectDao.get(student.getSubjectId()).getName());
            content.add(departmentDao.get(student.getDepartmentId()).getName());
            contentList.add(content);
        }
        HSSFWorkbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("学生信息");
        List<String> titles = ExcelUtil.getTitle(excelTitle);
        HSSFCellStyle headRowStyle = ExcelUtil.getHeadRowStyle(workbook);
        HSSFCellStyle contentRowsStyle = ExcelUtil.getContentRowsStyle(workbook);
        ExcelUtil.createHeadRow(sheet, headRowStyle, titles);
        ExcelUtil.createContentRows(sheet, contentList, contentRowsStyle);
        return workbook;
    }

    @Override
    public Workbook allExport(StudentForm studentForm) {
        Query query = new Query(studentForm.getCurPage(), studentForm.getLimit());
        List<Student> studentList = studentDao.pageQuery(studentForm, query);
        List<List<String>> contentList = new ArrayList<List<String>>();
        for (Student student : studentList) {
            List<String> content = new ArrayList<>();
            content.add(student.getNumber());
            content.add(student.getName());
            content.add(student.getGender() == 0 ? "男" : "女");
            content.add(student.getIdCardNo());
            content.add(student.getType() == 0 ? "硕士研究生" : "博士研究生");
            content.add(student.getPhone());
            content.add(student.getEmail());
            content.add(DateUtil.timeStamp2Date(student.getEnrollmentDate(), "yyyy-MM-dd HH:mm:ss"));
            content.add(student.getSchoolSystem().toString());
            content.add(teachingClassDao.get(student.getClassId()).getName());
            content.add(gradeDao.get(student.getGradeId()).getName());
            content.add(subjectDao.get(student.getSubjectId()).getName());
            content.add(departmentDao.get(student.getDepartmentId()).getName());
            contentList.add(content);
        }
        HSSFWorkbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("学生信息");
        List<String> titles = ExcelUtil.getTitle(excelTitle);
        HSSFCellStyle headRowStyle = ExcelUtil.getHeadRowStyle(workbook);
        HSSFCellStyle contentRowsStyle = ExcelUtil.getContentRowsStyle(workbook);
        ExcelUtil.createHeadRow(sheet, headRowStyle, titles);
        ExcelUtil.createContentRows(sheet, contentList, contentRowsStyle);
        return workbook;
    }
}
