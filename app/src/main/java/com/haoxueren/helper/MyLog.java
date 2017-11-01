package com.haoxueren.helper;

import android.util.Log;

/**
 * Android Log 工具类；
 */
public class MyLog {


    public static void test() {
        System.gc();
    }

    /**
     * 输出Info级别的Log信息
     */
    public static void info(Object... objects) {
        if (objects == null) {
            return;
        }
        for (Object object : objects) {
            Log.i("Haoxueren", object.toString());
        }
    }

    /**
     * 可以在基类中静态导入调用此方法，调用更方便
     */
    public static void print(Object... objects) {
        if (objects == null) {
            return;
        }
        for (Object object : objects) {
            System.out.println(object);
        }
    }

    /**
     * 打印当前线程信息
     */
    public static void printThreadInfo() {
        MyLog.info("当前线程：", Thread.currentThread().getName());
    }
}
