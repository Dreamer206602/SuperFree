package com.zchx.lb.superfree.ui.ui.widget.circleImage;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.zchx.lb.superfree.R;


/**
 * Created by Administrator on 2015/11/10.
 */
public class CircleImage extends ImageView {
    private Context context;
    private int borderwidth = 0;
    private int bordercolor = Color.GRAY;
    private int defaultwindth = 0;
    private int defaultheight = 0;

    private int defaultColor = 0xFFFFFFFF;

    public CircleImage(Context context) {
        super(context);
        this.context = context;
    }

    public CircleImage(Context context, AttributeSet attrs) {
        super(context, attrs);
        setAttributeSet(attrs);
        this.context = context;
    }

    public CircleImage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        setAttributeSet(attrs);
    }

    private void setAttributeSet(AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleImage);
        borderwidth = typedArray.getDimensionPixelSize(R.styleable.CircleImage_borderwindth, 0);
        bordercolor = typedArray.getColor(R.styleable.CircleImage_bordercolor, Color.GRAY);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return;
        }
        if (getWidth() == 0 && getHeight() == 0) {
            return;
        }
        this.measure(0, 0);
        if (drawable.getClass() == NinePatchDrawable.class) {
            return;
        }
        Bitmap b = ((BitmapDrawable) drawable).getBitmap();
        Bitmap bitmap = b.copy(Bitmap.Config.ARGB_8888, true);
        if (defaultheight == 0) {
            defaultheight = getHeight();
        }
        if (defaultwindth == 0) {
            defaultwindth = getWidth();
        }
        int radius = 0;
        if (bordercolor != defaultColor) {
            radius = (defaultwindth < defaultheight ? defaultwindth : defaultheight) / 2 - 2 * borderwidth;
            drawClicleBorder(canvas, radius + borderwidth, bordercolor);
            drawClicleBorder(canvas,radius, bordercolor);
        }else if (bordercolor != defaultColor && bordercolor == defaultColor) {// 定义画一个边框 
            radius = (defaultwindth < defaultheight ? defaultwindth : defaultheight) / 2 - borderwidth;
         drawClicleBorder(canvas, radius,bordercolor);
          }

        Bitmap roundBitmap = getCroppedRoundBitmap(bitmap, radius);
        canvas.drawBitmap(roundBitmap, defaultwindth / 2 - radius, defaultheight / 2 - radius, null);


    }

    public Bitmap getCroppedRoundBitmap(Bitmap bmp, int radius) {
        Bitmap scaledSrcBmp;
        int diameter = radius * 2;
        // 为了防止宽高不相等，造成圆形图片变形，因此截取长方形中处于中间位置最大的正方形图片 
        int bmpWidth = bmp.getWidth();
        int bmpHeight = bmp.getHeight();
        int squareWidth = 0, squareHeight = 0;
        int x = 0, y = 0;
        Bitmap squareBitmap;
        if (bmpHeight > bmpWidth) {// 高大于宽 
            squareWidth = squareHeight = bmpWidth;
            x = 0;
            y = (bmpHeight - bmpWidth) / 2;
            // 截取正方形图片 
            squareBitmap = Bitmap.createBitmap(bmp, x, y, squareWidth, squareHeight);
        } else if (bmpHeight < bmpWidth) {// 宽大于高 
            squareWidth = squareHeight = bmpHeight;
            x = (bmpWidth - bmpHeight) / 2;
            y = 0;
            squareBitmap = Bitmap.createBitmap(bmp, x, y, squareWidth, squareHeight);
        } else {
            squareBitmap = bmp;
        }
        if (squareBitmap.getWidth() != diameter || squareBitmap.getHeight() != diameter) {

            scaledSrcBmp = Bitmap.createScaledBitmap(squareBitmap, diameter, diameter, true);
        } else {
            scaledSrcBmp = squareBitmap;
        }
        Bitmap output = Bitmap.createBitmap(scaledSrcBmp.getWidth(),
                scaledSrcBmp.getHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, scaledSrcBmp.getWidth(), scaledSrcBmp.getHeight());

        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawCircle(scaledSrcBmp.getWidth() / 2,
                scaledSrcBmp.getHeight() / 2,
                scaledSrcBmp.getWidth() / 2,
                paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(scaledSrcBmp, rect, rect, paint);
        bmp = null;
        squareBitmap = null;
        scaledSrcBmp = null;
        return output;
    }

    public void drawClicleBorder(Canvas canvas, int radius, int color) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(color);
        paint.setStrokeWidth(borderwidth);
        canvas.drawCircle(defaultwindth / 2, defaultheight / 2, radius, paint);
    }
}
