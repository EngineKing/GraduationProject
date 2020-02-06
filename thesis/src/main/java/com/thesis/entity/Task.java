package com.thesis.entity;

/**
 * @Author LeiPeng
 * @Date 2020/1/26 - 10:55
 */
public class Task {
    private Integer id;
    private String title;
    private String description;
    private Long beginTime;
    private Long endTime;
    private Long resultsEndTime;
    private Integer type;
    private Integer annexId;
    private Integer pTaskId;

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

    public Integer getAnnexId() {
        return annexId;
    }

    public void setAnnexId(Integer annexId) {
        this.annexId = annexId;
    }

    public Integer getpTaskId() {
        return pTaskId;
    }

    public void setpTaskId(Integer pTaskId) {
        this.pTaskId = pTaskId;
    }
}
