package com.thesis.form;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author LeiPeng
 * @Date 2020/2/25 - 8:41
 *
 * 学生任务查询条件类
 */

@Getter
@Setter
public class StudentTopicForm {
    private String number;
    private String name;
    private Integer limit;
    private Integer curPage;
}
