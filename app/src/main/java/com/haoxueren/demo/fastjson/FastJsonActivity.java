package com.haoxueren.demo.fastjson;

import android.os.Bundle;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.haoxueren.demo.BaseActivity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Haoxueren on 2017/8/24.
 * FastJson
 */
public class FastJsonActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new View(this));
        Map map = new HashMap();
        map.put("name", "haoxueren");
        map.put("code", "android");
        String json = JSON.toJSONString(map);
        System.out.println(json);
    }
}
