package com.thesis.form;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author LeiPeng
 * @Date 2020/1/26 - 21:47
 */
@Getter
@Setter
public class TaskForm {
    private String title;
    private Integer type;
    private Integer curPage;
    private Integer limit;
}
