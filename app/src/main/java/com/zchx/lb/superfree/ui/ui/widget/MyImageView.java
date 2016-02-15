package com.zchx.lb.superfree.ui.ui.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

/**
 * Created
 * Author:meluo
 * Email:kongmuo@126.com
 * Date:15-10-21
 */
public class MyImageView extends ImageView {


    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        setScaleType(ScaleType.CENTER_INSIDE);
        Drawable drawable=getDrawable();
        if(drawable==null){
            return;
        }
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        Bitmap bitmap=Bitmap.createBitmap(getMeasuredWidth(),getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas mycanvas=new Canvas(bitmap);
        drawable.draw(mycanvas);
        float cx = getWidth() / 2;
        float cy = getHeight() / 2;

        float radius = Math.min(getWidth(), getHeight()) / 2;

        Paint borderPaint = new Paint();
        borderPaint.setAntiAlias(true);
        borderPaint.setColor(Color.GREEN);

        canvas.drawCircle(cx, cy, radius, borderPaint);

        // 画图
        BitmapShader shader = new BitmapShader(bitmap, Shader.TileMode.MIRROR,
                Shader.TileMode.MIRROR);
        Paint paint = new Paint();
        paint.setShader(shader);
        paint.setAntiAlias(true);
        canvas.drawCircle(cx, cy, radius, paint);

    }
}
