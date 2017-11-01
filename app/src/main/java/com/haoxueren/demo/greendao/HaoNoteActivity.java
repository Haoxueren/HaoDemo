package com.haoxueren.demo.greendao;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.haoxueren.demo.MyApplication;
import com.haoxueren.demo.R;
import com.orhanobut.logger.Logger;


import java.util.List;

public class HaoNoteActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
    }

    public void addNote(View view) {
        MyApplication application = (MyApplication) getApplication();
        DaoSession session = application.getDaoSession();
        HaoNoteDao dao = session.getHaoNoteDao();
        // 添加一条数据；
        HaoNote haoNote = new HaoNote();
        haoNote.setTag("好学人");
        haoNote.setNote("对生活充满热情，对未来充满信心。");
        haoNote.setUser("Haoxueren");
        long id = dao.insert(haoNote);
        Logger.d(id);
    }

    public void queryNote(View view) {
        MyApplication application = (MyApplication) getApplication();
        DaoSession session = application.getDaoSession();
        HaoNoteDao dao = session.getHaoNoteDao();

        List<HaoNote> list = dao.queryBuilder().
                where(HaoNoteDao.Properties.Note.like("%生活%"))
                .list();
        Logger.d(list.size());
    }
}
