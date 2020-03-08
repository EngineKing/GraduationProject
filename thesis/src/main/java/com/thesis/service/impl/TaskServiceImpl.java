package com.thesis.service.impl;

import com.thesis.dao.AnnexDao;
import com.thesis.dao.StudentTaskDao;
import com.thesis.dao.TaskDao;
import com.thesis.dao.TeacherTaskDao;
import com.thesis.entity.Annex;
import com.thesis.entity.Student;
import com.thesis.entity.Task;
import com.thesis.entity.TaskResult;
import com.thesis.exception.RRException;
import com.thesis.form.TaskForm;
import com.thesis.form.TeacherTaskForm;
import com.thesis.service.TaskService;
import com.thesis.utils.DateUtil;
import com.thesis.utils.Query;
import com.thesis.vo.TaskVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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

    @Autowired
    TeacherTaskDao teacherTaskDao;

    @Autowired
    StudentTaskDao studentTaskDao;

    @Value("${base_task_url}")
    private String base_task_url;

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
            taskVO.setAnnex(annexDao.get(task.getAnnexId()));
            taskVO.setpTask(task.getpTaskId() == 0 ? taskDao.get(task.getId()) : taskDao.get(task.getpTaskId()));
            taskVO.setStatus(task.getStatus());
            taskVOList.add(taskVO);
        }
        return taskVOList;
    }

    @Override
    public int pageQueryCount(TaskForm taskForm, Query query) {
        return taskDao.pageQueryCount(taskForm, query);
    }

    @Override
    public void update(Task task) {
        Task foundTask = taskDao.getByTitle(task.getTitle());
        if (foundTask != null && foundTask.getId() != task.getId()) throw new RRException("更新任务失败，任务名称已存在");
        taskDao.update(task);
    }

    @Override
    public void delete(Integer id) {
        Task task = taskDao.get(id);
        if (task == null) throw new RRException("删除任务失败，该任务不存在");
        // 是否需要加入任务的状态或者是判断该任务是否还未上传结果
        taskDao.delete(id);
    }

    @Override
    public void add(Task task) {
        Task foundTask = taskDao.getByTitle(task.getTitle());
        if (foundTask != null) throw new RRException("新增任务失败，任务名称已经存在");
        System.out.println(task.getpTaskId());
        taskDao.add(task);
    }

    @Override
    public List<Task> getAll() {
        return taskDao.getAll();
    }

    @Override
    public List<Task> getAllPTasks() {
        return taskDao.getAllPTasks();
    }

    @Override
    public List<Integer> getTeacherIdByTaskId(Integer id) {
        return teacherTaskDao.getTeacherIdByTaskId(id);
    }

    @Override
    public List<Integer> getStudentIdByTaskId(Integer id) {
        return studentTaskDao.getStudentIdByTaskId(id);
    }

    @Override
    public void upload(Integer taskId, MultipartFile file) {
        File destFile = new File(base_task_url + file.getOriginalFilename());
        try {
            file.transferTo(destFile);
            Annex annex = new Annex(file.getOriginalFilename(), base_task_url + file.getOriginalFilename(), "");
            annexDao.add(annex);
            taskDao.updateAnnex(taskId, annex.getId());
        } catch (IOException e) {
            throw new RRException("附件上传失败");
        }
    }

    @Override
    public Task get(Integer id) {
        return taskDao.get(id);
    }

    @Override
    public Annex getAnnex(Integer taskId) {
        Task task = taskDao.get(taskId);
        Annex annex = annexDao.get(task.getAnnexId());
        return annex;
    }
}
