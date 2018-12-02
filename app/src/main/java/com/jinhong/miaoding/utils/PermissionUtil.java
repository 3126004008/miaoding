package com.jinhong.miaoding.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

/**
 * Created by chrc on 2018/11/7.
 */

public class PermissionUtil {

    public static boolean checkPermissions(Context context, String[] permissions) {
        int length = permissions.length;
        for (int i = 0; i < length; i++) {
            String permission = permissions[i];
            int result = ContextCompat.checkSelfPermission(context, permission);
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }
}
