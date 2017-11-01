package com.haoxueren.demo.shader;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

/**
 * 绘制一闪一闪的多彩文字；<br>
 * 参考资料：<br>
 * http://wuxiaolong.me/2015/11/16/LinearGradientTextView/<br>
 * https://github.com/hanks-zyh/HTextView<br>
 */
public class FlashTextView extends View {
    private Paint paint;
    private Matrix matrix;
    private LinearGradient linearGradient;
    private String text = "对生活充满热情，对未来充满信心！";

    public FlashTextView(Context context) {
        this(context, null);
    }

    public FlashTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlashTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // 测量文字的宽度；
        paint = new Paint();
        paint.setTextSize(60);
        float textWidth = paint.measureText(text);
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        System.out.println(fontMetrics.leading);
        System.out.println(fontMetrics.ascent);
        System.out.println(fontMetrics.descent);
        // 创建画笔渲染器；
        int[] colors = {Color.RED, Color.GREEN, Color.MAGENTA, Color.BLUE};
        float[] positions = {0.0f, 0.33f, 0.66f, 1.0f};
        linearGradient = new LinearGradient(0, 0, textWidth, 0, colors, positions, Shader.TileMode.MIRROR);
        // 关联画笔与渲染器；
        paint.setShader(linearGradient);
        matrix = new Matrix();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawText(text, 0, 50, paint);
        // 使用矩阵平移渲染器；
        matrix.postTranslate(100, 0);
        linearGradient.setLocalMatrix(matrix);
        // 重绘界面；
        postInvalidateDelayed(100);
    }
}