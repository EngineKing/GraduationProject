package com.thesis.service.impl;

import com.thesis.dao.*;
import com.thesis.entity.*;
import com.thesis.exception.RRException;
import com.thesis.form.TaskResultForm;
import com.thesis.service.TaskResultService;
import com.thesis.utils.DateUtil;
import com.thesis.utils.ExcelUtil;
import com.thesis.utils.Query;
import com.thesis.vo.StudentResultVO;
import com.thesis.vo.TaskResultVO;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author LeiPeng
 * @Date 2020/1/28 - 14:16
 */
@Service
public class TaskResultServiceImpl implements TaskResultService {
    @Autowired
    TaskResultDao taskResultDao;

    @Autowired
    AnnexDao annexDao;

    @Autowired
    StudentDao studentDao;

    @Autowired
    TeacherDao teacherDao;

    @Autowired
    TaskDao taskDao;

    @Autowired
    TeachingClassDao teachingClassDao;

    @Autowired
    GradeDao gradeDao;

    @Autowired
    SubjectDao subjectDao;

    @Autowired
    DepartmentDao departmentDao;

    private static String[] excelTitle = new String[]{"学号", "姓名", "性别", "身份证号", "类型", "电话号码", "邮箱", "注册日期", "学制(年)",
            "班级", "年级", "学科", "学院", "结果"};

    @Override
    public int pageQueryCount(TaskResultForm taskResultForm, Query query) {
        return taskResultDao.pageQueryCount(taskResultForm, query);
    }

    @Override
    public List<TaskResultVO> pageQuery(TaskResultForm taskResultForm, Query query) {
        List<TaskResult> taskResultList = taskResultDao.pageQuery(taskResultForm, query);
        List<TaskResultVO> taskResultVOList = new ArrayList<>();
        for (int i = 0; i < taskResultList.size(); i++) {
            TaskResult taskResult = taskResultList.get(i);
            TaskResultVO taskResultVO = new TaskResultVO();
            taskResultVO.setId(taskResult.getId());
            taskResultVO.setTitle(taskResult.getTitle());
            taskResultVO.setDescription(taskResult.getDescription());
            taskResultVO.setIsAuditPassed(taskResult.getIsAuditPassed());
            taskResultVO.setSubmitTime(taskResult.getSubmitTime());
            taskResultVO.setAuditOpinion(taskResult.getAuditOpinion());
            taskResultVO.setIsResultSubmit(taskResult.getIsResultSubmit());
            taskResultVO.setResult(taskResult.getResult());
            taskResultVO.setRepeatRate(taskResult.getRepeatRate());
            taskResultVO.setAnnex(annexDao.get(taskResult.getAnnexId()));
            taskResultVO.setStudent(studentDao.get(taskResult.getStudentId()));
            taskResultVO.setTeacher(teacherDao.get(taskResult.getTeacherId()));
            taskResultVO.setTask(taskDao.get(taskResult.getTaskId()));
            taskResultVOList.add(taskResultVO);
        }
        return taskResultVOList;
    }

    @Override
    public void pass(Integer taskResultId) {
        System.out.println(taskResultId);
        taskResultDao.updateResult(taskResultId, 0);
    }

    public void fail(Integer taskResultId) {
        taskResultDao.updateResult(taskResultId, 1);
    }

    @Override
    public Workbook export(String taskResultIds) {
        String[] ids = taskResultIds.split(",");
        List<Integer> tIds = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            tIds.add(Integer.valueOf(ids[i]));
        }
        List<TaskResult> taskResultList = taskResultDao.getListByIds(tIds);
        List<Integer> sIds = new ArrayList<>();
        for (int i = 0; i < taskResultList.size(); i++) {
            sIds.add(taskResultList.get(i).getStudentId());
        }
        List<Student> studentList = studentDao.getListByIds(sIds);
        List<List<String>> contentList = new ArrayList<List<String>>();
        for (int i = 0; i < studentList.size(); i++) {
            Student student = studentList.get(i);
            List<String> content = new ArrayList<>();
            content.add(student.getNumber());
            content.add(student.getName());
            content.add(student.getGender() == 0 ? "男" : "女");
            content.add(student.getIdCardNo());
            content.add(student.getType() == 0 ? "硕士研究生" : "博士研究生");
            content.add(student.getPhone());
            content.add(student.getEmail());
            content.add(DateUtil.timeStamp2Date(student.getEnrollmentDate(), "yyyy-MM-dd HH:mm:ss"));
            content.add(student.getSchoolSystem() == 0 ? "两年" : "三年");
            content.add(teachingClassDao.get(student.getClassId()).getName());
            content.add(gradeDao.get(student.getGradeId()).getName());
            content.add(subjectDao.get(student.getSubjectId()).getName());
            content.add(departmentDao.get(student.getDepartmentId()).getName());
            content.add(taskResultList.get(i).getResult() == 0 ? "通过" : "未通过");
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
    public Workbook allExport(TaskResultForm taskResultForm) {
        Query query = new Query(taskResultForm.getCurPage(), taskResultForm.getLimit());
        List<TaskResult> taskResultList = taskResultDao.pageQuery(taskResultForm, query);
        List<Integer> sIds = new ArrayList<>();
        for (int i = 0; i < taskResultList.size(); i++) {
            sIds.add(taskResultList.get(i).getStudentId());
        }
        List<Student> studentList = studentDao.getListByIds(sIds);
        List<List<String>> contentList = new ArrayList<List<String>>();
        for (int i = 0; i < studentList.size(); i++) {
            Student student = studentList.get(i);
            List<String> content = new ArrayList<>();
            content.add(student.getNumber());
            content.add(student.getName());
            content.add(student.getGender() == 0 ? "男" : "女");
            content.add(student.getIdCardNo());
            content.add(student.getType() == 0 ? "硕士研究生" : "博士研究生");
            content.add(student.getPhone());
            content.add(student.getEmail());
            content.add(DateUtil.timeStamp2Date(student.getEnrollmentDate(), "yyyy-MM-dd HH:mm:ss"));
            content.add(student.getSchoolSystem() == 0 ? "两年" : "三年");
            content.add(teachingClassDao.get(student.getClassId()).getName());
            content.add(gradeDao.get(student.getGradeId()).getName());
            content.add(subjectDao.get(student.getSubjectId()).getName());
            content.add(departmentDao.get(student.getDepartmentId()).getName());
            content.add(taskResultList.get(i).getResult() == 0 ? "通过" : "未通过");
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
    public void delete(Integer id) {
        TaskResult foundTaskResult = taskResultDao.get(id);
        if (foundTaskResult == null) throw new RRException("删除任务结果失败，该任务结果不存在");
        taskResultDao.delete(id);
    }

    @Override
    public void add(TaskResult taskResult) {
        TaskResult foundTaskResult = taskResultDao.getByTitle(taskResult.getTitle());
        if (foundTaskResult != null) throw new RRException("新增任务结果失败，该任务结果名称已经存在");
        taskResultDao.add(taskResult);
    }

    @Override
    public Annex getAnnex(Integer taskResultId) {
        TaskResult taskResult = taskResultDao.get(taskResultId);
        Annex annex = annexDao.get(taskResult.getAnnexId());
        return annex;
    }

//    @Override
//    public List<StudentResultVO> getTaskResult(Integer taskId) {
//        List<TaskResult> taskResults = taskResultDao.getByTaskId(taskId);
//        List<StudentResultVO> studentResults = new ArrayList<>();
//        for (int i = 0; i < taskResults.size(); i++) {
//            TaskResult taskResult = taskResults.get(i);
//            if (taskResult.getIsAuditPassed() == 0) {
//                Student student = studentDao.get(taskResult.getStudentId());
//                StudentResultVO studentResult = new StudentResultVO();
//                studentResult.setId(i);
//                studentResult.setStudent(student);
//                studentResult.setTaskResult(taskResult);
//                studentResults.add(studentResult);
//            }
//        }
//        return studentResults;
//    }
//
//    @Override
//    public List<StudentResultVO> getTaskResultWithCondition(Integer taskId, String number, String name) {
//        List<TaskResult> taskResults = taskResultDao.getByTaskId(taskId);
//        List<StudentResultVO> studentResults = new ArrayList<>();
//        for (int i = 0; i < taskResults.size(); i++) {
//            StudentResultVO studentResult = new StudentResultVO();
//            TaskResult taskResult = taskResults.get(i);
//            studentResult.setId(i);
//            studentResult.setStudent(studentDao.get(taskResult.getStudentId()));
//            studentResult.setTaskResult(taskResult);
//            studentResults.add(studentResult);
//        }
//        if (!number.isEmpty()) {
//            for (int i = 0; i < studentResults.size(); i++) {
//                StudentResultVO studentResult = studentResults.get(i);
//                if (!studentResult.getStudent().getNumber().contains(number)) {
//                    studentResults.remove(i);
//                }
//            }
//        }
//        if (!name.isEmpty()) {
//            for (int i = 0; i < studentResults.size(); i++) {
//                StudentResultVO studentResult = studentResults.get(i);
//                if (!studentResult.getStudent().getName().contains(name)) {
//                    studentResults.remove(i);
//                }
//            }
//        }
//        return studentResults;
//    }
}
