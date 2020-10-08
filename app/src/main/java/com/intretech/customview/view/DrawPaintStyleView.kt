package com.intretech.customview.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 * @PackageName:com.intretech.customview.view
 * @DESC: Paint效果测试View
 * @Author:  YQ16685 Chanrey Lee
 * @Date 2020/10/7 - 6:47
 **/
class DrawPaintStyleView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {


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
        mPaint.color = Color.BLUE            //画笔颜色
        mPaint.strokeWidth = 40f              //画笔宽度
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        // 描边
        mPaint.style = Paint.Style.STROKE;
        canvas?.drawCircle(200F,200F,100F,mPaint);

        // 填充
        mPaint.style = Paint.Style.FILL;
        canvas?.drawCircle(200f,500f,100f,mPaint);

        // 描边加填充
        mPaint.style = Paint.Style.FILL_AND_STROKE;
        canvas?.drawCircle(200f, 800f, 100f, mPaint)
    }
}
