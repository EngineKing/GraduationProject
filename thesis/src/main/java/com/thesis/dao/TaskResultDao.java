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
    void add(TaskResult taskResult);

    TaskResult get(@Param("taskResultId") Integer id);

    void delete(@Param("taskResultId") Integer id);

    void update(TaskResult taskResult);

    List<TaskResult> pageQuery(@Param("taskResultForm") TaskResultForm taskResultForm, @Param("query") Query query);

    int pageQueryCount(@Param("taskResultForm") TaskResultForm taskResultForm, @Param("query") Query query);

    TaskResult getByTitle(@Param("taskResultTitle") String title);

    TaskResult getBySIdAndTId(@Param("studentId") Integer sId, @Param("taskId") Integer tId);

    void updateAnnexIdBySIdAndTId(@Param("annexId") Integer annexId, @Param("studentId")Integer studentId, @Param("taskId")Integer taskId);

    List<TaskResult> getByTaskId(@Param("taskId") Integer taskId);

    void updateResult(@Param("taskResultId") Integer taskResultId, @Param("result") int result);

    List<TaskResult> getListByIds(@Param("taskResultIds") List<Integer> tIds);
}
