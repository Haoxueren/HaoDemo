package com.haoxueren.helper;

import android.util.Log;

/**
 * Android Log 工具类；
 */
public class MyLog {

    public static void info(Object... objects) {
        if (objects == null) {
            return;
        }
        for (Object object : objects) {
            Log.i("Haoxueren", object.toString());
        }
    }
}
