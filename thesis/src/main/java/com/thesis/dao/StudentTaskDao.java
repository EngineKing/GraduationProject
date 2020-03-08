package com.thesis.dao;

import com.thesis.entity.StudentTask;
import com.thesis.form.StudentTaskForm;
import com.thesis.utils.Query;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author LeiPeng
 * @Date 2020/2/22 - 16:16
 */
public interface StudentTaskDao {
    void add(@Param("taskId") Integer taskId, @Param("studentId") Integer studentId);

    List<Integer> getStudentIdByTaskId(@Param("taskId") Integer id);

    void deleteByTaskId(@Param("taskId") Integer taskId);

    List<Integer> getTaskIdByStudentId(@Param("studentId") Integer studentId);

    List<StudentTask> query(@Param("studentId") Integer studentId);
}
