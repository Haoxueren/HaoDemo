package com.haoxueren.demo.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;

import com.haoxueren.demo.BaseActivity;
import com.haoxueren.demo.R;

/** 本地广播 */
public class LocalBroadcastManagerActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localbroadcastmanager);
        LocalBroadcastManagerActivity context = this;

        Intent intent = new Intent();
        intent.setAction("action");
        intent.putExtra("name", "haoxueren");
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
