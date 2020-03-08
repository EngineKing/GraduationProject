package com.thesis.service;

import com.thesis.entity.Annex;
import com.thesis.entity.TaskResult;
import com.thesis.form.TaskResultForm;
import com.thesis.utils.Query;
import com.thesis.vo.StudentResultVO;
import com.thesis.vo.TaskResultVO;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @Author LeiPeng
 * @Date 2020/1/28 - 14:15
 */
public interface TaskResultService {
    int pageQueryCount(TaskResultForm taskResultForm, Query query);

    List<TaskResultVO> pageQuery(TaskResultForm taskResultForm, Query query);

    void delete(Integer id);

    void add(TaskResult taskResult);

    Annex getAnnex(Integer taskResultId);

    void pass(Integer taskResultId);

    void fail(Integer taskResultId);

    Workbook export(String taskResultIds);

    Workbook allExport(TaskResultForm taskResultForm);

//    List<StudentResultVO> getTaskResult(Integer taskId);
//
//    List<StudentResultVO> getTaskResultWithCondition(Integer taskId, String number, String name);
}
