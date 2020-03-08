package com.thesis.config;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import sun.security.util.Password;

/**
 * @Author LeiPeng
 * @Date 2020/2/20 - 9:35
 */
public class UserRealm extends AuthorizingRealm {
    /**
     * 执行授权逻辑
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("执行授权逻辑");
        return null;
    }

    /**
     * 执行认证逻辑
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行认证逻辑");
        // 假设数据库的用户名和密码
        String account = "admin";
        String pwd = "123";

        // 编写shiro判断逻辑，判断用户名和密码
        // 1.判断用户名
        UsernamePasswordToken token1 = (UsernamePasswordToken) token;
        if (!token1.getUsername().equals(account)){
            // 用户名不存在
            return null; // shiro底层会抛出UnknownAccountException
        }
        // 2.判断密码
        return new SimpleAuthenticationInfo("", pwd, "");










































    }
}
