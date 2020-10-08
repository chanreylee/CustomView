package com.intretech.customview.view.canvas

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 * @PackageName:com.intretech.customview.view.canvas
 * @DESC:
 * @Author:  YQ16685 Chanrey Lee
 * @Date 2020/10/7 - 10:06
 **/
class CanvasTranslateView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

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
        // 在坐标原点绘制一个黑色圆形
        mPaint.setColor(Color.BLACK);
        canvas?.translate(200f,200f);    //设置新的坐标原点
        canvas?.drawCircle(0f,0f,100f,mPaint);

        // 在坐标原点绘制一个蓝色圆形
        mPaint.setColor(Color.BLUE);
        canvas?.translate(200f,200f);    //设置新的坐标原点。可累加
        canvas?.drawCircle(0f,0f,100f,mPaint);   //圆心(Cx, Cy),半径radius
    }
}
