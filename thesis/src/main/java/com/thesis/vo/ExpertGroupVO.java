package com.thesis.vo;

import com.thesis.entity.Teacher;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author LeiPeng
 * @Date 2020/1/27 - 17:22
 */
@Getter
@Setter
public class ExpertGroupVO {
    private Integer id;
    private String name;
    private Teacher teacher;
    private Integer status;
}
