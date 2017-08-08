package com.haoxueren.demo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

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
    private List<String> getAdapterData() {
        List<String> list = new ArrayList<>();
        list.add("Activity的生命周期");
        list.add("Fragment的生命周期");
        list.add("Service的生命周期");
        list.add("ButterKnife使用示例");
        list.add("RxJava使用示例");
        list.add("OkHttp使用示例");
        list.add("自定义控件");
        list.add("D");
        list.add("D");
        return list;
    }

    private void initRecyclerView(RecyclerView recyclerView) {
        // 设置Adapter
        recyclerView.setAdapter(new HaoAdapter(getAdapterData()));
        // 创建LayoutManager
        int spanCount = 4;
        int orientation = StaggeredGridLayoutManager.VERTICAL;
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
     * 自定义RecyclerView的Adapter
     */
    class HaoAdapter extends RecyclerView.Adapter<HaoAdapter.HaoViewHolder> {

        private List<String> list;

        public HaoAdapter(List<String> list) {
            this.list = list;
        }

        @Override
        public HaoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = View.inflate(getApplicationContext(), R.layout.layout_recycler, null);
            return new HaoViewHolder(itemView);
        }


        @Override
        public void onBindViewHolder(HaoViewHolder holder, int position) {
            holder.itemTextView.setText(list.get(position));
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class HaoViewHolder extends RecyclerView.ViewHolder {

            @BindView(R.id.itemTextView)
            TextView itemTextView;

            public HaoViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }
}
