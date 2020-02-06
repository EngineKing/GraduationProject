package com.thesis.dao;

import com.thesis.entity.TaskResult;
import com.thesis.form.TaskResultForm;
import com.thesis.utils.Query;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author LeiPeng
 * @Date 2020/1/28 - 14:17
 */
public interface TaskResultDao {
    void addTaskResult(TaskResult taskResult);

    TaskResult getTaskResultById(@Param("taskResultId") Integer id);

    void deleteTaskResultById(@Param("taskResultId") Integer id);

    void updateTaskResult(TaskResult taskResult);

    List<TaskResult> pageQuery(@Param("taskResultForm") TaskResultForm taskResultForm, @Param("query") Query query);

    int pageQueryCount(@Param("taskResultForm") TaskResultForm taskResultForm, @Param("query") Query query);

    TaskResult getTaskResultByTitle(@Param("taskResultTitle") String title);
}
