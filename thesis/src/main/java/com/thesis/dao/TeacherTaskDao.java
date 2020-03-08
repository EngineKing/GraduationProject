package com.thesis.dao;

import com.thesis.entity.TeacherTask;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author LeiPeng
 * @Date 2020/2/22 - 10:51
 */
public interface TeacherTaskDao {
    void add(@Param("taskId") Integer taskId, @Param("teacherId") Integer teacherId);

    List<Integer> getTeacherIdByTaskId(@Param("taskId") Integer id);

    void deleteByTaskId(@Param("taskId") Integer taskId);

    List<TeacherTask> query(@Param("teacherId") Integer id);
}
