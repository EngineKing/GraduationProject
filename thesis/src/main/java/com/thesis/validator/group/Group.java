package com.thesis.validator.group;

import javax.validation.GroupSequence;

/**
 * @Author LeiPeng
 * @Date 2020/1/13 - 22:02
 */
@GroupSequence({AddGroup.class, UpdateGroup.class})
public interface Group {
}
