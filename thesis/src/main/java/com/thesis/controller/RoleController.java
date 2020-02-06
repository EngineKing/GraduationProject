package com.thesis.controller;

import com.thesis.entity.Role;
import com.thesis.form.RoleForm;
import com.thesis.service.RoleService;
import com.thesis.utils.Page;
import com.thesis.utils.Query;
import com.thesis.utils.R;
import com.thesis.validator.Assert;
import com.thesis.validator.ValidatorUtils;
import com.thesis.validator.group.AddGroup;
import com.thesis.vo.RoleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author LeiPeng
 * @Date 2020/1/15 - 20:35
 */
@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    RoleService roleService;

    /**
     * 新增角色信息
     *
     * @param role 角色
     * @return 新增是否成功
     */
    @PostMapping("/addRole")
    public R addRole(@RequestBody Role role) {
        ValidatorUtils.validate(role, AddGroup.class);
        roleService.addRole(role);
        return R.ok();
    }

    /**
     * 通过部门id删除角色信息
     *
     * @param id 部门id
     * @return 删除是否成功
     */
    @PostMapping("/deleteRoleById/{id}")
    public R deleteRoleById(@PathVariable(name = "id") Integer id) {
        Assert.isNull(id, "id不能为空");
        roleService.deleteRoleById(id);
        return R.ok();
    }


    /**
     * 更新角色信息
     *
     * @param role 角色信息
     * @return 更新是否成功
     */
    @PostMapping("/updateRole")
    public R updateRole(@RequestBody Role role) {
        ValidatorUtils.validate(role, AddGroup.class);
        roleService.updateRole(role);
        return R.ok();
    }

    /**
     * 分页查询
     *
     * @param roleForm 查询条件类
     * @param curPage  当前页
     * @param limit    页面大小
     * @return 页面信息
     */
    @PostMapping("/pageQuery")
    public R pageQuery(RoleForm roleForm, Integer curPage, Integer limit) {
        Query query = new Query(curPage, limit);
        List<RoleVO> roleList = roleService.pageQuery(roleForm, query);
        int total = roleService.pageQueryCount(roleForm, query);
        Page rolePage = new Page(query, total, roleList);
        return R.ok().put("rolePage", rolePage);
    }
}
