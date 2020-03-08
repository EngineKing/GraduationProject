package com.thesis.form;

/**
 * @Author LeiPeng
 * @Date 2020/1/23 - 9:28
 */
public class SubjectForm {
    private String name;
    private Integer departmentId;
    private Integer curPage;
    private Integer limit;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getCurPage() {
        return curPage;
    }

    public void setCurPage(Integer curPage) {
        this.curPage = curPage;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
