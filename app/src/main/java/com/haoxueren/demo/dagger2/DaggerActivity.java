package com.haoxueren.demo.dagger2;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.haoxueren.demo.R;

import javax.inject.Inject;

public class DaggerActivity extends Activity {

    @Inject
    Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dragger);
        DaggerPersonComponent.Builder builder = DaggerPersonComponent.builder();
        PersonComponent component = builder.personModule(new PersonModule("Haosir")).build();
        component.inject(this);
        System.out.println("注入成功：" + person.name);
    }
}
