package com.haoxueren.demo.retrofit;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.haoxueren.demo.R;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * 演示Retrofit请求API接口
 */
public class RetrofitDemoActivity extends Activity {
    @BindView(R.id.responseTextView)
    TextView responseTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_demo);
        ButterKnife.bind(this);
    }


    public interface MobileService {
        @GET("http://sj.apidata.cn/")
        Call<String> getRegion(@Query("mobile") String mobile);
    }

    @OnClick(R.id.converterButton)
    public void converter() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://sj.apidata.cn/")
                .addConverterFactory(ScalarsConverterFactory.create()).build();
        MobileService mobileService = retrofit.create(MobileService.class);
        Call<String> call = mobileService.getRegion("13634107840");
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String body = response.body();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


    /**
     * 添加一行数据
     */
    public interface BmobService {
        // 添加一行数据
        @Headers({"X-Bmob-Application-Id: b919495a5d000715e89eca5c299d33d2",
                "X-Bmob-REST-API-Key: 418f61fe0e34c61e2a0f7e175ba18d32",
                "Content-Type: application/json"})
        @POST("https://api.bmob.cn/1/classes/{table}")
        Call<ResponseBody> insert(@Path("table") String table, @Body RequestBody body);

        // 获取一行数据
        @Headers({"X-Bmob-Application-Id: b919495a5d000715e89eca5c299d33d2",
                "X-Bmob-REST-API-Key: 418f61fe0e34c61e2a0f7e175ba18d32",})
        @GET("https://api.bmob.cn/1/classes/{table}/{objectId}")
        Call<ResponseBody> query(@Path("table") String table, @Path("objectId") String objectId);
    }

    // GET请求示例代码
    @OnClick(R.id.getButton)
    public void get() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.bmob.cn/").build();
        BmobService bmobService = retrofit.create(BmobService.class);
        Call<ResponseBody> call = bmobService.query("Retrofit", "062e1aefa0");
        enqueueCall(call);
    }

    // POST请求示例代码
    @OnClick(R.id.postButton)
    public void post() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.bmob.cn/").build();
        BmobService bmobService = retrofit.create(BmobService.class);
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        String json = "{\"name\":\"haoxueren\",\"age\":31,\"job\":\"android\"}";
        RequestBody body = RequestBody.create(mediaType, json);
        Call<ResponseBody> call = bmobService.insert("Retrofit", body);
        enqueueCall(call);
    }


    /*************************
     * 以下是封装方法区
     *************************/
    /**
     * 请求服务器并处理返回结果
     */
    private void enqueueCall(Call<ResponseBody> call) {
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    responseTextView.setText(response.body().string());
                } catch (IOException e) {
                    responseTextView.setText(e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                responseTextView.setText(t.getMessage());
            }
        });
    }

}
