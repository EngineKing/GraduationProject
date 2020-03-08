package com.thesis.utils;

/**
 * @Author LeiPeng
 * @Date 2020/3/5 - 19:35
 */
public class DataUtil {
    public static String getTeacherJobTitle(Integer jobTitle){
        String res = "";
        if (jobTitle == 0) {
            res = "助教";
        } else if (jobTitle == 1) {
            res = "讲师";
        } else if (jobTitle == 2) {
            res = "副教授";
        } else {
            res = "教授";
        }
        return res;
    }
}
