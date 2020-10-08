package com.intretech.customview.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import java.util.jar.Attributes

/**
 * @PackageName:com.intretech.customview.view
 * @DESC:
 * @Author:  YQ16685 Chanrey Lee
 * @Date 2020/9/30 - 14:26
 **/
class DrawPointView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

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

    /**
     * 绘图方法 ,再此方法中生效
     */
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawPoint(200F, 200F, mPaint)   //一个点
        canvas?.drawPoints(floatArrayOf(              //多个点
            500f, 500f,
            500f, 600f,
            500f, 700f), mPaint)
    }
}
