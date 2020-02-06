package com.thesis.service.impl;

import com.thesis.dao.DepartmentDao;
import com.thesis.dao.RoleDao;
import com.thesis.dao.UserDao;
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
    RoleDao roleDao;

    /**
     * 新增用户
     *
     * @param user 用户信息
     */
    @Override
    public void addUser(User user) {
        User foundUser = userDao.getUserByAccount(user.getAccount());
        if (foundUser != null) throw new RRException("新增用户失败，该用户账号已存在");
        userDao.addUser(user);
    }

    /**
     * 根据用户id获取用户
     *
     * @param id 用户id
     * @return 用户信息
     */
    @Override
    public User getUserById(Integer id) {
        return userDao.getUserById(id);
    }

    /**
     * 根据用户id删除用户
     *
     * @param id 用户id
     */
    @Override
    public void deleteUserById(Integer id) {
        User foundUser = userDao.getUserById(id);
        if (foundUser == null) throw new RRException("删除用户失败，该用户不存在");
        userDao.deleteUserById(id);
    }

    /**
     * 更新用户
     *
     * @param user 用户信息
     */
    @Override
    public void updateUser(User user) {
        User foundUser = userDao.getUserByAccount(user.getAccount());
        if (foundUser != null && foundUser.getId() != user.getId()) throw new RRException("修改用户失败，该用户账号已存在");
        userDao.updateUser(user);
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
        List<Role> roles = roleDao.getAllRoles();
        HashMap<Integer, Role> map = new HashMap<>();
        for (int i = 0; i < roles.size(); i++) {
            map.put(roles.get(i).getId(), roles.get(i));
        }
        for (int i = 0; i < users.size(); i++) {
            UserVO userVO = new UserVO();
            User user = users.get(i);
            List<Integer> roleIdsToUser = userDao.getRoleIdsByUserId(user.getId());
            StringBuilder roleName = new StringBuilder();
            for (int j = 0; j < roleIdsToUser.size() - 1; j++) {
                roleName.append(roleDao.getRoleById(roleIdsToUser.get(i)).getName()).append(",");
            }
            roleName.append(roleDao.getRoleById(roleIdsToUser.get(roleIdsToUser.size() - 1)).getName());
            userVO.setId(user.getId());
            userVO.setAccount(user.getAccount());
            userVO.setGender(user.getGender() == 0 ? "男" : "女");
            userVO.setEmail(user.getEmail());
            userVO.setPhone(user.getPhone());
            userVO.setCreateTime(DateUtil.timeStamp2Date(user.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
            userVO.setDepartmentName(departmentDao.getDepartmentById(user.getDepartmentId()).getName());
            userVO.setRoleName(roleName.toString());
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
