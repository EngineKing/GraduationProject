package com.thesis.dao;

import com.thesis.entity.Task;
import com.thesis.form.TaskForm;
import com.thesis.utils.Query;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author LeiPeng
 * @Date 2020/1/26 - 21:47
 */
public interface TaskDao {
    void addTask(Task task);

    Task getTaskById(@Param("taskId") Integer id);

    void deleteTaskById(@Param("taskId") Integer id);

    void updateTask(Task task);

    List<Task> pageQuery(@Param("taskForm") TaskForm taskForm, @Param("query") Query query);

    int pageQueryCount(@Param("taskForm") TaskForm taskForm, @Param("query") Query query);

    Task getTaskByTitle(@Param("taskTitle") String title);
}
