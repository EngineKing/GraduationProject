package com.thesis.service.impl;

import com.thesis.dao.MenuDao;
import com.thesis.dao.RoleDao;
import com.thesis.dao.UserDao;
import com.thesis.dao.UserRoleDao;
import com.thesis.entity.Menu;
import com.thesis.entity.Role;
import com.thesis.entity.User;
import com.thesis.entity.UserRole;
import com.thesis.exception.RRException;
import com.thesis.form.RoleForm;
import com.thesis.service.RoleService;
import com.thesis.utils.Query;
import com.thesis.utils.R;
import com.thesis.vo.RoleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Author LeiPeng
 * @Date 2020/1/15 - 20:36
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleDao roleDao;

    @Autowired
    UserDao userDao;

    @Autowired
    MenuDao menuDao;

    @Autowired
    UserRoleDao userRoleDao;

    /**
     * 新增角色
     *
     * @param role 角色信息
     */
    @Override
    public void add(Role role) {
        Role foundRole = roleDao.getByName(role.getName());
        if (foundRole != null) throw new RRException("新增角色失败，该角色名称已经存在");
        roleDao.add(role);
    }

    /**
     * 根据角色id删除角色信息
     *
     * @param id 角色id
     */
    @Override
    public void delete(Integer id) {
        Role foundRole = roleDao.get(id);
        if (foundRole == null) throw new RRException("删除角色失败，该角色不存在");
        List<Integer> userIds = userRoleDao.getByRoleId(id);
        if (!userIds.isEmpty()) throw new RRException("删除角色失败，该角色存在用户");
        roleDao.delete(id);
    }

    /**
     * 更新角色信息
     *
     * @param role 角色信息
     */
    @Override
    public void update(Role role) {
        Role foundRole = roleDao.getByName(role.getName());
        if (foundRole != null && foundRole.getId() != role.getId()) throw new RRException("更新角色失败，该角色名称已存在");
        roleDao.update(role);
    }

    /**
     * 分页查询
     * @param roleForm 查询条件
     * @param query 页
     * @return 查询结果
     */
    @Override
    public List<RoleVO> pageQuery(RoleForm roleForm, Query query) {
        List<Role> roles = roleDao.pageQuery(roleForm, query);
        List<RoleVO> roleVOS = new ArrayList<>();
        for (int i = 0; i < roles.size(); i++) {
            RoleVO roleVO = new RoleVO();
            roleVO.setId(roles.get(i).getId());
            roleVO.setName(roles.get(i).getName());
            roleVO.setDescription(roles.get(i).getDescription());
            roleVOS.add(roleVO);
        }
        return roleVOS;
    }

    /**
     * 计算满足条件的综述
     * @param roleForm 查询条件
     * @param query 页
     * @return 满足条件的总数
     */
    @Override
    public int pageQueryCount(RoleForm roleForm, Query query) {
        return roleDao.pageQueryCount(roleForm, query);
    }

    @Override
    public List<Role> getAll() {
        return roleDao.getAllRoles();
    }
}
