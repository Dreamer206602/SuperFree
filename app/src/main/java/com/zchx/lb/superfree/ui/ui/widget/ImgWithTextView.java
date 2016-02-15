package com.zchx.lb.superfree.ui.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.zchx.lb.superfree.R;


/**
 * Created by
 * Author: wswenyue
 * Email: wswenyue@163.com
 * GitHub: https://github.com/wswenyue
 * Date: 2015/11/12
 */
public class ImgWithTextView extends View {

    //    private static final int DEFAULT_COLOR = 0xFF45C01A;
    private static final int DEFAULT_TEXT_COLOR = 0xff333333;
    private final float DEFAULT_TEXT_SIZE = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics());
    //    private int mColor;
    private Bitmap mIconBitmap;
    private String mText = "你好";//默认的文字
    //设置默认的字体大小12sp
    private int mTextSize;

    //内存中绘图的纯色背景
    private Canvas mCanvas;
    private Bitmap mBitmap;
    private Paint mPaint;

    private float mAlpha = 1.0f;//透明度

    private Rect mIconRect;//Icon的绘制范围
    private Rect mTextBound;//text的绘制范围
    private Paint mTextPaint;


    private boolean InTop = false;
    private boolean InBottom = false;
    private boolean InLeft = false;
    private boolean InRight = false;
    private final int TagTop = 1;
    private final int TagBottom = 2;
    private final int TagLeft = 3;
    private final int TagRight = 4;


    public ImgWithTextView(Context context) {
        this(context, null);
    }

    public ImgWithTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ImgWithTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray array = context.obtainStyledAttributes(attrs,
                R.styleable.ImgWithTextView);

        for (int i = 0; i < array.getIndexCount(); i++) {
            int attr = array.getIndex(i);
            switch (attr) {
                case R.styleable.ImgWithTextView_img:
                    BitmapDrawable drawable = (BitmapDrawable) array.getDrawable(attr);
                    mIconBitmap = drawable.getBitmap();
                    break;
                case R.styleable.ImgWithTextView_top:
                    InTop = array.getBoolean(attr, false);
                    break;
                case R.styleable.ImgWithTextView_bottom:
                    InBottom = array.getBoolean(attr, false);
                    break;
                case R.styleable.ImgWithTextView_left:
                    InLeft = array.getBoolean(attr, false);
                    break;
                case R.styleable.ImgWithTextView_right:
                    InRight = array.getBoolean(attr, false);
                    break;
                case R.styleable.ImgWithTextView_text:
                    mText = array.getString(attr);
                    break;
                case R.styleable.ImgWithTextView_textSize:
                    mTextSize = (int) array.getDimension(attr, DEFAULT_TEXT_SIZE);
                    break;
            }

        }

        array.recycle();//回收

        mTextBound = new Rect();
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);//看锯齿
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setColor(0xff555555);

        mTextPaint.getTextBounds(mText, 0, mText.length(), mTextBound);


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int iconWith = 0;
        int left = 0;
        int top = 0;

        switch (judgeDirection()) {
            case TagTop:
                //计算icon的宽度(取icon的长或宽的最小值),icon定义为正方形
                iconWith = Math.min(getMeasuredWidth() - getPaddingLeft() - getPaddingRight(),
                        getMeasuredHeight() - getPaddingTop() - getPaddingBottom() - mTextBound.height());
                left = getMeasuredWidth() / 2 - iconWith / 2;
                top = getPaddingTop();
                break;
            case TagBottom:
                iconWith = Math.min(getMeasuredWidth() - getPaddingLeft() - getPaddingRight(),
                        getMeasuredHeight() - getPaddingTop() - getPaddingBottom() - mTextBound.height());
                left = getMeasuredWidth() / 2 - iconWith / 2;
                top = (getMeasuredHeight() - mTextBound.height()) / 2 - iconWith / 2;
                break;
            case TagLeft:
                iconWith = Math.min(getMeasuredWidth() - getPaddingLeft() - getPaddingRight() - mTextBound.width(),
                        getMeasuredHeight() - getPaddingTop() - getPaddingBottom());
                left = getPaddingLeft();
                top = getPaddingTop();
                break;
            case TagRight:
                iconWith = Math.min(getMeasuredWidth() - getPaddingLeft() - getPaddingRight() - mTextBound.width(),
                        getMeasuredHeight() - getPaddingTop() - getPaddingBottom());
                left = (getMeasuredWidth() - mTextBound.width()) / 2 - iconWith / 2;
                top = getPaddingTop();
                break;
        }


        //确定绘制范围
        if (iconWith == 0) {
            return;
        }
        mIconRect = new Rect(left, top, left + iconWith, top + iconWith);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(mIconBitmap, null, mIconRect, null);
        int alpha = (int) Math.ceil(255 * mAlpha);
        //内存 准备mBitmap , setAlpha, 纯色，xfermode, 图标
//        setupTargetBitmap(alpha);
        //1、绘制原文本。2、绘制变色文本
        drawSourceText(canvas, alpha);

//        drawTargetText(canvas, alpha);

        //绘制图标
//        canvas.drawBitmap(mBitmap, 0, 0, null);
    }

    /**
     * 绘制变色的文本
     *
     * @param canvas
     * @param alpha
     */
    private void drawTargetText(Canvas canvas, int alpha) {
//        mTextPaint.setColor(mColor);
        mTextPaint.setAlpha(alpha);
        int x = getMeasuredWidth() / 2 - mTextBound.width() / 2;
        int y = mIconRect.bottom + mTextBound.height();
        canvas.drawText(mText, x, y, mTextPaint);
    }

    /**
     * 绘制原文本 TODO 这里需要改进
     *
     * @param canvas
     * @param alpha
     */
    private void drawSourceText(Canvas canvas, int alpha) {
        mTextPaint.setColor(DEFAULT_TEXT_COLOR);
        mTextPaint.setAlpha(255 - alpha);
        int x = 0;
        int y = 0;
        switch (judgeDirection()) {
            case TagTop:
                x = getMeasuredWidth() / 2 - mTextBound.width() / 2;
                y = mIconRect.bottom + mTextBound.height();
                break;
            case TagBottom:
                x = getMeasuredWidth() / 2 - mTextBound.width() / 2;
                y = mIconRect.bottom + mTextBound.height();
                break;
            case TagLeft:
                break;
            case TagRight:
                break;
        }


        canvas.drawText(mText, x, y, mTextPaint);
    }

    /**
     * 在内存中绘制可变色的icon
     */
    private void setupTargetBitmap(int alpha) {
        mBitmap = Bitmap.createBitmap(getMeasuredWidth(),
                getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
//        mPaint.setColor(mColor);
        mPaint.setAntiAlias(true);//抗锯齿
        mPaint.setDither(true);//设定是否使用图像抖动处理，会使绘制出来的图片颜色更加平滑和饱满，图像更加清晰
        mPaint.setAlpha(alpha);
        //绘制纯色
        mCanvas.drawRect(mIconRect, mPaint);
        //设置Xfermode的叠加形式
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        mPaint.setAlpha(255);
        mCanvas.drawBitmap(mIconBitmap, null, mIconRect, mPaint);

    }

    public void setIconAlpha(float alpha) {
        this.mAlpha = alpha;
        invalidateView();
    }

    /**
     * 重绘
     */
    private void invalidateView() {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            invalidate();
        } else {
            //非ui线程调用
            postInvalidate();
        }
    }

    private int judgeDirection() {
        int ret = TagLeft;
        if (InTop) {
            ret = TagTop;
        }
        if (InBottom) {
            ret = TagBottom;
        }
        if (InLeft) {
            ret = TagLeft;
        }
        if (InRight) {
            ret = TagRight;
        }
        return ret;
    }


}
