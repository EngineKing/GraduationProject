package com.thesis.service;

import com.thesis.entity.Task;
import com.thesis.form.TaskForm;
import com.thesis.utils.Query;
import com.thesis.vo.TaskVO;

import java.util.List;

/**
 * @Author LeiPeng
 * @Date 2020/1/26 - 21:48
 */
public interface TaskService {
    List<TaskVO> pageQuery(TaskForm taskForm, Query query);

    int pageQueryCount(TaskForm taskForm, Query query);

    void updateTask(Task task);

    void deleteTaskById(Integer id);

    void addDepartment(Task task);
}
