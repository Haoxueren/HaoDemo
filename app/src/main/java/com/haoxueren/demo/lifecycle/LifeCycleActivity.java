package com.haoxueren.demo.lifecycle;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.haoxueren.demo.R;
import com.haoxueren.helper.MyLog;

public class LifeCycleActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);
        MyLog.info("onCreate");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        MyLog.info("onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        MyLog.info("onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        MyLog.info("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        MyLog.info("onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        MyLog.info("onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyLog.info("onDestroy");
    }

    /**
     * 弹出对话框：观察Activity的生命周期；
     */
    public void showDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("AlertDialog");
        builder.setMessage("弹出Dialog时，Activity的生命周期怎么变化？");
        builder.setPositiveButton("OK", null);
        builder.show();
    }

    /**
     * 打开一个Dialog样式的Activity；
     */
    public void openActivity(View view) {
        Intent intent = new Intent(this, DialogThemeActivity.class);
        startActivity(intent);
    }
}
