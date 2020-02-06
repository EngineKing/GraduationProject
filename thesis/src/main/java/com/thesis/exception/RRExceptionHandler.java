package com.thesis.exception;

import com.thesis.utils.R;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;

/**
 * 异常处理器
 *
 * @author LiangYongjie
 * @date 2018-03-07
 */
@RestControllerAdvice
public class RRExceptionHandler {

    @ExceptionHandler(RRException.class)
    public R handlerRRException(RRException e) {
        return new R(e.getCode(), e.getMsg());
    }

    @ExceptionHandler(AuthorizationException.class)
    public R handlerAuthorizationException() {
        return new R(403, "无权限");
    }

    @ExceptionHandler(AuthenticationException.class)
    public R handlerAuthenticationException() {
        return new R(401, "未登录");
    }
}
