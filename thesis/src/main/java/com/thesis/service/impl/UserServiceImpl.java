package com.thesis.service.impl;

import com.thesis.dao.DepartmentDao;
import com.thesis.dao.RoleDao;
import com.thesis.dao.UserDao;
import com.thesis.dao.UserRoleDao;
import com.thesis.entity.Department;
import com.thesis.entity.Role;
import com.thesis.entity.User;
import com.thesis.exception.RRException;
import com.thesis.form.UserForm;
import com.thesis.utils.DateUtil;
import com.thesis.utils.Query;
import com.thesis.vo.DepartmentVO;
import com.thesis.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author LeiPeng
 * @Date 2020/1/13 - 22:13
 */
@Service
public class UserServiceImpl implements com.thesis.service.UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    DepartmentDao departmentDao;

    @Autowired
    UserRoleDao userRoleDao;

    /**
     * 新增用户
     *
     * @param user 用户信息
     */
    @Override
    public void add(User user) {
        User foundUser = userDao.getUserByAccount(user.getAccount());
        if (foundUser != null) throw new RRException("新增用户失败，该用户账号已存在");
        user.setCreateTime(DateUtil.timeStamp());
        userDao.add(user);
    }

    /**
     * 根据用户id获取用户
     *
     * @param id 用户id
     * @return 用户信息
     */
    @Override
    public User get(Integer id) {
        return userDao.get(id);
    }

    /**
     * 根据用户id删除用户
     *
     * @param id 用户id
     */
    @Override
    public void delete(Integer id) {
        User foundUser = userDao.get(id);
        if (foundUser == null) throw new RRException("删除用户失败，该用户不存在");
        userDao.delete(id);
    }

    /**
     * 更新用户
     *
     * @param user 用户信息
     */
    @Override
    public void update(User user) {
        User foundUser = userDao.getUserByAccount(user.getAccount());
        if (foundUser != null && foundUser.getId() != user.getId()) throw new RRException("修改用户失败，该用户账号已存在");
        userDao.update(user);
    }

    /**
     * 分页查询
     *
     * @param userForm 查询条件类
     * @param query    页
     * @return 查询结果
     */
    @Override
    public List<UserVO> pageQuery(UserForm userForm, Query query) {
        List<User> users = userDao.pageQuery(userForm, query);
        List<UserVO> userVOS = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            UserVO userVO = new UserVO();
            User user = users.get(i);
            userVO.setId(user.getId());
            userVO.setAccount(user.getAccount());
            userVO.setGender(user.getGender());
            userVO.setEmail(user.getEmail());
            userVO.setPhone(user.getPhone());
            userVO.setCreateTime(user.getCreateTime());
            userVO.setUpdateTime(user.getUpdateTime());
            userVO.setDepartment(departmentDao.get(user.getDepartmentId()));
            userVO.setLoginTimes(user.getLoginTimes());
            userVO.setStatus(user.getStatus());
            userVOS.add(userVO);
        }
        return userVOS;
    }

    /**
     * 查询 满足条件的总数
     *
     * @param userForm 查询条件类
     * @param query    页
     * @return 总数
     */
    @Override
    public int pageQueryCount(UserForm userForm, Query query) {
        return userDao.pageQueryCount(userForm, query);
    }
}
