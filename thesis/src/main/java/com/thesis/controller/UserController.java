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
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
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
    
    /**
     * 新增用户
     * @param user 用户信息
     * @return 是否成功
     */
    @PostMapping("/add")
    public R add(@RequestBody User user) {
        ValidatorUtils.validate(user, AddGroup.class);
        userService.add(user);
        return R.ok();
    }

    /**
     * 根据用户id获取用户信息
     * @param id 用户id
     * @return 用户信息
     */
    @PostMapping("/get/{id}")
    public R get(@PathVariable(name = "id") Integer id) {
        Assert.isNull(id, "id不能为空");
        User user = userService.get(id);
        return R.ok().put("user", user);
    }

    /**
     * 通过用户id删除用户信息
     *
     * @param id 用户id
     * @return 是否成功
     */
    @PostMapping("/delete")
    public R delete(Integer id) {
        Assert.isNull(id, "id不能为空");
        userService.delete(id);
        return R.ok();
    }

    /**
     * 修改用户信息
     *
     * @param user 用户信息
     * @return 修改是否成功
     */
    @PostMapping("/update")
    public R update(@RequestBody User user) {
        ValidatorUtils.validate(user, AddGroup.class);
        userService.update(user);
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

    @PostMapping("/login")
    public R login(String account, String pwd){
        R result = null;
        // 1.获取Subject
        Subject subject = SecurityUtils.getSubject();
        // 2.封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(account, pwd);
        // 3.执行登录方法
        try {
            subject.login(token);
            // 登录成功
            result = R.ok();
        } catch (UnknownAccountException e){
            // 登录失败:用户名不存在
            result = R.error(500, "用户账号不存在");
        } catch (IncorrectCredentialsException e){
            // 登录失败：密码错误
            result = R.error(500, "密码错误");
        }
        return result;
    }
}
