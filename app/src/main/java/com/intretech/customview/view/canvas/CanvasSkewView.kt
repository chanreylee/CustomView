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
 * @Date 2020/10/7 - 11:31
 **/
class CanvasSkewView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

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
        mPaint.style = Paint.Style.STROKE       //画笔模式为填充
        mPaint.strokeWidth = 5f              //画笔宽度
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        this.mWidth = w.toFloat()
        this.mHeight = h.toFloat()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        // 将坐标系原点移动到画布正中心
        canvas?.translate(mWidth / 2, mHeight / 2);
        val rect = RectF(0f, 0f, 200f, 200f) // 矩形区域

        canvas?.drawRect(rect,mPaint);

        canvas?.skew(1f,0f);           // 水平错切 <- 45度
        canvas?.skew(0f,1f);           // 水平错切 <- 45度

        mPaint.color = Color.BLUE;            // 绘制蓝色矩形
        canvas?.drawRect(rect,mPaint);
    }
}
