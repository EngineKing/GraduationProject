package com.thesis.utils;

import java.util.HashMap;

/**
 * @Author LeiPeng
 * @Date 2020/1/13 - 22:04
 */
public class R extends HashMap<String, Object> {
    public R(int code, String message) {
        super.put("code", code);
        super.put("msg", message);
    }

    public static R ok() {
        return new R(200, "success");
    }

    public static R error(int code, String message) {
        return new R(code, message);
    }

    public static R error(String message) {
        return R.error(500, message);
    }

    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
