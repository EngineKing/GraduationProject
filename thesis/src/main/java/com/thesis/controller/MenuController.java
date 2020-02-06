package com.thesis.controller;

import com.thesis.entity.Department;
import com.thesis.entity.Menu;
import com.thesis.form.DepartmentForm;
import com.thesis.form.MenuForm;
import com.thesis.service.MenuService;
import com.thesis.utils.Page;
import com.thesis.utils.Query;
import com.thesis.utils.R;
import com.thesis.validator.Assert;
import com.thesis.validator.ValidatorUtils;
import com.thesis.validator.group.AddGroup;
import com.thesis.vo.DepartmentVO;
import com.thesis.vo.MenuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author LeiPeng
 * @Date 2020/1/19 - 20:57
 */
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    MenuService menuService;

    /**
     * 新增菜单信息
     *
     * @param menu 部门信息
     * @return 是否成功
     */
    @PostMapping("/addMenu")
    public R addMenu(@RequestBody Menu menu) {
        ValidatorUtils.validate(menu, AddGroup.class);
        menuService.addMenu(menu);
        return R.ok();
    }

    /**
     * 通过部门id删除菜单信息
     *
     * @param id 菜单id
     * @return 是否成功
     */
    @PostMapping("/deleteMenuById/{id}")
    public R deleteMenuById(@PathVariable(name = "id") Integer id) {
        Assert.isNull(id, "id不能为空");
        menuService.deleteMenuById(id);
        return R.ok();
    }

    /**
     * 修改部门信息
     *
     * @param menu 菜单信息
     * @return 修改是否成功
     */
    @PostMapping("/updateMenu")
    public R updateMenu(@RequestBody Menu menu) {
        ValidatorUtils.validate(menu, AddGroup.class);
        menuService.updateMenu(menu);
        return R.ok();
    }

    /**
     * 分页查询
     * @param menuForm 查询条件
     * @param curPage 当前页
     * @param limit 页面大小
     * @return 返回是否成功
     */
    @PostMapping("/pageQuery")
    public R pageQuery(MenuForm menuForm, Integer curPage, Integer limit){
        Query query = new Query(curPage, limit);
        List<MenuVO> menuVOList = menuService.pageQuery(menuForm, query);
        int total = menuService.pageQueryCount(menuForm, query);
        Page menuPage = new Page(query, total, menuVOList);
        return R.ok().put("menuPage", menuPage);
    }
}
