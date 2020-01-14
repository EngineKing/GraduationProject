package com.thesis.controller;

import com.thesis.entity.User;
import com.thesis.service.UserService;
import com.thesis.utils.R;
import com.thesis.validator.ValidatorUtils;
import com.thesis.validator.group.AddGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author LeiPeng
 * @Date 2020/1/13 - 22:02
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    public R addUser(User user){
        ValidatorUtils.validate(user, AddGroup.class);
        userService.addUser(user);
        return R.ok();
    }

    @RequestMapping("/getUserById")
    public R getUserById(Integer id){
        if (id == null) return R.error(500, "id为空");
        User user = userService.getUserById(id);
        return R.ok().put("user", user);
    }
}
