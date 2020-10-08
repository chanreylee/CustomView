package com.intretech.customview.view.canvas

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View


/**
 * @PackageName:com.intretech.customview.view.canvas
 * @DESC:
 * @Author:  YQ16685 Chanrey Lee
 * @Date 2020/10/7 - 10:22
 **/
class CanvasScaleView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {
    /**
     * 创建画笔
     */
    private var mPaint = Paint()

    private var mWidth = 0f
    private var mHeight = 0f

    /**
     * 加载时初始化
     */
    init {
        initPaint()
    }

    /**
     *  初始化画笔
     */
    private fun initPaint() {
        mPaint.color = Color.BLACK            //画笔颜色
        mPaint.style = Paint.Style.FILL       //画笔模式为填充
        mPaint.strokeWidth = 10f              //画笔宽度
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        this.mWidth = w.toFloat()
        this.mHeight = h.toFloat()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.translate(mWidth/2, mHeight/2)       // 将坐标系原点移动到画布正中心

        val rectF1 = RectF(0f, -400f, 400f, 0f) // 矩形区域


        //因为先画完黑色的，再设置画布缩放，故蓝色的矩形框较小
//        mPaint.setColor(Color.BLACK);           // 绘制黑色矩形
//        canvas?.drawRect(rectF1,mPaint);

//        canvas?.scale(0.5f,0.5f);                // 画布缩放
//        canvas?.scale(0.5f,0.5f, 200f, 0f);                // 画布缩放,并且缩放中心偏移200f
//        canvas?.scale(-0.5f,-0.5f);                // 画布缩放,当缩放比例为负数的时候会根据缩放中心轴进行翻转
//        canvas?.scale(-0.5f,-0.5f, 200f, 0f);                // 画布缩放,翻转并且缩放中心偏移200f
//
//        mPaint.setColor(Color.BLUE);            // 绘制蓝色矩形
//        canvas?.drawRect(rectF1,mPaint);

        val rectF2 = RectF(-400f, -400f, 400f, 400f) // 矩形区域
        mPaint.color = Color.GREEN
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeWidth = 25f
        for (i in 0..30) {
            canvas?.scale(0.9f, 0.9f)
//            canvas?.drawRect(rectF2, mPaint)
            canvas?.drawCircle(0f, 0f, 400f, mPaint)

        }

    }
}
