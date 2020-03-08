package com.thesis.service;

import com.thesis.entity.User;
import com.thesis.form.UserForm;
import com.thesis.utils.Query;
import com.thesis.vo.UserVO;

import java.util.List;

/**
 * @Author LeiPeng
 * @Date 2020/1/13 - 22:13
 */
public interface UserService {
    void add(User user);

    User get(Integer id);

    void delete(Integer id);

    void update(User user);

    List<UserVO> pageQuery(UserForm userForm, Query query);

    int pageQueryCount(UserForm userForm, Query query);
}
