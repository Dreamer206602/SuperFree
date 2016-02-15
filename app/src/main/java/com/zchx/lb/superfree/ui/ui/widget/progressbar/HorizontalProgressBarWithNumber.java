package com.zchx.lb.superfree.ui.ui.widget.progressbar;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ProgressBar;

import com.zchx.lb.superfree.R;


/**
 * Created on 2015/12/7 17:21
 * Created by Author boobooL
 * 邮箱：boobooMX@163.com
 */

/**
 * 自定义水平进度条
 */
public class HorizontalProgressBarWithNumber extends ProgressBar {
    private static final int DEFAULT_TEXT_SIZE=10;
    private static final int DEFAULT_TEXT_COLOR=0XFFFC00D1;
    private static final int DEFAULT_COLOR_UNREACHED_COLOR=0xFFd3d6da;
    public static final int DEFAULT_HEIGHT_REACHED_PROGRESS_BAR=2;
    public static final int DEFAULT_HEIGHT_UNREACHED_PROGRESS_BAR=2;
    public static final int DEFAULT_SIZE_TEXT_OFFSET=10;
    protected Paint mPaint=new Paint();
    protected  int mTextColor=DEFAULT_TEXT_COLOR;

    /**
     * size of text(sp)
     */
    protected  int mTextSize=sp2px(DEFAULT_TEXT_SIZE);

    /**
     * offset of draw progress
     */
   protected int mTextOffset=dp2px(DEFAULT_SIZE_TEXT_OFFSET);

    /**
     * color of reached bar
     */
    protected int mReachedBarColor=DEFAULT_TEXT_COLOR;

    /**
     * color of unreached bar
     */
    protected int mUnReachedBarColor=DEFAULT_COLOR_UNREACHED_COLOR;

    /**
     * height of reached progress bar
     */
    protected int mReachedProgressBarHeight=dp2px(DEFAULT_HEIGHT_REACHED_PROGRESS_BAR);

    /**
     * height of unreached progress bar
     */
    protected int mUnreachedProgressBarHeight=dp2px(DEFAULT_HEIGHT_UNREACHED_PROGRESS_BAR);

    /**
     * view of except padding
     */
    protected  int mRealWidth;

    protected boolean mIfDrawText=true;
    public static final int VISIBLE=0;




    public HorizontalProgressBarWithNumber(Context context) {
        this(context,null);
    }
    public HorizontalProgressBarWithNumber(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public HorizontalProgressBarWithNumber(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setHorizontalScrollBarEnabled(true);
        obtainStyledAttributes(attrs);
        mPaint.setTextSize(mTextSize);
        mPaint.setColor(mTextColor);
    }

    /**
     * get the styled attributes
     * @param attrs
     */
    private void obtainStyledAttributes(AttributeSet attrs) {
        //init values from attributes
        final TypedArray attributes=getContext().obtainStyledAttributes(attrs,
             R.styleable.HorizontalProgressBarWidthNumber);
        mTextColor=attributes.getColor(R.styleable.HorizontalProgressBarWidthNumber_progress_text_color,
                DEFAULT_TEXT_COLOR);

        mTextSize=(int)attributes.getDimension(R.styleable.HorizontalProgressBarWidthNumber_progress_text_size,
                mTextSize);

        //进度条所到达的颜色
        mReachedBarColor=attributes.getColor(R.styleable.HorizontalProgressBarWidthNumber_progress_reached_color,
                getColor(R.color.def_pointer_color));

        //进度条未到达的颜色
        mUnReachedBarColor = attributes.getColor(R.styleable.HorizontalProgressBarWidthNumber_progress_unreached_bar_height,
                getColor(R.color.def_wheel_color));

        mReachedProgressBarHeight= (int) attributes.getDimension(
                R.styleable.HorizontalProgressBarWidthNumber_progress_reached_bar_height,
                mReachedProgressBarHeight);
        mUnreachedProgressBarHeight= (int) attributes.getDimension(
                R.styleable.HorizontalProgressBarWidthNumber_progress_unreached_bar_height,
                mUnreachedProgressBarHeight);
        mTextOffset= (int) attributes.getDimension(
                R.styleable.HorizontalProgressBarWidthNumber_progress_text_offset
        ,mTextOffset);

        int textVisible=attributes.getInt(
                R.styleable.HorizontalProgressBarWidthNumber_progress_text_visibility,
                VISIBLE);
        if(textVisible!=VISIBLE){
            mIfDrawText=false;
        }
        attributes.recycle();
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int heightMode=MeasureSpec.getMode(heightMeasureSpec);
        if(heightMode!=MeasureSpec.EXACTLY){
            float textHeight=(mPaint.descent()+mPaint.ascent());
            int exceptHeight=(int)(getPaddingTop()+getPaddingBottom()+Math.max(Math.max(mReachedProgressBarHeight,
                    mUnReachedBarColor),Math.abs(textHeight)));
            heightMeasureSpec=MeasureSpec.makeMeasureSpec(exceptHeight,MeasureSpec.EXACTLY);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        canvas.save();
        //画笔平移到指定的paddingLeft,getHeight()/2位置，注意以后的坐标都为0,0
        canvas.translate(getPaddingLeft(),getHeight()/2);

        boolean noNeedBg=false;
        //当前进度和总值的比例
        float radio=getProgress()*1.0f/getMax();
        //已经到达的宽度
        float progressPosX=(int)(mRealWidth*radio);
        //绘制的文本
        String text=getProgress()+"%";

        //拿到字体的宽度和高度
        float textWidth=mPaint.measureText(text);
        float textHeight=(mPaint.descent()+mPaint.ascent())/2;

        //如果到达最后，则未到达的进度条不需要绘制
        if(progressPosX+textWidth>mRealWidth){
            progressPosX=mRealWidth-textWidth;
            noNeedBg=true;
        }

        //绘制已经到达的进度
        //float endX=progressPosX-mTextOffset/2;
        float endX=progressPosX;
        if(endX>0){
            mPaint.setColor(mReachedBarColor);
            mPaint.setStrokeWidth(mReachedProgressBarHeight);
            canvas.drawLine(0,0,endX,0,mPaint);
        }

        //暂时不需要设置文字
//        //绘制文本
//        if(mIfDrawText){
//            mPaint.setColor(mTextColor);
//            canvas.drawText(text,progressPosX,-textHeight,mPaint);
//
//        }

        //绘制未到达的进度条
        if(!noNeedBg){
            //float start=progressPosX+mTextOffset/2+textWidth;
            float start=progressPosX;
            mPaint.setColor(mUnReachedBarColor);
            mPaint.setStrokeWidth(mUnreachedProgressBarHeight);
            canvas.drawLine(start,0,mRealWidth,0,mPaint);
        }

        canvas.restore();

    }

    /**
     * dp 2 px
     * @param dpVal
     * @return
     */
    protected int dp2px(int dpVal){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dpVal,getResources().getDisplayMetrics());
    }

    protected int sp2px(int spVal){
        return (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,spVal,getResources().getDisplayMetrics());
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mRealWidth=w-getPaddingRight()-getPaddingLeft();
    }

    //获取xml下的定义的颜色
    @TargetApi(Build.VERSION_CODES.M)
    private int getColor(int colorId) {
        final int version = Build.VERSION.SDK_INT;
        if (version >= 23) {
            return getContext().getColor(colorId);
        } else {
            return getResources().getColor(colorId);
        }
    }
}
