package com.haoxueren.demo.andjump;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.haoxueren.demo.R;
import com.qtinject.andjump.api.QtInject;

@QtInject
public class AndJumpActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_andjump);
    }

    public void andJump(View view) {
        QtTargetActivity.getInstance().setName("Haoxueren").requestCode(0).start(this);
    }
}
