package com.intretech.customview.view

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
 * @Date 2020/9/30 - 16:19
 **/
class DrawOvalView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {
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

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        // 第一种
        val rectF = RectF(100f, 100f, 800f, 400f)
        canvas?.drawOval(rectF, mPaint)


        // 第二种
//        canvas?.drawOval(100f, 100, 800f, 400f, mPaint)


    }
}
