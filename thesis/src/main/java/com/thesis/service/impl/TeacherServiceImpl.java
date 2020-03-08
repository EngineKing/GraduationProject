package com.thesis.service.impl;

import com.thesis.dao.*;
import com.thesis.entity.*;
import com.thesis.exception.RRException;
import com.thesis.form.TeacherForm;
import com.thesis.form.TeacherTaskForm;
import com.thesis.service.TeacherService;
import com.thesis.utils.DataUtil;
import com.thesis.utils.ExcelUtil;
import com.thesis.utils.Query;
import com.thesis.vo.StudentResultVO;
import com.thesis.vo.TaskVO;
import com.thesis.vo.TeacherVO;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Author LeiPeng
 * @Date 2020/1/26 - 13:39
 */
@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    TeacherDao teacherDao;

    @Autowired
    DepartmentDao departmentDao;

    @Autowired
    SubjectDao subjectDao;

    @Autowired
    UserDao userDao;

    @Autowired
    UserRoleDao userRoleDao;

    @Autowired
    RoleDao roleDao;

    @Autowired
    TeacherTaskDao teacherTaskDao;

    @Autowired
    TaskDao taskDao;

    @Autowired
    AnnexDao annexDao;

    @Autowired
    StudentDao studentDao;

    @Autowired
    TaskResultDao taskResultDao;

    private static String[] excelTitle = new String[]{"编号", "姓名", "性别", "年龄", "电话号码", "邮箱", "职称", "简介",
            "所属学科", "所属部门"};

    @Override
    public void add(UserAndTeacher ut) {
        User foundUser = userDao.getUserByAccount(ut.getAccount());
        if (foundUser != null) throw new RRException("新增导师信息失败，该账号已经存在");
        Teacher foundTeacher = teacherDao.getByNumber(ut.getNumber());
        if (foundTeacher != null) throw new RRException("新增导师信息失败，该编号已经存在");
        Subject subject = subjectDao.get(ut.getSubjectId());
        User user = new User(ut.getAccount(), "123", ut.getGender(), ut.getEmail(),
                ut.getPhone(), ut.getCreateTime(), ut.getUpdateTime(),
                subject.getDepartmentId(), ut.getLoginTimes(), ut.getStatus());
        userDao.add(user);
        Teacher teacher = new Teacher(ut.getNumber(), ut.getName(), ut.getGender(),
                ut.getAge(), ut.getPhone(), ut.getEmail(), ut.getJobTitle(),
                ut.getIntroduction(), ut.getSubjectId(), subject.getDepartmentId(), user.getId(),
                ut.getStatus());
        teacherDao.add(teacher);

    }

    @Override
    public void delete(Integer id) {
        Teacher foundTeacher = teacherDao.get(id);
        if (foundTeacher == null) throw new RRException("删除教师失败，该教师不存在");
        teacherDao.delete(id);
    }

    @Override
    public void update(Teacher teacher) {
        teacherDao.update(teacher);
    }

    @Override
    public List<TeacherVO> pageQuery(TeacherForm teacherForm, Query query) {
        List<Teacher> teacherList = teacherDao.pageQuery(teacherForm, query);
        List<TeacherVO> teacherVOList = new ArrayList<>();
        for (int i = 0; i < teacherList.size(); i++) {
            TeacherVO teacherVO = new TeacherVO();
            Teacher teacher = teacherList.get(i);
            teacherVO.setId(teacher.getId());
            teacherVO.setAccount(userDao.get(teacher.getUserId()).getAccount());
            teacherVO.setNumber(teacher.getNumber());
            teacherVO.setName(teacher.getName());
            teacherVO.setAge(teacher.getAge());
            teacherVO.setEmail(teacher.getEmail());
            teacherVO.setIntroduction(teacher.getIntroduction());
            teacherVO.setGender(teacher.getGender());
            teacherVO.setJobTitle(teacher.getJobTitle());
            teacherVO.setPhone(teacher.getPhone());
            teacherVO.setDepartment(departmentDao.get(teacher.getDepartmentId()));
            teacherVO.setSubject(subjectDao.get(teacher.getSubjectId()));
            teacherVO.setStatus(teacher.getStatus());
            teacherVOList.add(teacherVO);
        }
        return teacherVOList;
    }

    @Override
    public int pageQueryCount(TeacherForm teacherForm, Query query) {
        return teacherDao.pageQueryCount(teacherForm, query);
    }

    @Override
    public List<Teacher> getAll() {
        return teacherDao.getAll();
    }

    @Override
    public Teacher getByUserId(Integer id) {
        return teacherDao.getByUserId(id);
    }

    @Override
    public void related2Task(TeacherTaskForm teacherTaskForm) {
        Integer taskId = teacherTaskForm.getTaskId();
        String[] numbers = teacherTaskForm.getNumbers();
        List<Teacher> teachers = new ArrayList<>();
        for (int i = 0; i < numbers.length; i++) {
            teachers.add(teacherDao.getByNumber(numbers[i]));
        }
        teacherTaskDao.deleteByTaskId(taskId);
        for (Teacher teacher : teachers) {
            teacherTaskDao.add(taskId, teacher.getId());
        }
    }

    @Override
    public void related2TaskByExcel(TeacherTaskForm teacherTaskForm) {
        Integer taskId = teacherTaskForm.getTaskId();
        List<Integer> foundTIds = teacherTaskDao.getTeacherIdByTaskId(taskId);
        List<Teacher> teachers = new ArrayList<>();
        String[] numbers = teacherTaskForm.getNumbers();
        for (int i = 0; i < numbers.length; i++) {
            teachers.add(teacherDao.getByNumber(numbers[i]));
        }
        Set<Integer> teacherIdsSet = new HashSet<>();
        for (int i = 0; i < teachers.size(); i++) {
            teacherIdsSet.add(teachers.get(i).getId());
        }
        for (int i = 0; i < foundTIds.size(); i++) {
            teacherIdsSet.add(foundTIds.get(i));
        }
        teacherTaskDao.deleteByTaskId(taskId);
        for(Integer studentId : teacherIdsSet){
            teacherTaskDao.add(taskId, studentId);
        }
    }

    @Override
    public List<TaskVO> query(Integer userId, String title) {
        Teacher teacher = teacherDao.getByUserId(userId);
        List<TeacherTask> teacherTasks = teacherTaskDao.query(teacher.getId());
        List<TaskVO> taskVOS = new ArrayList<>();
        boolean flag = title == null || title.length() == 0;
        for (int i = 0; i < teacherTasks.size(); i++) {
            Task task = taskDao.get(teacherTasks.get(i).getTaskId());
            TaskVO taskVO = new TaskVO();
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
    public List<StudentResultVO> getTaskResult(Integer userId, Integer taskId) {
        Teacher teacher = teacherDao.getByUserId(userId);
        List<Student> students = studentDao.getByTeacherId(teacher.getId());
        List<StudentResultVO> studentResults = new ArrayList<>();
        for (int i = 0; i < students.size(); i++) {
            TaskResult foundTaskResult = taskResultDao.getBySIdAndTId(students.get(i).getId(), taskId);
            if (foundTaskResult != null) {
                StudentResultVO studentResult = new StudentResultVO();
                studentResult.setId(i);
                studentResult.setStudent(students.get(i));
                studentResult.setTaskResult(foundTaskResult);
                studentResults.add(studentResult);
            }
        }
        return studentResults;
    }

    @Override
    public void pass(Integer taskResultId) {
        TaskResult taskResult = taskResultDao.get(taskResultId);
        taskResult.setIsAuditPassed(0);
        taskResult.setAuditOpinion("通过");
        taskResultDao.update(taskResult);
    }

    @Override
    public void fail(Integer taskResultId, String auditOpinion) {
        TaskResult taskResult = taskResultDao.get(taskResultId);
        taskResult.setIsAuditPassed(1);
        taskResult.setAuditOpinion(auditOpinion);
        taskResultDao.update(taskResult);
    }

    @Override
    public List<StudentResultVO> getTaskResultWithCondition(Integer userId, Integer taskId, String number,
                                                            String name) {
        Teacher teacher = teacherDao.getByUserId(userId);
        List<Student> students = studentDao.getByTeacherId(teacher.getId());
        List<StudentResultVO> studentResults = new ArrayList<>();
        for (int i = 0; i < students.size(); i++) {
            TaskResult foundTaskResult = taskResultDao.getBySIdAndTId(students.get(i).getId(), taskId);
            if (foundTaskResult != null) {
                StudentResultVO studentResult = new StudentResultVO();
                studentResult.setId(i);
                studentResult.setStudent(students.get(i));
                studentResult.setTaskResult(foundTaskResult);
                studentResults.add(studentResult);
            }
        }
        if (!number.isEmpty()) {
            for (int i = 0; i < studentResults.size(); i++) {
                StudentResultVO studentResult = studentResults.get(i);
                if (!studentResult.getStudent().getNumber().contains(number)) {
                    studentResults.remove(i);
                }
            }
        }
        if (!name.isEmpty()) {
            for (int i = 0; i < studentResults.size(); i++) {
                StudentResultVO studentResult = studentResults.get(i);
                if (!studentResult.getStudent().getName().contains(name)) {
                    studentResults.remove(i);
                }
            }
        }
        return studentResults;
    }

    @Override
    public Workbook export(String teacherIds) {
        String[] ids = teacherIds.split(",");
        List<Integer> tIds = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            tIds.add(Integer.valueOf(ids[i]));
        }
        List<Teacher> teacherList = teacherDao.getListByIds(tIds);
        List<List<String>> contentList = new ArrayList<List<String>>();
        for (Teacher teacher : teacherList) {
            List<String> content = new ArrayList<>();
            content.add(teacher.getNumber());
            content.add(teacher.getName());
            content.add(teacher.getGender() == 0 ? "男" : "女");
            content.add(teacher.getAge().toString());
            content.add(teacher.getPhone());
            content.add(teacher.getEmail());
            content.add(DataUtil.getTeacherJobTitle(teacher.getJobTitle()));
            content.add(teacher.getIntroduction());
            content.add(subjectDao.get(teacher.getSubjectId()).getName());
            content.add(departmentDao.get(teacher.getDepartmentId()).getName());
            contentList.add(content);
        }
        HSSFWorkbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("导师信息");
        List<String> titles = ExcelUtil.getTitle(excelTitle);
        HSSFCellStyle headRowStyle = ExcelUtil.getHeadRowStyle(workbook);
        HSSFCellStyle contentRowsStyle = ExcelUtil.getContentRowsStyle(workbook);
        ExcelUtil.createHeadRow(sheet, headRowStyle, titles);
        ExcelUtil.createContentRows(sheet, contentList, contentRowsStyle);
        return workbook;
    }

    @Override
    public Workbook allExport(TeacherForm teacherForm) {
        Query query = new Query(teacherForm.getCurPage(), teacherForm.getLimit());
        List<Teacher> teacherList = teacherDao.pageQuery(teacherForm, query);
        List<List<String>> contentList = new ArrayList<List<String>>();
        for (Teacher teacher : teacherList) {
            List<String> content = new ArrayList<>();
            content.add(teacher.getNumber());
            content.add(teacher.getName());
            content.add(teacher.getGender() == 0 ? "男" : "女");
            content.add(teacher.getAge().toString());
            content.add(teacher.getPhone());
            content.add(teacher.getEmail());
            content.add(DataUtil.getTeacherJobTitle(teacher.getJobTitle()));
            content.add(teacher.getIntroduction());
            content.add(subjectDao.get(teacher.getSubjectId()).getName());
            content.add(departmentDao.get(teacher.getDepartmentId()).getName());
            contentList.add(content);
        }
        HSSFWorkbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("导师信息");
        List<String> titles = ExcelUtil.getTitle(excelTitle);
        HSSFCellStyle headRowStyle = ExcelUtil.getHeadRowStyle(workbook);
        HSSFCellStyle contentRowsStyle = ExcelUtil.getContentRowsStyle(workbook);
        ExcelUtil.createHeadRow(sheet, headRowStyle, titles);
        ExcelUtil.createContentRows(sheet, contentList, contentRowsStyle);
        return workbook;
    }

    @Override
    public void batchInsert(List<UserAndTeacher> userAndTeachers) {
        for (int i = 0; i < userAndTeachers.size(); i++) {
            UserAndTeacher ut = userAndTeachers.get(i);
            ut.setCreateTime(new Date().getTime());
            ut.setUpdateTime(new Date().getTime());
            ut.setLoginTimes(0);
            ut.setStatus(0);
            User foundUser = userDao.getUserByAccount(ut.getAccount());
            if (foundUser != null) throw new RRException("导入导师信息失败，该账号"+foundUser.getAccount()+"已经存在");
            Teacher foundTeacher = teacherDao.getByNumber(ut.getNumber());
            if (foundTeacher != null) throw new RRException("导入导师信息失败，该编号"+foundTeacher.getNumber()+"已经存在");
            Subject subject = subjectDao.get(ut.getSubjectId());
            User user = new User(ut.getAccount(), "123", ut.getGender(), ut.getEmail(),
                    ut.getPhone(), ut.getCreateTime(), ut.getUpdateTime(),
                    subject.getDepartmentId(), ut.getLoginTimes(), ut.getStatus());
            userDao.add(user);
            Teacher teacher = new Teacher(ut.getNumber(), ut.getName(), ut.getGender(),
                    ut.getAge(), ut.getPhone(), ut.getEmail(), ut.getJobTitle(),
                    ut.getIntroduction(), ut.getSubjectId(), subject.getDepartmentId(), user.getId(),
                    ut.getStatus());
            teacherDao.add(teacher);
        }
    }
}
