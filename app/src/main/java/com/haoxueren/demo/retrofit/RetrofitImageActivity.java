package com.haoxueren.demo.retrofit;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.haoxueren.demo.HaoDemo;
import com.haoxueren.demo.R;
import com.haoxueren.helper.MyLog;
import com.haoxueren.helper.ToastHelper;

import org.json.JSONArray;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;

@SuppressWarnings("ALL")
public class RetrofitImageActivity extends Activity {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    /**
     * Retrofit Service
     */
    public interface HaoService {
        @GET("data/福利/10/2")
        Call<ResponseBody> getImages();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        ButterKnife.bind(this, getWindow().getDecorView());
        // 获取并设置页面标题
        setTitle(getIntent().getStringExtra(HaoDemo.TITLE));
        // Retrofit请求服务器
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://gank.io/api/").build();
        HaoService service = retrofit.create(HaoService.class);
        Call<ResponseBody> call = service.getImages();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String json = response.body().string();
                    JSONObject jsonObject = new JSONObject(json);
                    boolean error = jsonObject.optBoolean("error");
                    if (error) {
                        ToastHelper.showShortToast("数据解析异常");
                        return;
                    }
                    JSONArray jsonArray = jsonObject.optJSONArray("results");
                    // 设置布局管理器
                    recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                    recyclerView.setAdapter(new ImageAdapter(jsonArray));
                } catch (Exception e) {
                    e.printStackTrace();
                    ToastHelper.showShortToast(e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    /**
     * ViewHolder
     */
    class ImageViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imageView)
        ImageView imageView;

        public ImageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class ImageAdapter extends RecyclerView.Adapter<ImageViewHolder> {

        private JSONArray array;

        public ImageAdapter(JSONArray array) {
            this.array = array;
        }


        @Override
        public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ImageViewHolder(View.inflate(getApplicationContext(), R.layout.layout_image, null));
        }

        @Override
        public void onBindViewHolder(ImageViewHolder holder, int position) {
            JSONObject image = array.optJSONObject(position);
            String url = image.optString("url");
            Glide.with(getApplicationContext()).load(url).into(holder.imageView);
        }

        @Override
        public int getItemCount() {
            return array.length();
        }
    }


}
