package com.thesis.service.impl;

import com.thesis.dao.*;
import com.thesis.entity.TaskResult;
import com.thesis.exception.RRException;
import com.thesis.form.TaskResultForm;
import com.thesis.service.TaskResultService;
import com.thesis.utils.Query;
import com.thesis.vo.TaskResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
            taskResultVO.setAuditOpinion(taskResult.getAuditOpinion());
            taskResultVO.setIsResultSubmit(taskResult.getIsResultSubmit());
            taskResultVO.setResult(taskResult.getResult());
            taskResultVO.setRepeatRate(taskResult.getRepeatRate());
            taskResultVO.setAnnex(annexDao.getAnnexById(taskResult.getAnnexId()));
            taskResultVO.setStudent(studentDao.getStudentById(taskResult.getStudentId()));
            taskResultVO.setTeacher(teacherDao.getTeacherById(taskResult.getTeacherId()));
            taskResultVO.setTask(taskDao.getTaskById(taskResult.getTaskId()));
            taskResultVOList.add(taskResultVO);
        }
        return taskResultVOList;
    }

    @Override
    public void updateTaskResult(TaskResult taskResult) {
        TaskResult foundTaskResult = taskResultDao.getTaskResultByTitle(taskResult.getTitle());
        if (foundTaskResult != null && foundTaskResult.getId() != taskResult.getId()) throw new RRException("更新任务结果失败，该任务结果名称已经存在");
        taskResultDao.updateTaskResult(taskResult);
    }

    @Override
    public void deleteTaskResultById(Integer id) {
        TaskResult foundTaskResult = taskResultDao.getTaskResultById(id);
        if (foundTaskResult == null) throw new RRException("删除任务结果失败，该任务结果不存在");
        taskResultDao.deleteTaskResultById(id);
    }

    @Override
    public void addTaskResult(TaskResult taskResult) {
        TaskResult foundTaskResult = taskResultDao.getTaskResultByTitle(taskResult.getTitle());
        if (foundTaskResult != null) throw new RRException("新增任务结果失败，该任务结果名称已经存在");
        taskResultDao.addTaskResult(taskResult);
    }
}
