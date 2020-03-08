package com.thesis.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author LeiPeng
 * @Date 2020/2/20 - 9:29
 */
@Configuration
public class ShiroConfig {
    /**
     * 创建ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 添加shiro的内置过滤器
        /**
         * Shiro内置过滤器，可以实现权限相关的拦截器
         * 常用的过滤器：
         *      anon:无需认证（登录）可以访问
         *      authc:必须要认证才可以访问
         *      user:如果使用了remember me的功能可以直接访问
         *      perms:该资源必须得到资源权限才可以访问
         *      role：该资源必须得到角色权限才可以访问
         */
        Map<String, String> filterMap = new LinkedHashMap<>();
        /*filterMap.put("/add", "authc");
        filterMap.put("/update", "authc");*/
        // 如果是某个文件夹下的所有资源都需要认证
//        filterMap.put("/user/*", "authc");
        // 修改跳转登录的页面
//        shiroFilterFactoryBean.setLoginUrl("/user/toLogin");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 创建DefaultWebSecurityManager
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 关联realm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    /**
     * 创建Realm
     */
    @Bean(name = "userRealm")
    public UserRealm getRealm() {
        return new UserRealm();
    }
}
