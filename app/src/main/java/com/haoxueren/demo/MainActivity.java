package com.haoxueren.demo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.graphics.ColorUtils;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.haoxueren.demo.textview.TextViewActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

@SuppressLint("SetTextI18n")
public class MainActivity extends Activity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this, this.getWindow().getDecorView());
        initRecyclerView(recyclerView);
    }

    @NonNull
    private List<HaoDemo> getAdapterData() {
        List<HaoDemo> list = new ArrayList<>();
        list.add(new HaoDemo("Activity的生命周期", null));
        list.add(new HaoDemo("Fragment的生命周期", null));
        list.add(new HaoDemo("自定义控件", null));
        list.add(new HaoDemo("Dagger2使用示例", null));
        list.add(new HaoDemo("Android屏幕适配的几种方法", null));
        list.add(new HaoDemo("Android本地广播使用示例", null));
        list.add(new HaoDemo("BroadcastReceiver使用示例", null));
        list.add(new HaoDemo("OkHttp使用示例", null));
        list.add(new HaoDemo("RxJava使用示例", null));
        list.add(new HaoDemo("ButterKnife使用示例", null));
        list.add(new HaoDemo("Service的生命周期", null));
        list.add(new HaoDemo("自定义RecyclerView的ItemDecoration", null));
        list.add(new HaoDemo("RecyclerView上拉加载更多", null));
        list.add(new HaoDemo("RecyclerView添加Header和Footer", null));
        list.add(new HaoDemo("RecyclerView拖动排序", null));
        list.add(new HaoDemo("RecyclerView侧滑删除", null));
        list.add(new HaoDemo("TextView汉字竖直显示", TextViewActivity.class));
        list.add(new HaoDemo("Retrofit使用示例", null));
        list.add(new HaoDemo("", null));
        list.add(new HaoDemo("", null));
        return list;
    }


    private void initRecyclerView(RecyclerView recyclerView) {
        // 设置Adapter
        HaoAdapter adapter = new HaoAdapter(getAdapterData());
        recyclerView.setAdapter(adapter);
        // 创建LayoutManager
        int spanCount = 12;
        int orientation = StaggeredGridLayoutManager.HORIZONTAL;
        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(spanCount, orientation);
        // 设置LayoutManager
        recyclerView.setLayoutManager(layoutManager);
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

    public interface OnItemClickListener {
        void onItemClick(View v, int position);
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
            Random random = new Random();
            int red = random.nextInt(256);
            int green = random.nextInt(256);
            int blue = random.nextInt(256);
            int background = Color.argb(0x66, red, green, blue);
            int textColor = Color.rgb(256 - red, 256 - green, 256 - blue);
            holder.itemView.setBackgroundColor(background);
            holder.itemTextView.setTextColor(textColor);
            final HaoDemo haoDemo = list.get(position);
            holder.itemTextView.setText(haoDemo.getTitle());
            // 设置条目点击监听器
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Class<? extends Activity> clazz = haoDemo.getClazz();
                    if (clazz != null) {
                        Intent intent = new Intent(context, clazz);
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
