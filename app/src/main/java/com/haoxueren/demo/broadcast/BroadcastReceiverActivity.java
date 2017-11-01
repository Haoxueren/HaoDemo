package com.haoxueren.demo.broadcast;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.haoxueren.demo.R;

public class BroadcastReceiverActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_receiver);
    }

    /**
     * 本地广播接收者
     */
    public static class LocalReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            System.out.println("LocalReceiver：" + intent.getStringExtra("name"));
        }
    }
}
