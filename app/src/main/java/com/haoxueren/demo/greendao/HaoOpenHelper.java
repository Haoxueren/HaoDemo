package com.haoxueren.demo.greendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Haoxueren on 2017/8/25.
 * 主要负责数据库的升级操作
 */
public class HaoOpenHelper extends DaoMaster.OpenHelper {

    public HaoOpenHelper(Context context, String name) {
        super(context, name);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("ALTER TABLE HAO_NOTE ADD COLUMN USER");
        System.out.println("数据库升级啦");
    }
}
