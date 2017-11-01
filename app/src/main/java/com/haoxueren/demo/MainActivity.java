package com.haoxueren.demo;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.haoxueren.demo.andjump.AndJumpActivity;
import com.haoxueren.demo.dagger2.DaggerActivity;
import com.haoxueren.demo.shader.FlashActivity;
import com.haoxueren.demo.shader.ShaderActivity;
import com.haoxueren.demo.broadcast.LocalBroadcastManagerActivity;
import com.haoxueren.demo.date.DateFormatActivity;
import com.haoxueren.demo.fastjson.FastJsonActivity;
import com.haoxueren.demo.flexboxlayout.FlexboxActivity;
import com.haoxueren.demo.glide.GlideDemoActivity;
import com.haoxueren.demo.greendao.HaoNoteActivity;
import com.haoxueren.demo.retrofit.RetrofitDemoActivity;
import com.haoxueren.demo.retrofit.RetrofitImageActivity;
import com.haoxueren.demo.rxjava.RxJavaActivity;
import com.haoxueren.demo.textview.TextViewActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

@TargetApi(21)
@SuppressLint("SetTextI18n")
public class MainActivity extends Activity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initRecyclerView(recyclerView);
        MainActivity context = this;

        LocalBroadcastManager.getInstance(context).registerReceiver(
                new BroadcastReceiver() {
                    @Override
                    public void onReceive(Context context, Intent intent) {
                        System.out.println("onReceive");
                        // 处理通过Intent传递过来的数据
                        String action = intent.getAction();
                        String extra = intent.getStringExtra("name");
                        // 注销BroadcastReceiver，释放资源
                        LocalBroadcastManager.getInstance(context).unregisterReceiver(this);
                    }
                }, new IntentFilter("action"));


    }


    @NonNull
    private List<HaoDemo> getAdapterData() {
        List<HaoDemo> list = new ArrayList<>();
        list.add(new HaoDemo("Dagger2", DaggerActivity.class));
        list.add(new HaoDemo("AndJump", AndJumpActivity.class));
        list.add(new HaoDemo("FlashTextView", FlashActivity.class));
        list.add(new HaoDemo("Shader", ShaderActivity.class));
        list.add(new HaoDemo("LocalBroadcastManager", LocalBroadcastManagerActivity.class));
        list.add(new HaoDemo("FastJson", FastJsonActivity.class));
        list.add(new HaoDemo("FlexboxLayout", FlexboxActivity.class));
        list.add(new HaoDemo("RxJava", RxJavaActivity.class));
        list.add(new HaoDemo("GreenDao", HaoNoteActivity.class));
        list.add(new HaoDemo("Retrofit", RetrofitDemoActivity.class));
        list.add(new HaoDemo("Glide", GlideDemoActivity.class));
        list.add(new HaoDemo("SimpleDateFormat", DateFormatActivity.class));
        list.add(new HaoDemo("RecycleView", RetrofitImageActivity.class));
        list.add(new HaoDemo("TextView汉字竖直显示", TextViewActivity.class));
        list.add(new HaoDemo("Android屏幕适配", null));
        list.add(new HaoDemo("解决RecyclerView异步加载图片错位的问题", null));
        list.add(new HaoDemo("Activity的生命周期", null));
        list.add(new HaoDemo("Fragment的生命周期", null));
        list.add(new HaoDemo("自定义控件", null));
        list.add(new HaoDemo("Dagger2", null));
        list.add(new HaoDemo("Android本地广播", null));
        list.add(new HaoDemo("BroadcastReceiver", null));
        list.add(new HaoDemo("OkHttp", null));
        list.add(new HaoDemo("ButterKnife", null));
        list.add(new HaoDemo("Service的生命周期", null));
        list.add(new HaoDemo("自定义RecyclerView的ItemDecoration", null));
        list.add(new HaoDemo("RecyclerView上拉加载更多", null));
        list.add(new HaoDemo("RecyclerView添加Header和Footer", null));
        list.add(new HaoDemo("RecyclerView拖动排序", null));
        list.add(new HaoDemo("RecyclerView侧滑删除", null));
        list.add(new HaoDemo("WebSocket", null));
        list.add(new HaoDemo("Android常用的性能优化工具", null));
        list.add(new HaoDemo("内在优化及如何避免OOM", null));
        list.add(new HaoDemo("Android触摸事件的传递机制", null));
        list.add(new HaoDemo("Android跨进程通信的几种方式", null));
        list.add(new HaoDemo("Android6.0运行时权限", null));
        list.add(new HaoDemo("Android7.0新特性", null));
        list.add(new HaoDemo("Java GC原理", null));
        list.add(new HaoDemo("Android Handler的原理", null));
        list.add(new HaoDemo("Android Binder机制原理", null));
        list.add(new HaoDemo("Gson", null));
        list.add(new HaoDemo("深入理解网络图片加载", null));
        list.add(new HaoDemo("Java的动态代理模式", null));
        list.add(new HaoDemo("Android中Cookie的使用", null));
        list.add(new HaoDemo("AIDL", null));
        list.add(new HaoDemo("", null));
        list.add(new HaoDemo("", null));
        list.add(new HaoDemo("", null));
        list.add(new HaoDemo("", null));
        list.add(new HaoDemo("", null));
        list.add(new HaoDemo("", null));
        list.add(new HaoDemo("", null));
        list.add(new HaoDemo("", null));
        return list;
    }


    private void initRecyclerView(RecyclerView recyclerView) {
        // 创建StaggeredGridLayoutManager
        // int spanCount = 10;
        // int orientation = StaggeredGridLayoutManager.HORIZONTAL;
        // RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(spanCount, orientation);
        // 设置 FlexboxLayoutManager
        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(this);
        flexboxLayoutManager.setFlexDirection(FlexDirection.ROW);
        flexboxLayoutManager.setJustifyContent(JustifyContent.FLEX_START);
        recyclerView.setLayoutManager(flexboxLayoutManager);
        // 设置Adapter
        HaoAdapter adapter = new HaoAdapter(getAdapterData());
        recyclerView.setAdapter(adapter);
        // 设置条目动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        // 设置条目分割线
        Context context = getApplicationContext();
        int horizontal = DividerItemDecoration.HORIZONTAL;
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(context, horizontal);
        // recyclerView.addItemDecoration(dividerItemDecoration);
    }


    /**
     * 必须继承RecyclerView.ViewHolder
     */
    class HaoViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.itemTextView)
        TextView itemTextView;

        public HaoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    /**
     * RecyclerView的Adapter需要继承RecyclerView.Adapter<ViewHolder>
     */
    class HaoAdapter extends RecyclerView.Adapter<HaoViewHolder> {

        private Context context;
        private List<HaoDemo> list;

        public HaoAdapter(List<HaoDemo> list) {
            this.list = list;
            context = getApplicationContext();
        }


        @Override
        public HaoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // 创建ViewHolder：需要提供一个itemView
            View itemView = View.inflate(context, R.layout.layout_recycler, null);
            return new HaoViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(HaoViewHolder holder, final int position) {
            // 设置条目布局中控件的属性
            holder.itemTextView.setTextColor(Color.BLACK);
            final HaoDemo haoDemo = list.get(position);
            holder.itemTextView.setText(haoDemo.getTitle());
            ViewGroup.LayoutParams layoutParams = holder.itemTextView.getLayoutParams();
            // layoutParams.setFlexGrow(1.0f);
            // holder.itemView.setLayoutParams(layoutParams);
            // 设置条目点击监听器
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Class<? extends Activity> clazz = haoDemo.getClazz();
                    if (clazz != null) {
                        Intent intent = new Intent(context, clazz);
                        intent.putExtra(HaoDemo.TITLE, haoDemo.getTitle());
                        startActivity(intent);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            // 返回条目的数量
            return list.size();
        }
    }


}
