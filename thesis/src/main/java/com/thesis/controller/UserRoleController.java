package com.thesis.controller;

import com.thesis.form.UserRoleForm;
import com.thesis.service.UserRoleService;
import com.thesis.utils.R;
import com.thesis.validator.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author LeiPeng
 * @Date 2020/2/9 - 16:26
 */
@RestController
@RequestMapping("/userRole")
public class UserRoleController {
    @Autowired
    private UserRoleService userRoleService;

    @PostMapping("/getByUserId")
    public R getByUserId(Integer id){
        Assert.isNull(id, "id不能为空");
        List<Integer> userRoleIdList = userRoleService.getByUserId(id);
        return R.ok().put("userRoleIdList", userRoleIdList);
    }

    @PostMapping("/assign")
    public R assign(@RequestBody UserRoleForm userRoleForm){
        userRoleService.assign(userRoleForm);
        return R.ok();
    }
}
