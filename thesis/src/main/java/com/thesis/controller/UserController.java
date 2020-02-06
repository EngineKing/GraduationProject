package com.thesis.controller;

import com.thesis.entity.User;
import com.thesis.form.LoginForm;
import com.thesis.form.UserForm;
import com.thesis.service.UserService;
import com.thesis.utils.Page;
import com.thesis.utils.Query;
import com.thesis.utils.R;
import com.thesis.validator.Assert;
import com.thesis.validator.ValidatorUtils;
import com.thesis.validator.group.AddGroup;
import com.thesis.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author LeiPeng
 * @Date 2020/1/13 - 22:02
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

//    @PostMapping("/login")
//    public void login(@RequestBody LoginForm loginForm){
//        System.out.println(loginForm.getUsername() + "-----------" + loginForm.getPassword());
//    }
    
    /**
     * 新增用户
     * @param user 用户信息
     * @return 是否成功
     */
    @PostMapping("/addUser")
    public R addUser(@RequestBody User user) {
        ValidatorUtils.validate(user, AddGroup.class);
        userService.addUser(user);
        return R.ok();
    }

    /**
     * 根据用户id获取用户信息
     * @param id 用户id
     * @return 用户信息
     */
    @PostMapping("/getUserById/{id}")
    public R getUserById(@PathVariable(name = "id") Integer id) {
        Assert.isNull(id, "id不能为空");
        User user = userService.getUserById(id);
        return R.ok().put("user", user);
    }

    /**
     * 通过用户id删除用户信息
     *
     * @param id 用户id
     * @return 是否成功
     */
    @PostMapping("/deleteUserById/{id}")
    public R deleteUserById(@PathVariable(name = "id") Integer id) {
        Assert.isNull(id, "id不能为空");
        userService.deleteUserById(id);
        return R.ok();
    }

    /**
     * 修改用户信息
     *
     * @param user 用户信息
     * @return 修改是否成功
     */
    @PostMapping("/updateUser")
    public R updateUser(@RequestBody User user) {
        ValidatorUtils.validate(user, AddGroup.class);
        userService.updateUser(user);
        return R.ok();
    }

    /**
     * 分页查询
     * @param userForm 查询条件
     * @return 返回是否成功
     */
    @PostMapping("/pageQuery")
    public R pageQuery(@RequestBody UserForm userForm){
        Query query = new Query(userForm.getCurPage(), userForm.getLimit());
        List<UserVO> userVOList = userService.pageQuery(userForm, query);
        int total = userService.pageQueryCount(userForm, query);
        Page userPage = new Page(query, total, userVOList);
        return R.ok().put("userPage", userPage);
    }
}
