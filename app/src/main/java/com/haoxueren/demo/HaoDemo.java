package com.haoxueren.demo;

import android.app.Activity;

/**
 * 示例代码的实体对象
 */
public class HaoDemo {

    public static final String TITLE = "HaoDemo.title";

    private String title;// 标题

    private String blog;// 博客地址

    private Class<? extends Activity> clazz;// 演示界面

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

    public String getBlog() {
        return blog;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }

    public Class<? extends Activity> getClazz() {
        return clazz;
    }

    public void setClazz(Class<? extends Activity> clazz) {
        this.clazz = clazz;
    }
}
