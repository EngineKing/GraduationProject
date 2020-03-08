package com.thesis.form;

/**
 * @Author LeiPeng
 * @Date 2020/1/15 - 16:37
 */
public class DepartmentForm {
    private String name;
    private Integer pid;
    private Integer curPage;
    private Integer limit;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
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
