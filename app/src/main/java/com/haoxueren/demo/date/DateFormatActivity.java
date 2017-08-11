package com.haoxueren.demo.date;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.haoxueren.demo.R;
import com.orhanobut.logger.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DateFormatActivity extends Activity {

    @BindView(R.id.textView)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_format);
        setTitle("格式化带毫秒值的日期");
        ButterKnife.bind(this);
        try {
            String date1 = formatDate("yyyy-MM-dd HH:mm:ss:SSS");
            String date2 = formatDate("yyyy-MM-dd HH:mm:ss a");
            textView.append(date1 + "\n\n");
            textView.append(date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String formatDate(String pattern) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(pattern, Locale.CHINA);
        String dateText = format.format(new Date());
        Logger.d(dateText);
        return dateText;
    }
}
