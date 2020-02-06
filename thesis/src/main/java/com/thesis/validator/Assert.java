package com.thesis.validator;

import com.thesis.exception.RRException;
import org.apache.commons.lang3.StringUtils;

/**
 * @Author LeiPeng
 * @Date 2020/1/13 - 22:02
 */
public class Assert {

    public static void isBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new RRException(message);
        }
    }

    public static void isNull(Object obj, String message) {
        if (obj == null) {
            throw new RRException(message);
        }
    }

    public static void isNotNull(Object object, String message) {
        if (object != null) {
            throw new RRException(message);
        }
    }

}
