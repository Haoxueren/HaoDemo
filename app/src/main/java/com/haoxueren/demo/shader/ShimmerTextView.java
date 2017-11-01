package com.haoxueren.demo.shader;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 自定义会闪动的文字
 */
public class ShimmerTextView extends TextView {

    public ShimmerTextView(Context context) {
        this(context, null);
    }

    public ShimmerTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShimmerTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
