package com.haoxueren.demo;

import android.app.Application;

import com.haoxueren.helper.ToastHelper;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * 自定义Application
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ToastHelper.init(getApplicationContext());
        Logger.addLogAdapter(new AndroidLogAdapter());
    }
}
