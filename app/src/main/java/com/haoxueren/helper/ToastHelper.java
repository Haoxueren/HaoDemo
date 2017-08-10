package com.haoxueren.helper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

/**
 * 使用前需要在Application中执行初始化
 * ToastHelper.init(context);
 */
@SuppressLint("ALL")
public class ToastHelper {

    public static Context context;

    public static void init(Context context) {
        ToastHelper.context = context;
    }

    public static void showShortToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
