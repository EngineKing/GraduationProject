package com.thesis.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author LeiPeng
 * @Date 2020/2/6 - 13:23
 */
public class DateUtil {
    /**
     * Long型时间戳转换为Date
     * @param time 时间
     * @param format 格式
     * @return String类型Date
     */
    public static String timeStamp2Date(Long time, String format){
        if (time == null || time == 0) return "";
        if (format == null || format.isEmpty()) format = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(time));
    }

    /**
     * 获取当前时间戳
     * @return 时间戳
     */
    public static Long timeStamp(){
        return System.currentTimeMillis();
    }
}
