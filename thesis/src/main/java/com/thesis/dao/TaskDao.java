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
    void add(Task task);

    Task get(@Param("taskId") Integer id);

    void delete(@Param("taskId") Integer id);

    void update(Task task);

    List<Task> pageQuery(@Param("taskForm") TaskForm taskForm, @Param("query") Query query);

    int pageQueryCount(@Param("taskForm") TaskForm taskForm, @Param("query") Query query);

    Task getByTitle(@Param("taskTitle") String title);

    List<Task> getAll();

    List<Task> getAllPTasks();

    void updateAnnex(@Param("taskId") Integer taskId, @Param("annexId") Integer id);
}
