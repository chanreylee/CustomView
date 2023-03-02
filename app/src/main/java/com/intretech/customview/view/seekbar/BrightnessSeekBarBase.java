package com.intretech.customview.view.seekbar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import androidx.core.content.ContextCompat;

import com.intretech.customview.R;


/**
 * 灯刻度的进度条
 *
 * @author YQ16685 Chanreylee
 * @date 2/3/21 7:16 PM
 */
public class BrightnessSeekBarBase extends BaseLightSeekBar {
    protected final String TAG = getClass().getSimpleName();

    private OnProgressListener mListener;

    private Path mPath;  //渐变路径

    public void setBgScaleVisibility(boolean mBgScaleVisibility) {
        this.mBgScaleVisibility = mBgScaleVisibility;
    }

    public BrightnessSeekBarBase(Context context) {
        super(context);
    }

    public BrightnessSeekBarBase(Context context, AttributeSet attrs) {
        super(context, attrs);
        gradientColorStart = ContextCompat.getColor(context, R.color.light_brightness_gradient_start);
        gradientColorEnd =  ContextCompat.getColor(context, R.color.light_brightness_gradient_end);
        rXY = context.getResources().getDimension(R.dimen.space_30);
    }


    @Override
    protected synchronized void init() {
        super.init();
        isInitWH = true;
        mPath = new Path();
        float mDensity = getResources().getDisplayMetrics().density;
        mMeasuredHeight = getMeasuredHeight() - (int) (8 * mDensity);
        mMeasuredWidth = getMeasuredWidth();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (!isInitWH) {
            init();
        }
//        if (mTouchY != 0) {
//            onGradient(canvas);
//        }
        drawCalibration(canvas);

        drawCurrentCalibration(canvas);

    }

    /**
     * 渐变处理
     */
    private void onGradient(Canvas canvas) {
        LinearGradient shader = new LinearGradient(0, mTouchY, 0, getMeasuredHeight(), gradientColorEnd, gradientColorStart, Shader.TileMode.MIRROR);
        mGradientPaint.setShader(shader);
        mPath = new Path();

        if (mCurrentProgress < 100 && mCurrentProgress > 5) {
            RectF rectBg = new RectF(0, Math.abs(mTouchY - mProgressLineWidth), getMeasuredWidth(), getMeasuredHeight());
            mPath.addRoundRect(rectBg, getRadius(rXY, false, false, true, true), Path.Direction.CW);
            canvas.drawPath(mPath, mGradientPaint);
        } else {
            if (mCurrentProgress <= 5) { //顶部另外绘制
                RectF rectBg = new RectF(0, mTouchY - mProgressLineWidth > 0 ? mTouchY - mProgressLineWidth : 0, getMeasuredWidth(), getMeasuredHeight());
                mPath.addRoundRect(rectBg, getRadius(rXY, true, true, true, true), Path.Direction.CW);
                canvas.drawPath(mPath, mGradientPaint);
            }
        }
    }

    /**
     * 回调进度
     */
    protected void progressCallBack() {
        if (null != mListener) {

            Log.e(TAG, "--------------------onProgressx---------mTouchY: " + mTouchY);

            if (mTouchY <= mProgressPadding) {
                mCurrentProgress = 0;
            } else if (mTouchY >= mMeasuredHeight - mProgressPadding) {
                mCurrentProgress = 100;
            }
            int progress = 100 - mCurrentProgress;
            Log.e(TAG, "--------------------onProgress---------isFromUser: " + isFromUser);
            Log.e(TAG, "--------------------onProgress---------mTouchY: " + mTouchY);
            mListener.onProgress(progress, mCurrentEvent);
        }
    }



    public int getProgress() {
        return 100 - mCurrentProgress;
    }

    public void setProgress(int progress) {
        Log.e(TAG, "--------------------setProgress---------isFromUser: " + isFromUser);
        if (!isFromUser) {
            //逻辑上会先设置进度，这个时候控件还没有技术完成，需要先缓存这个进度
            this.mCurrentProgress = 100 - progress;
            //百分百先转换成小数点 在乘以 控件高度 = 横线进度位置
            mTouchY = mCurrentProgress * mProgressInterval + mProgressPadding;

            Log.e(TAG, "---------------------------setProgress-------currentProgress: " + this.mCurrentProgress);
            Log.e(TAG, "---------------------------setProgress-------touchY: " + mTouchY);

            isMaxOrMin();
            invalidate();
        }

    }


    @Override
    void calculateTouchY(MotionEvent event) {
        //先算占比
        mCurrentProgress = (int) ((event.getY() - mProgressPadding) / mProgressInterval);
        //根据占比算出toucheY
        mTouchY = mCurrentProgress * mProgressInterval + mProgressPadding;
    }

    @Override
    void checkClickable() {
        //点击时候获取一下是否可点击（设备离线的时候 不可点击）
        if (null != mListener) {
            mClickEnable = mListener.enableClick();
        }
    }

    public boolean isFromUser() {
        return isFromUser;
    }

    public void setOnProgressListener(OnProgressListener listener) {
        this.mListener = listener;
    }
    

    public interface OnProgressListener {
        void onProgress(int progress, int currentEvent);

        boolean enableClick();
    }
}


