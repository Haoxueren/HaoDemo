package com.haoxueren.demo.andjump;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.haoxueren.demo.R;
import com.haoxueren.helper.ToastHelper;
import com.qtinject.andjump.api.QtInject;

import butterknife.BindView;
import butterknife.ButterKnife;

@QtInject
public class TargetActivity extends Activity {

    @QtInject
    String name;

    @BindView(R.id.nameTextView)
    TextView nameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target);
        ButterKnife.bind(this);
        QtTargetActivity.inject(this);
        nameTextView.setText(name);
    }
}
