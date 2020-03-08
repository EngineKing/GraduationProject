package com.thesis.form;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author LeiPeng
 * @Date 2020/1/26 - 11:07
 */
@Getter
@Setter
@ToString
public class TeacherForm {
    private String number;
    private String name;
    private Integer jobTitle;
    private Integer subjectId;
    private Integer departmentId;
    private Integer curPage;
    private Integer limit;
}
