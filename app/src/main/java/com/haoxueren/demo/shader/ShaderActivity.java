package com.haoxueren.demo.shader;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

import com.haoxueren.demo.BaseActivity;
import com.haoxueren.demo.R;

import butterknife.BindView;

public class ShaderActivity extends BaseActivity {

    @BindView(R.id.imageView)
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shader);
        setTitle("ShaderActivity");

        linearGradient();

    }

    private void linearGradient() {// 渲染器：Shader
        float x0 = 0;
        float y0 = 0;
        float x1 = 1000;
        float y1 = 0;
        float[] positions = {0.0f, 0.5f, 1.0f};
        int[] colors = {Color.RED, Color.GREEN, Color.MAGENTA};
        Shader sweepGradient = new SweepGradient(200f, 200f, colors, positions);
        Shader.TileMode tileMode = Shader.TileMode.MIRROR;
        Shader radialGradient = new RadialGradient(200f, 200f, 200f, colors, positions, tileMode);
        Shader linearGradient = new LinearGradient(x0, y0, x1, y1, colors, positions, tileMode);

        Bitmap bitmapDog = getBitmapFromRes(R.drawable.cartoon_dog);
        BitmapShader bitmapShader = new BitmapShader(bitmapDog, tileMode, tileMode);
        ComposeShader composeShader = new ComposeShader(linearGradient, bitmapShader, PorterDuff.Mode.ADD);

        // 画布：Bitmap
        int width = 400;
        int height = 400;
//        int width = bitmapDog.getWidth();
//        int height = bitmapDog.getHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        // 画笔：Paint
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(radialGradient);

        // 绘制：Canvas
        RectF rect = new RectF(0, 0, width, height);
        Canvas canvas = new Canvas(bitmap);
//        canvas.drawRect(rect, paint);
        canvas.drawOval(rect, paint);
        // 显示
        imageView.setImageBitmap(bitmap);
    }


    /** 绘制不同TileMode的图像 */
    private void setTileMode(Shader.TileMode tileX, Shader.TileMode tileY) {
        // 从资源文件获取Bitmap对象
        Resources resources = context.getResources();
        Drawable drawable = resources.getDrawable(R.drawable.cartoon_dog);
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        // 用原Bitmap对象的参数创建一个新的Bitmap
        int width = bitmap.getWidth() * 2;
        int height = bitmap.getHeight() * 2;
        Bitmap.Config config = bitmap.getConfig();
        Bitmap newBitmap = Bitmap.createBitmap(width, height, config);
        // 创建BitmapShader并设置给画笔
        Shader shader = new BitmapShader(bitmap, tileX, tileY);
        Paint paint = new Paint();
        paint.setShader(shader);
        // 将图像绘制到bitmapOval上
        RectF rect = new RectF(0, 0, width, height);
        Canvas canvas = new Canvas(newBitmap);
        canvas.drawRect(rect, paint);
        // 显示绘制好的图像
        imageView.setImageBitmap(newBitmap);
    }

    /** 根据原图生成一个椭圆形的Bitmap */
    public Bitmap drawOvalBitmap(Bitmap bitmap) {
        // 根据原Bitmap创建一个新Bitmap
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap.Config config = bitmap.getConfig();
        Bitmap ovalBitmap = Bitmap.createBitmap(width, height, config);
        // 创建BitmapShader
        Shader.TileMode clamp = Shader.TileMode.CLAMP;
        Shader shader = new BitmapShader(bitmap, clamp, clamp);
        // 创建画笔并设置渲染器
        Paint paint = new Paint();
        paint.setShader(shader);
        // 将图像绘制到bitmapOval上
        RectF oval = new RectF(0, 0, width, height);
        Canvas canvas = new Canvas(ovalBitmap);
        canvas.drawOval(oval, paint);
        return ovalBitmap;
    }

    /** 从资源文件中获取Bitmap对象 */
    public Bitmap getBitmapFromRes(int resId) {
        Resources resources = context.getResources();
        BitmapDrawable drawable = (BitmapDrawable) resources.getDrawable(resId);
        return drawable.getBitmap();
    }
}
