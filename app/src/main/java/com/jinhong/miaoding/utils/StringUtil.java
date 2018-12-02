package com.jinhong.miaoding.utils;

/**
 * Created by chrc on 2018/11/6.
 */

public class StringUtil {

    public static boolean isEmpty(String str) {
        if (str == null || "".equals(str.trim())) {
            return true;
        }
        return false;
    }
}
