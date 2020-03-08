package com.thesis.controller;

import com.thesis.entity.Department;
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
    @PostMapping("/add")
    public R add(@RequestBody Role role) {
        ValidatorUtils.validate(role, AddGroup.class);
        roleService.add(role);
        return R.ok();
    }

    /**
     * 通过部门id删除角色信息
     *
     * @param id 部门id
     * @return 删除是否成功
     */
    @PostMapping("/delete")
    public R delete(Integer id) {
        Assert.isNull(id, "id不能为空");
        roleService.delete(id);
        return R.ok();
    }


    /**
     * 更新角色信息
     *
     * @param role 角色信息
     * @return 更新是否成功
     */
    @PostMapping("/update")
    public R update(@RequestBody Role role) {
        ValidatorUtils.validate(role, AddGroup.class);
        roleService.update(role);
        return R.ok();
    }

    /**
     * 分页查询
     *
     * @param roleForm 查询条件类
     * @return 页面信息
     */
    @PostMapping("/pageQuery")
    public R pageQuery(@RequestBody RoleForm roleForm) {
        Query query = new Query(roleForm.getCurPage(), roleForm.getLimit());
        List<RoleVO> roleList = roleService.pageQuery(roleForm, query);
        int total = roleService.pageQueryCount(roleForm, query);
        Page rolePage = new Page(query, total, roleList);
        return R.ok().put("rolePage", rolePage);
    }

    @PostMapping("/getAll")
    public R getAll(){
        List<Role> roles = roleService.getAll();
        return R.ok().put("roles", roles);
    }
}
