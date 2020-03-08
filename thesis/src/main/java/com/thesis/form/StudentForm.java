package com.thesis.form;

/**
 * @Author LeiPeng
 * @Date 2020/1/28 - 14:07
 */
public class StudentForm {
    private String number;
    private String name;
    private Integer type;
//    private Integer enrollmentDate;
    private Integer subjectId;
    private Integer classId;
    private Integer curPage;
    private Integer limit;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

//    public Integer getEnrollmentDate() {
//        return enrollmentDate;
//    }
//
//    public void setEnrollmentDate(Integer enrollmentDate) {
//        this.enrollmentDate = enrollmentDate;
//    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
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
