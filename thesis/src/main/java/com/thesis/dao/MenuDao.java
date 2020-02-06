package com.thesis.dao;

import com.thesis.entity.Menu;
import com.thesis.form.MenuForm;
import com.thesis.utils.Query;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author LeiPeng
 * @Date 2020/1/19 - 23:17
 */
public interface MenuDao {
    List<Menu> getAllMenus();

    void addMenu(Menu menu);

    Menu getMenuById(@Param("menuId") Integer id);

    void deleteMenuById(@Param("menuId") Integer id);

    void updateMenu(Menu menu);

    List<Menu> pageQuery(@Param("menuForm") MenuForm menuForm, @Param("query") Query query);

    int pageQueryCount(@Param("menuForm") MenuForm menuForm, @Param("query") Query query);

    Menu getMenuByName(@Param("menuName") String name);
}
