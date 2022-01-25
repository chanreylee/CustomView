package com.intretech.customview.view.bezier;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @PackageName:com.intretech.customview.view.bezier
 * @DESC: 原文https://blog.csdn.net/wanxuedong/article/details/71805579,
 * github:    https://github.com/DevinShine/MagicCircle
 * @Author: YQ16685 Chanrey Lee
 * @Date 2020/10/14 - 9:56
 **/
public class MagicCircle extends View {

    private Path mPath;
    private Paint mFillCirclePaint;

    /**
     * View的宽度
     **/
    private int width;
    /**
     * View的高度，这里View应该是正方形，所以宽高是一样的
     **/
    private int height;

    private float maxLength;
    private float mInterpolatedTime;
    private float stretchDistance;
    private float cDistance;
    private float radius;
    private float c;
    private float blackMagic = 0.551915024494f;   //这是三次贝塞尔式的常量，详解请见:http://spencermortensen.com/articles/bezier-circle/

    /*
     * 下面定义了p1, p2, p3, p4，四个对象，人要想明明是四个控制点，为什么要定义四个对象呢，因为这里是为了把这四个点和他们的辅助点联系在一起，
     * 所以总共应该是四个控制点和八个辅助点
     * */
    private PointF p2, p4;   //定义了p2，p4两个对象，即圆的上面两个点，一个是右边的点，一个是左边的点
    private PointF p1, p3;  //定义了p1，p3两个对象，即圆的上面两个点，一个是下边的点，一个是上面边的点


    public MagicCircle(Context context) {
        this(context, null);
    }

    public MagicCircle(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initData();
    }

    private void initData() {

        /*
         * 下面设置一些这个圆的参数，包括颜色，圆边框宽度(因为圆被填充了，所以也看不出来),还有抗锯齿
         * */
        mFillCirclePaint = new Paint();
        mFillCirclePaint.setColor(0xFFfe626d);
        mFillCirclePaint.setStyle(Paint.Style.FILL);
        mFillCirclePaint.setStrokeWidth(1);
        mFillCirclePaint.setAntiAlias(true);
        mPath = new Path();
        p2 = new PointF(0, 0);
        p4 = new  PointF(0, 0);

        p1 = new  PointF(0, 0);
        p3 = new PointF(0, 0);

        /*
         * 给四个点做初始化，各自固定好位置，等下用来变形
         * */
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = getWidth();
        height = getHeight();
        radius = 50;
        c = radius * blackMagic;
        stretchDistance = radius;
        cDistance = c * 0.45f;
        maxLength = width - radius / 8;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        
    }
}
