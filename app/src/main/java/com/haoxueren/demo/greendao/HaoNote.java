package com.haoxueren.demo.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;

/**
 * 使用GreenDao操作数据库的实体类
 */
@Entity
public class HaoNote {
    @Id(autoincrement = true)
    private Long id;

    @Property(nameInDb = "TAG")
    private String tag;

    @NotNull
    private String note;

    private String user;

    @Generated(hash = 1180243073)
    public HaoNote(Long id, String tag, @NotNull String note, String user) {
        this.id = id;
        this.tag = tag;
        this.note = note;
        this.user = user;
    }

    @Generated(hash = 1431015879)
    public HaoNote() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTag() {
        return this.tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getUser() {
        return this.user;
    }

    public void setUser(String user) {
        this.user = user;
    }


}
