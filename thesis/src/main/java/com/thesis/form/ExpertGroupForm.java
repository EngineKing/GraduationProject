package com.thesis.form;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author LeiPeng
 * @Date 2020/1/27 - 17:22
 */
@Getter
@Setter
public class ExpertGroupForm {
    private String name;
    private Integer teacherId;
    private Integer curPage;
    private Integer limit;
}
