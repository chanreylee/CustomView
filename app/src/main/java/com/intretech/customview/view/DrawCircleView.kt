package com.intretech.customview.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 * @PackageName:com.intretech.customview.view
 * @DESC:
 * @Author:  YQ16685 Chanrey Lee
 * @Date 2020/9/30 - 16:30
 **/
class DrawCircleView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet){

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
        canvas?.drawCircle(500f,500f,400f ,mPaint);  // 绘制一个圆心坐标在(500,500)，半径为400 的圆。
    }
}
