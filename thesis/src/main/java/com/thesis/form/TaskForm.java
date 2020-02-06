package com.thesis.form;

/**
 * @Author LeiPeng
 * @Date 2020/1/26 - 21:47
 */
public class TaskForm {
    private String title;
    private Long beginTime;
    private Long endTime;
    private Long resultsEndTime;
    private Integer type;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
}
