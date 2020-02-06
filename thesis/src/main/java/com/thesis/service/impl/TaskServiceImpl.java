package com.thesis.service.impl;

import com.thesis.dao.AnnexDao;
import com.thesis.dao.TaskDao;
import com.thesis.entity.Task;
import com.thesis.exception.RRException;
import com.thesis.form.TaskForm;
import com.thesis.service.TaskService;
import com.thesis.utils.Query;
import com.thesis.vo.TaskVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author LeiPeng
 * @Date 2020/1/26 - 21:48
 */
@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    TaskDao taskDao;

    @Autowired
    AnnexDao annexDao;

    @Override
    public List<TaskVO> pageQuery(TaskForm taskForm, Query query) {
        List<Task> taskList = taskDao.pageQuery(taskForm, query);
        List<TaskVO> taskVOList = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            TaskVO taskVO = new TaskVO();
            Task task = taskList.get(i);
            taskVO.setId(task.getId());
            taskVO.setTitle(task.getTitle());
            taskVO.setDescription(task.getDescription());
            taskVO.setType(task.getType());
            taskVO.setBeginTime(task.getBeginTime());
            taskVO.setEndTime(task.getEndTime());
            taskVO.setResultsEndTime(task.getResultsEndTime());
            taskVO.setAnnex(annexDao.getAnnexById(task.getAnnexId()));
            taskVO.setpTask(taskDao.getTaskById(task.getpTaskId()));
            taskVOList.add(taskVO);
        }
        return taskVOList;
    }

    @Override
    public int pageQueryCount(TaskForm taskForm, Query query) {
        return taskDao.pageQueryCount(taskForm, query);
    }

    @Override
    public void updateTask(Task task) {
        Task foundTask = taskDao.getTaskByTitle(task.getTitle());
        if (foundTask != null && foundTask.getId() != task.getId()) throw new RRException("更新任务失败，任务名称已存在");
        taskDao.updateTask(task);
    }

    @Override
    public void deleteTaskById(Integer id) {
        Task task = taskDao.getTaskById(id);
        if (task == null) throw new RRException("删除任务失败，该任务不存在");
        // 是否需要加入任务的状态或者是判断该任务是否还未上传结果
        taskDao.deleteTaskById(id);
    }

    @Override
    public void addDepartment(Task task) {
        Task foundTask = taskDao.getTaskByTitle(task.getTitle());
        if (foundTask != null) throw new RRException("新增任务失败，任务名称已经存在");
        taskDao.addTask(task);
    }
}
