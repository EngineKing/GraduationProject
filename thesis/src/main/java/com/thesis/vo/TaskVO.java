package com.thesis.vo;

import com.thesis.entity.Annex;
import com.thesis.entity.Task;

/**
 * @Author LeiPeng
 * @Date 2020/1/26 - 21:48
 */
public class TaskVO {
    private Integer id;
    private String title;
    private String description;
    private Long beginTime;
    private Long endTime;
    private Long resultsEndTime;
    private Integer type;
    private Annex annex;
    private Task pTask;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Long beginTime) {
        this.beginTime = beginTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Long getResultsEndTime() {
        return resultsEndTime;
    }

    public void setResultsEndTime(Long resultsEndTime) {
        this.resultsEndTime = resultsEndTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Annex getAnnex() {
        return annex;
    }

    public void setAnnex(Annex annex) {
        this.annex = annex;
    }

    public Task getpTask() {
        return pTask;
    }

    public void setpTask(Task pTask) {
        this.pTask = pTask;
    }
}
