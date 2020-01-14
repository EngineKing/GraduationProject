package com.thesis.service.impl;

import com.thesis.dao.UserDao;
import com.thesis.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author LeiPeng
 * @Date 2020/1/13 - 22:13
 */
@Service
public class UserService implements com.thesis.service.UserService {

    @Autowired
    UserDao userDao;

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public User getUserById(Integer id) {
        return userDao.getUserById(id);
    }
}
