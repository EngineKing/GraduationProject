package com.thesis.service;

import com.thesis.entity.Annex;
import com.thesis.entity.Task;
import com.thesis.form.TaskForm;
import com.thesis.form.TeacherTaskForm;
import com.thesis.utils.Query;
import com.thesis.vo.TaskVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author LeiPeng
 * @Date 2020/1/26 - 21:48
 */
public interface TaskService {
    List<TaskVO> pageQuery(TaskForm taskForm, Query query);

    int pageQueryCount(TaskForm taskForm, Query query);

    void update(Task task);

    void delete(Integer id);

    void add(Task task);

    List<Task> getAll();

    List<Task> getAllPTasks();

    List<Integer> getTeacherIdByTaskId(Integer id);

    List<Integer> getStudentIdByTaskId(Integer id);

    void upload(Integer taskId, MultipartFile file);

    Task get(Integer id);

    Annex getAnnex(Integer taskId);
}
