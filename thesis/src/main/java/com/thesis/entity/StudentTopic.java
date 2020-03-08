package com.thesis.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author LeiPeng
 * @Date 2020/2/16 - 11:22
 */
@Getter
@Setter
@ToString
public class StudentTopic {
    private Integer id;
    private Integer studentId;
    private Integer topicId;
    private Integer isTutorAgree; // 0 同意 1 不同意 2 待审核
}
