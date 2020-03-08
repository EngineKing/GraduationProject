package com.thesis.form;

/**
 * @Author LeiPeng
 * @Date 2020/1/16 - 21:21
 */
public class RoleForm {
    private String name;
    private Integer curPage;
    private Integer limit;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
