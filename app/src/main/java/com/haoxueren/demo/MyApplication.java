package com.haoxueren.demo;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.haoxueren.demo.greendao.DaoMaster;
import com.haoxueren.demo.greendao.DaoMaster.DevOpenHelper;
import com.haoxueren.demo.greendao.DaoSession;
import com.haoxueren.demo.greendao.HaoOpenHelper;
import com.haoxueren.helper.ToastHelper;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * 自定义Application
 */
public class MyApplication extends Application {

    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        ToastHelper.init(getApplicationContext()); // 初始化 ToastHelper
        Logger.addLogAdapter(new AndroidLogAdapter()); // 初始化Logger
        // 初始化GreenDao
        DaoMaster.OpenHelper helper = new HaoOpenHelper(this, "HaoNote.db");
        SQLiteDatabase database = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(database);
        daoSession = daoMaster.newSession();
    }

    /**
     * 获取DaoSession对象
     */
    public DaoSession getDaoSession() {
        return daoSession;
    }
}
