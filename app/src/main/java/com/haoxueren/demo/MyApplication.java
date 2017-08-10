package com.haoxueren.demo;

import android.app.Application;

import com.haoxueren.helper.ToastHelper;

/**
 * 自定义Application
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ToastHelper.init(getApplicationContext());
    }
}
