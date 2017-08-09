package com.haoxueren.demo;

import android.app.Activity;

/**
 * 示例代码的实体对象
 */
public class HaoDemo {

    private String title;

    private Class<? extends Activity> clazz;

    public HaoDemo(String title, Class<? extends Activity> clazz) {
        this.title = title;
        this.clazz = clazz;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Class<? extends Activity> getClazz() {
        return clazz;
    }

    public void setClazz(Class<? extends Activity> clazz) {
        this.clazz = clazz;
    }
}
