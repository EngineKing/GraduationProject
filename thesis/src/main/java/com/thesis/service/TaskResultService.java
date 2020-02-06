package com.thesis.service;

import com.thesis.entity.TaskResult;
import com.thesis.form.TaskResultForm;
import com.thesis.utils.Query;
import com.thesis.vo.TaskResultVO;

import java.util.List;

/**
 * @Author LeiPeng
 * @Date 2020/1/28 - 14:15
 */
public interface TaskResultService {
    int pageQueryCount(TaskResultForm taskResultForm, Query query);

    List<TaskResultVO> pageQuery(TaskResultForm taskResultForm, Query query);

    void updateTaskResult(TaskResult taskResult);

    void deleteTaskResultById(Integer id);

    void addTaskResult(TaskResult taskResult);
}
