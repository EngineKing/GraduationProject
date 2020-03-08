package com.thesis.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author LeiPeng
 * @Date 2020/1/27 - 17:17
 */
@Getter
@Setter
public class ExpertGroup {
    private Integer id;
    private String name;
    private Integer teacherId;
    private Integer status;
}
