package com.thesis.service.impl;

import com.thesis.dao.MenuDao;
import com.thesis.dao.RoleDao;
import com.thesis.entity.Menu;
import com.thesis.entity.Role;
import com.thesis.exception.RRException;
import com.thesis.form.MenuForm;
import com.thesis.service.MenuService;
import com.thesis.utils.Query;
import com.thesis.vo.MenuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author LeiPeng
 * @Date 2020/1/19 - 23:18
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    MenuDao menuDao;

    @Autowired
    RoleDao roleDao;

    /**
     * 新增菜单
     *
     * @param menu 菜单信息
     */
    @Override
    public void addMenu(Menu menu) {
        Menu foundMenu = menuDao.getMenuByName(menu.getName());
        if (foundMenu != null) throw new RRException("新增菜单失败，该菜单名称已经存在");
        menuDao.addMenu(menu);
    }

    /**
     * 通过部门id删除菜单信息
     *
     * @param id 菜单id
     */
    @Override
    public void deleteMenuById(Integer id) {
        Menu foundMenu = menuDao.getMenuById(id);
        if (foundMenu == null) throw new RRException("删除菜单失败，该菜单不存在");
        List<Role> roleList = roleDao.getAllRoles();
        for (int i = 0; i < roleList.size() && roleList.get(i).getMenuIds().contains(id.toString()); i++) {
            throw new RRException("删除菜单失败，某角色拥有此菜单");
        }
        menuDao.deleteMenuById(id);
    }

    /**
     * 修改菜单信息
     *
     * @param menu 菜单信息
     */
    @Override
    public void updateMenu(Menu menu) {
        Menu foundMenu = menuDao.getMenuByName(menu.getName());
        if (foundMenu != null && foundMenu.getId() != menu.getId()) throw new RRException("修改菜单失败，该菜单名称已存在");
        menuDao.updateMenu(menu);
    }

    @Override
    public List<MenuVO> pageQuery(MenuForm menuForm, Query query) {
        List<Menu> menus = menuDao.pageQuery(menuForm, query);
        List<MenuVO> menuVOS = new ArrayList<>();
        for (int i = 0; i < menus.size(); i++) {
            MenuVO menuVO = new MenuVO();
            Menu menu = menus.get(i);
            menuVO.setId(menu.getId());
            menuVO.setName(menu.getName());
            menuVO.setpName(menuDao.getMenuById(menu.getId()).getName());
            menuVO.setOrderNo(menu.getOrderNo());
            menuVO.setPermission(menu.getPermission());
            menuVO.setRemark(menu.getRemark());
            menuVO.setType(menu.getType());
            menuVO.setStatus(menu.getStatus());
            menuVO.setUrl(menu.getUrl());
            menuVOS.add(menuVO);
        }
        return menuVOS;
    }

    @Override
    public int pageQueryCount(MenuForm menuForm, Query query) {
        return menuDao.pageQueryCount(menuForm, query);
    }
}
