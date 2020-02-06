package com.thesis.vo;

import com.thesis.entity.Menu;

import java.util.List;

/**
 * @Author LeiPeng
 * @Date 2020/1/15 - 20:40
*/
public class RoleVO {
    private int id;
    private String name;
    private String description;
    private List<Menu> menus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }
}
