package com.intretech.customview.view.seekbar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.core.content.ContextCompat;

import com.intretech.customview.R;


/**
 * 灯刻度的进度条
 *
 * @author YQ16685 Chanreylee
 * @date 2/3/21 7:16 PM
 */
public abstract class BaseLightSeekBar extends View {
    protected final String TAG = getClass().getSimpleName();


    protected int mMeasuredHeight;
    protected int mMeasuredWidth;

    protected int mLines;
    protected float mLinesInterval;

    protected Paint mPaint, mProgressPaint, mGradientPaint;
    /**
     * 进度线的厚度
     */
    protected int mProgressLineWidth;
    protected float mPadding, mProgressPadding;


    /**
     * 刻度的宽度
     */
    protected double mScaleWidth;
    protected float mStartX;
    protected float mTouchY;
    private float mDownY;
    protected int mCurrentProgress = 0;

    protected int mCurrentEvent;

    /**
     * 只有滑动一定距离才开始绘制，不然按下去就会开始绘制
     */
    boolean isMove;

    /**
     * 当用户按下去之后 进度不能再因外部设值而改变
     * 还有 进度的回调如果不是用户按下去则不回调进度：表现在代码设置进度的时候不能执行回调
     */
    protected boolean isFromUser;

    /**
     * 是否初始化过了 宽高问题
     */
    protected boolean isInitWH;
    /**
     * 每个进度的像素梯度
     */
    protected float mProgressInterval;

    /**
     * 按压在控制条一定范围内，才判断为控制
     */
    protected float mDragControlRegion;

    /**
     * 是否可点击，设备离线的时候不能点击控制设备,默认可点击
     */
    protected boolean mClickEnable = true;

    /**
     * 是否显示刻度，默认显示
     */
    protected boolean mBgScaleVisibility = true;

    /**
     * 当前标尺的位置的X轴起始点
     */
    protected float mProgressStartX;

    /**
     * 渐变色开始
     */
    protected int gradientColorStart;
    /**
     * 渐变色结束
     */
    protected int gradientColorEnd;
    /**
     * 圆角矩形的圆角半径
     */
    protected float rXY;

    public void setBgScaleVisibility(boolean mBgScaleVisibility) {
        this.mBgScaleVisibility = mBgScaleVisibility;
    }

    public BaseLightSeekBar(Context context) {
        super(context);
    }

    public BaseLightSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        gradientColorStart = ContextCompat.getColor(context, R.color.light_brightness_gradient_start);
        gradientColorEnd =  ContextCompat.getColor(context, R.color.light_brightness_gradient_end);
        rXY = context.getResources().getDimension(R.dimen.space_30);
    }

    protected synchronized void init() {

        isInitWH = true;

        float mDensity = getResources().getDisplayMetrics().density;
        mMeasuredHeight = getMeasuredHeight() - (int) (8 * mDensity);
        mMeasuredWidth = getMeasuredWidth();

        //背景刻度，刻度线的厚度
        //线的厚度
        int lineWidth = (int) (3 * mDensity);
        //线中心与线边缘的距离（第一个刻度的时候需要加上这个padding）
        mPadding = (float) lineWidth / (float) 2;

        //当前刻度，刻度下的厚度
        mProgressLineWidth = (int) (5 * mDensity);
        mDragControlRegion = 5 * mDensity;

        mProgressPadding = (float) mProgressLineWidth / (float) 2;


        mProgressInterval = ((float) mMeasuredHeight - (float) mProgressLineWidth) / (float) 100;


        mScaleWidth = mMeasuredWidth * 0.25;

        //刻度的起点
        mStartX = (float) ((mMeasuredWidth - mScaleWidth) / 2);
        mProgressStartX = (float) (mMeasuredWidth * 0.64 /2);

        //线之间的间隔 （10 表示空白间隔，空白间隔加上线厚度 等于每个刻度的间隔）
        float tempInterval = 10 * mDensity + lineWidth;
        //计算出线条数 大概的线条数(为什么要加1 ： 有的时候刚好最后一条线差一点点，这样会有一条线的空格，撑不满 。所以+1条线)
        mLines = (int) ((mMeasuredHeight - lineWidth) / tempInterval) + 1;
        //重新算出刻度线的间隔
        mLinesInterval = (mMeasuredHeight - lineWidth) / (float) mLines;


        //刻度画笔
        mPaint = new Paint();
        mPaint.setStrokeWidth(lineWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setColor(Color.WHITE);

        //当前进度的画笔
        mProgressPaint = new Paint();
        mProgressPaint.setStrokeWidth(mProgressLineWidth);
        mProgressPaint.setStrokeCap(Paint.Cap.ROUND);
        mProgressPaint.setColor(Color.WHITE);

        //渐变背景画笔
        mGradientPaint = new Paint();
        mGradientPaint.setStyle(Paint.Style.FILL);
        mProgressPaint.setStrokeCap(Paint.Cap.SQUARE);
        mGradientPaint.setStrokeWidth(getMeasuredWidth());


        //首次初始化
        mTouchY = mCurrentProgress * mProgressInterval + mProgressPadding;
        isMaxOrMin();

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (!isInitWH) {
            init();
        }
    }

    /**
     * 绘制刻度
     */
    protected void drawCalibration(Canvas canvas) {
        if (mBgScaleVisibility) {
            for (int i = 1; i < mLines; i++) {
                float startY = (float) i * mLinesInterval + mPadding;
                canvas.drawLine(mStartX, startY, (float) (mStartX + mScaleWidth), startY, mPaint);
            }
        }
    }

    /**
     * 画进度
     */
    protected void drawCurrentCalibration(Canvas canvas) {
        if (mTouchY != 0) {
            canvas.drawLine(mProgressStartX, mTouchY, mMeasuredWidth - mProgressStartX, mTouchY, mProgressPaint);
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {


        mCurrentEvent = event.getAction();


        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                //触摸点按压在当前拖动条位置才能拖动
                float abs = Math.abs(event.getY() - mTouchY);
                Log.e(TAG, "------------------------------abs: " + abs);
                Log.e(TAG, "------------------------------mDragControlRegion: " + mDragControlRegion);
                if (abs < mDragControlRegion) {
                    isMove = true;
                } else {
                    isMove = false;
                }

                mDownY = event.getY();
                isFromUser = true;

                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:

                isFromUser = false;

                //点击
                boolean isClick = false;
                float moveAbs = Math.abs(event.getY() - mDownY);
                if (moveAbs < mProgressInterval) {
                    isClick = true;
                }
                if (isClick) {

                    Log.e(TAG, "------------------------isClick-------------");

                    //点击在当前刻度上无效
                    boolean isTouchCurrentProgress = false;
                    float currentProgress = Math.abs(event.getY() - mTouchY);
                    if (currentProgress < mProgressInterval) {
                        isTouchCurrentProgress = true;
                    }

                    //如果是点击 而且 点击的地方不在当前进度位置，则
                    if (!isTouchCurrentProgress) {
                        checkClickable();
                        Log.e(TAG, "------------------------mClickEnable-------------");
                        if (mClickEnable) {
                            //点下去跟抬起在一定范围内 判断为点击
                            calculateTouchY(event);
                            isMaxOrMin();
                            invalidate();
                            progressCallBack();
                        }

                    }
                } else if (isMove) {
                    Log.e(TAG , "------------------------isMove-------------");
                    //拖动
                    calculateTouchY(event);
                    isMaxOrMin();

                    invalidate();
                    isMove = false;
                    progressCallBack();
                }


                break;
            case MotionEvent.ACTION_MOVE:

                boolean isMoving = Math.abs(event.getY() - mDownY) > mProgressInterval;
                if (isMove && isMoving) {
                    Log.e(TAG, "------------------------ACTION_MOVE-------------");
                    calculateTouchY(event);
                    isMaxOrMin();
                    invalidate();
                    progressCallBack();
                }
                break;
            default:
        }
        return true;
    }


    /**
     * 圆角矩形 路径绘制
     * @param radius 半径
     * @param topLeft 左上
     * @param topRight 右上
     * @param bottomRight 右下
     * @param bottomLeft 左下
     * @return Path
     */
    protected float[] getRadius(float radius, boolean topLeft, boolean topRight, boolean bottomRight, boolean bottomLeft) {

        final float[] radii = new float[8];

        if (topLeft) {
            radii[0] = radius;
            radii[1] = radius;
        }

        if (topRight) {
            radii[2] = radius;
            radii[3] = radius;
        }

        if (bottomRight) {
            radii[4] = radius;
            radii[5] = radius;
        }

        if (bottomLeft) {
            radii[6] = radius;
            radii[7] = radius;
        }

        return radii;
    }

    abstract void progressCallBack();

    abstract void calculateTouchY(MotionEvent event);

    abstract void checkClickable();

    /**
     * 大小边缘限制
     *
     * @author chenguowu
     * @date 2/22/21 1:26 PM
     */
    protected void isMaxOrMin() {
        if (mTouchY < mProgressPadding) {
            mTouchY = mProgressPadding;
        } else if (mTouchY > mMeasuredHeight - mProgressPadding) {
            mTouchY = mMeasuredHeight - mProgressPadding;
        }
    }


    public boolean isFromUser() {
        return isFromUser;
    }
}


