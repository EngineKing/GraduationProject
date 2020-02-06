package com.thesis.service.impl;

import com.thesis.dao.MenuDao;
import com.thesis.dao.RoleDao;
import com.thesis.dao.UserDao;
import com.thesis.entity.Menu;
import com.thesis.entity.Role;
import com.thesis.entity.User;
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

    /**
     * 新增角色
     *
     * @param role 角色信息
     */
    @Override
    public void addRole(Role role) {
        Role foundRole = roleDao.getRoleByName(role.getName());
        if (foundRole != null) throw new RRException("新增角色失败，该角色名称已经存在");
        roleDao.addRole(role);
    }

    /**
     * 根据角色id删除角色信息
     *
     * @param id 角色id
     */
    @Override
    public void deleteRoleById(Integer id) {
        Role foundRole = roleDao.getRoleById(id);
        if (foundRole != null) throw new RRException("删除角色失败，该角色不存在");
        List<User> users = userDao.getUsersByRoleId(id);
        if (!users.isEmpty()) throw new RRException("删除角色失败，该角色存在用户");
        roleDao.deleteRoleById(id);
    }

    /**
     * 更新角色信息
     *
     * @param role 角色信息
     */
    @Override
    public void updateRole(Role role) {
        Role foundRole = roleDao.getRoleByName(role.getName());
        if (foundRole != null && foundRole.getId() != role.getId()) throw new RRException("更新角色失败，该角色名称已存在");
        roleDao.updateRole(role);
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
        List<Menu> menus = menuDao.getAllMenus(); // 获取全部的菜单信息
        HashMap<Integer, Menu> menuMap = new HashMap<>();
        for (int i = 0; i < menus.size(); i++) { // 根据菜单建立索引表
            menuMap.put(menus.get(i).getId(), menus.get(i));
        }
        List<RoleVO> roleVOS = new ArrayList<>();
        for (int i = 0; i < roles.size(); i++) {
            RoleVO roleVO = new RoleVO();
            roleVO.setId(roles.get(i).getId());
            roleVO.setName(roles.get(i).getName());
            roleVO.setDescription(roles.get(i).getDescription());
            String[] menuIds = roles.get(i).getMenuIds().split(","); // 按照逗号分隔
            List<Menu> menuList = new ArrayList<>();
            for (int j = 0; j < menuIds.length; j++) {
                menuList.add(menuMap.get(Integer.valueOf(menuIds[i])));
            }
            roleVO.setMenus(menuList);
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
}
