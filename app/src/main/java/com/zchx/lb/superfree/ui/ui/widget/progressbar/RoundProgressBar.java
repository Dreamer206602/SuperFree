package com.zchx.lb.superfree.ui.ui.widget.progressbar;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.zchx.lb.superfree.R;

public class RoundProgressBar extends View {
    private Paint paint;

    private int roundColor;

    private int roundProgressColor;

    private int textColor;

    private float textSize;

    private float roundWidth;

    private float roundProgressWidth;

    private float max;

    private float progress;
    /**
     * Display text in the middle
     */
    private boolean textIsDisplayable;

    /**
     * Delay Animation
     */
    private ValueAnimator valueAnimator;

    /**
     * Delay Progress
     */
    private float lastProgress;

    private int style;

    public static final int STROKE = 0;
    public static final int FILL = 1;
    public static final int FILL_AND_STROKE = 2;
    public static final int HORIZONTAL = 3;

    public RoundProgressBar(Context context) {
        this(context, null);
    }

    public RoundProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundProgressBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        paint = new Paint();

        valueAnimator = new ValueAnimator();
        valueAnimator.addUpdateListener(new ValueAnimatorListenerImp());


        TypedArray mTypedArray = context.obtainStyledAttributes(attrs,
                R.styleable.RoundProgressBar);

        //获取自定义属性和默认值
        roundColor = mTypedArray.getColor(R.styleable.RoundProgressBar_roundColor, Color.RED);
        roundProgressColor = mTypedArray.getColor(R.styleable.RoundProgressBar_roundProgressColor, Color.GREEN);
        textColor = mTypedArray.getColor(R.styleable.RoundProgressBar_textColor, Color.GREEN);
        textSize = mTypedArray.getDimension(R.styleable.RoundProgressBar_textSize_roundBar, 15);
        roundWidth = mTypedArray.getDimension(R.styleable.RoundProgressBar_roundWidth, 5);
        roundProgressWidth = mTypedArray.getDimension(R.styleable.RoundProgressBar_roundProgressWidth, 5);
        max = mTypedArray.getFloat(R.styleable.RoundProgressBar_max, 100);
        textIsDisplayable = mTypedArray.getBoolean(R.styleable.RoundProgressBar_textIsDisplayable, true);
        style = mTypedArray.getInt(R.styleable.RoundProgressBar_style, 0);


        mTypedArray.recycle();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int centre = getWidth()/2; //获取圆心的x坐标
        int radius = (int) (centre - roundWidth); //圆环的半径
        paint.setAntiAlias(true);  //消除锯齿

        RectF oval = new RectF(centre - radius, centre - radius, centre
                + radius, centre + radius);  //用于定义的圆弧的形状和大小的界限

        switch (style) {
            case STROKE:{
                drawRing(canvas, centre, radius);
                initPaint();
                paint.setStyle(Paint.Style.STROKE);
                canvas.drawArc(oval, 270, 360 * progress / max, false, paint);  //根据进度画圆弧
                break;
            }
            case FILL:{
                paint.setStyle(Paint.Style.FILL);
                initPaint();
                if(progress !=0) {
                    canvas.drawArc(oval, 270, 360 * progress / max, true, paint);
                    drawRing(canvas, centre, radius);
                }


                break;
            }
            case FILL_AND_STROKE:{
                drawRing(canvas, centre, radius);
                initPaint();
                paint.setStyle(Paint.Style.FILL_AND_STROKE);
                if(progress !=0)
                    canvas.drawArc(oval, 270, 360 * progress / max, true, paint);
                break;
            }
            case HORIZONTAL:{
                if(progress !=0) {

                    float yHeight = (progress / max) * 2 * radius;
                    float angle = (float) (Math.acos((radius - yHeight) / radius) * 180 / Math.PI);
                    paint.setStyle(Paint.Style.FILL);
                    initPaint();
                    canvas.drawArc(oval, 90 - angle, angle * 2, false, paint);

                }
                drawRing(canvas, centre, radius);
                break;
            }
        }

        /**
         * 画进度百分比
         */
        if(textIsDisplayable){
            paint.setStrokeWidth(0);
            paint.setColor(textColor);
            paint.setTextSize(textSize);
            paint.setTypeface(Typeface.DEFAULT_BOLD); //设置字体
            int percent = (int)(((float)progress / (float)max) * 100);  //中间的进度百分比，先转换成float在进行除法运算，不然都为0
            float textWidth = paint.measureText(percent + "%");   //测量字体宽度，我们需要根据字体的宽度设置在圆环中间
            if(percent>=100){
                canvas.drawText("已投满", centre - textWidth /1.5f, centre + textSize/2, paint); //画出进度百分比
            }else{
                canvas.drawText(percent + "%", centre - textWidth / 2, centre + textSize/2, paint); //画出进度百分比
            }




        }

    }

    public synchronized float getMax() {
        return max;
    }

    /**
     * 设置进度的最大值
     * @param max
     */
    public synchronized void setMax(int max) {
        if(max < 0){
            max = 100;
            Log.d("RoundProgressBar", "max is not less than 0");
        }
        this.max = max;
    }

    /**
     * 绘制外圆环
     */
    public void drawRing(Canvas canvas, float centre, float radius){
        /**
         * 画最外层的大圆环
         */
        paint.setColor(roundColor); //设置圆环的颜色
        paint.setStyle(Paint.Style.STROKE); //设置空心
        paint.setStrokeWidth(roundWidth); //设置圆环的宽度
        canvas.drawCircle(centre, centre, radius, paint); //画出圆环
    }

    public void initPaint(){
        paint.setStrokeWidth(roundProgressWidth); //设置圆环的宽度
        paint.setColor(roundProgressColor);  //设置进度的颜色
    }

    /**
     * 获取进度.需要同步
     * @return
     */
    public synchronized float getProgress() {
        return progress;
    }

    /**
     * 此为线程安全控件
     * @param progress
     */
    public synchronized void setProgress(float progress) {
        if(progress < 0){
            throw new IllegalArgumentException("progress not less than 0");
        }
        if(progress > max){
            progress = progress % max;
        }
        if(progress <= max){
            if (valueAnimator != null && this.progress != progress) {
                valueAnimator.setFloatValues(lastProgress, progress);
                valueAnimator.setDuration(500);
                valueAnimator.start();
            }
        }

    }


    class ValueAnimatorListenerImp implements ValueAnimator.AnimatorUpdateListener{

        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            float value = (float) valueAnimator.getAnimatedValue();

            progress = value;
            lastProgress = value;
            postInvalidate();
        }
    }

    private static final String INSTANCE_STATE = "instance_state";
    private static final String ROUND_COLOR = "round_color";
    private static final String ROUND_PROGRESS_COLOR = "round_progress_color";
    private static final String TEXT_COLOR = "text_color";
    private static final String TEXT_SIZE = "text_size";
    private static final String ROUND_WIDTH = "round_width";
    private static final String ROUND_PROGRESS_WIDTH = "round_progress_width";
    private static final String MAX = "max";
    private static final String PROGRESS = "progress";
    private static final String TEXT_IS_DISPLAYABLE = "text_is_displayable";
    private static final String STYLE = "style";

    @Override
    protected Parcelable onSaveInstanceState() {
        final Bundle bundle = new Bundle();
        bundle.putParcelable(INSTANCE_STATE, super.onSaveInstanceState());
        bundle.putInt(ROUND_COLOR, roundColor);
        bundle.putInt(ROUND_PROGRESS_COLOR, roundProgressColor);
        bundle.putInt(TEXT_COLOR, textColor);
        bundle.putFloat(TEXT_SIZE, textSize);
        bundle.putFloat(ROUND_WIDTH, roundWidth);
        bundle.putFloat(ROUND_PROGRESS_WIDTH, roundProgressWidth);
        bundle.putFloat(MAX, max);
        bundle.putFloat(PROGRESS, progress);
        bundle.putBoolean(TEXT_IS_DISPLAYABLE, textIsDisplayable);
        bundle.putInt(STYLE, style);

        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if(state instanceof Bundle) {
            final Bundle bundle = (Bundle) state;
            roundColor = bundle.getInt(ROUND_COLOR);
            roundProgressColor = bundle.getInt(ROUND_PROGRESS_COLOR);
            textColor = bundle.getInt(TEXT_COLOR);
            textSize = bundle.getInt(TEXT_SIZE);
            roundWidth = bundle.getFloat(ROUND_WIDTH);
            roundProgressWidth = bundle.getFloat(ROUND_PROGRESS_WIDTH);
            progress = bundle.getFloat(PROGRESS);
            max = bundle.getFloat(MAX);
            textIsDisplayable = bundle.getBoolean(TEXT_IS_DISPLAYABLE);
            style = bundle.getInt(STYLE);
            super.onRestoreInstanceState(bundle.getParcelable(INSTANCE_STATE));
            return;
        }
        super.onRestoreInstanceState(state);
    }

}
