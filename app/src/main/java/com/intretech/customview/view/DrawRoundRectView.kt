package com.intretech.customview.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

/**
 * @PackageName:com.intretech.customview.view
 * @DESC:
 * @Author:  YQ16685 Chanrey Lee
 * @Date 2020/9/30 - 15:45
 **/
class DrawRoundRectView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

    /**
     * 创建画笔
     */
    private var mPaint = Paint()

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

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val rectF = RectF(100f, 100f, 800f, 400f)
        canvas?.drawRoundRect(rectF, 30f, 30f, mPaint)   //圆角矩形

        mPaint.color = Color.GRAY
        canvas?.drawRect(rectF, mPaint)     //矩形

        mPaint.color = Color.BLUE
        canvas?.drawRoundRect(rectF, 350f, 150f, mPaint)       //椭圆
    }
}
