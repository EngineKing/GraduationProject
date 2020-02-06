package com.thesis.service;

import com.thesis.entity.Menu;
import com.thesis.form.MenuForm;
import com.thesis.utils.Query;
import com.thesis.vo.MenuVO;

import java.util.List;

/**
 * @Author LeiPeng
 * @Date 2020/1/19 - 23:18
 */
public interface MenuService {
    void addMenu(Menu menu);

    void deleteMenuById(Integer id);

    void updateMenu(Menu menu);

    List<MenuVO> pageQuery(MenuForm menuForm, Query query);

    int pageQueryCount(MenuForm menuForm, Query query);
}
