package com.thesis.service;

import com.thesis.entity.User;
/**
 * @Author LeiPeng
 * @Date 2020/1/13 - 22:13
 */
public interface UserService {
    void addUser(User user);

    User getUserById(Integer id);
}
