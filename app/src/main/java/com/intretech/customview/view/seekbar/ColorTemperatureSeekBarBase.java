package com.intretech.customview.view.seekbar;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import androidx.core.content.ContextCompat;

import com.intretech.customview.R;


/**
 * 色温拖动条
 *
 * @author: YQ16685 Chanrey Lee
 * @date 2022/4/19 - 13:09
 **/
public class ColorTemperatureSeekBarBase extends BaseLightSeekBar {
    private String TAG = this.getClass().getSimpleName();

    private static final int AMPLITUDE = 50;  //调节幅度50K
    /**
     * 默认色温最高最低值
     */
    private int mMaxProgress = 6500;
    private int mMinProgress = 2700;

    private OnColorTemperatureListener mListener;

    /**
     * 色温刻度一共只有76个，与常规的100刻度有区别
     */
    private int mMaxPercent = (mMaxProgress - mMinProgress) / AMPLITUDE;

    private int mCurrentColorTemperature;
    private float mProgressInterval;


    public ColorTemperatureSeekBarBase(Context context) {
        super(context);
    }

    public ColorTemperatureSeekBarBase(Context context, AttributeSet attrs) {
        super(context, attrs);
        gradientColorStart = ContextCompat.getColor(context, R.color.light_color_temperature_gradient_start);
        gradientColorEnd =  ContextCompat.getColor(context, R.color.light_color_temperature_gradient_end);
    }

    @Override
    protected synchronized void init() {
        super.init();
        mProgressInterval = ((float) mMeasuredHeight - (float) mProgressLineWidth) / ((float) (mMaxProgress - mMinProgress) / (float) 50);


        //首次初始化
        //占比
        int tempPercent = mCurrentColorTemperature * mMaxPercent / getCurrentColorTemperatureRegion();

        mTouchY = (mMaxPercent - tempPercent) * mProgressInterval + mProgressPadding;

        isMaxOrMin();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!isInitWH) {
            init();
        }

        drawCalibration(canvas);

        drawCurrentCalibration(canvas);
    }

    @Override
    protected void progressCallBack() {
        if (null != mListener) {

            if (mTouchY <= mProgressPadding) {
                mListener.onColorTemperature(mMaxProgress, mCurrentEvent);
            } else if (mTouchY >= mMeasuredHeight - mProgressPadding) {
                mListener.onColorTemperature(mMinProgress, mCurrentEvent);
            } else {
                mListener.onColorTemperature(mCurrentColorTemperature + mMinProgress, mCurrentEvent);
            }
        }
    }

    @Override
    protected void calculateTouchY(MotionEvent event) {

        //先算出占比
        int currentProgress = (int) ((event.getY() - mProgressPadding) / mProgressInterval);
        //根据占比算出色温 （一个占比 50色温）
        mCurrentColorTemperature = getCurrentColorTemperatureRegion() - currentProgress * AMPLITUDE;
        //根据占比算出touchY （一个占比 有固定的像素占比）
        mTouchY = currentProgress * mProgressInterval + mProgressPadding;
    }


    /**
     * 传进来的参数要向下50取整
     *
     * @author chenguowu
     * @date 3/10/21 2:20 PM
     */
    public void setColorTemperature(int colorTemperature) {
        Log.e(TAG,"-----------------------setColorTemperature ;" + colorTemperature);
        if (colorTemperature < mMinProgress) {
            colorTemperature = mMinProgress;
        } else if (colorTemperature >= mMaxProgress) {
            colorTemperature = mMaxProgress;
        }

        int finalColorTemperature = colorTemperature;
        if (!isFromUser) {

            //50 为单位 向下取整。 6500 -2700 = 3800
            int tempZ = (finalColorTemperature - mMinProgress) / 50 * 50;
            Log.e(TAG,"--------------------tempZ: " + tempZ);
            mCurrentColorTemperature = tempZ;

            //占比
            int tempPercent = mCurrentColorTemperature * mMaxPercent / getCurrentColorTemperatureRegion();

            mTouchY = (mMaxPercent - tempPercent) * mProgressInterval + mProgressPadding;
            Log.e(TAG,"---------------------------setProgress-------currentColorTemperature: " + mCurrentColorTemperature);
            Log.e(TAG,"---------------------------setProgress-------touchY: " + mTouchY);

            isMaxOrMin();
            invalidate();

        }
    }

    @Override
    void checkClickable() {
        //点击时候获取一下是否可点击（设备离线的时候 不可点击）
        if (null != mListener) {
            mClickEnable = mListener.enableClick();
        }
    }

    /**
     * 获取色温区间值
     *
     * @author chenguowu
     * @date 4/12/21 4:29 PM
     */
    private int getCurrentColorTemperatureRegion() {
        return mMaxProgress - mMinProgress;
    }

    public boolean isFromUser() {
        return isFromUser;
    }

    public void setOnProgressListener(OnColorTemperatureListener listener) {
        this.mListener = listener;
    }

    public interface OnColorTemperatureListener {
        void onColorTemperature(int colorTemperature, int currentEventAction);
        boolean enableClick();
    }
}
