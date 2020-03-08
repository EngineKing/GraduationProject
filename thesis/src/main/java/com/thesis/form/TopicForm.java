package com.thesis.form;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author LeiPeng
 * @Date 2020/1/25 - 22:20
 */
@Getter
@Setter
@ToString
public class TopicForm {
    private String name;
    private Integer taskId;
    private Integer teacherId;
    private Integer curPage;
    private Integer limit;
}
