package com.intretech.customview.view.path

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

/**
 * @PackageName:com.intretech.customview.view.path
 * @DESC:
 * @Author:  YQ16685 Chanrey Lee
 * @Date 2020/10/12 - 11:19
 **/
class PathLastPointView (context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

    /**
     * 创建画笔
     */
    private val mPaint = Paint()
    private var mWidth = 0f
    private var mHeight = 0f

    init {
        initPaint()
    }

    /**
     * 画笔设置
     */
    fun initPaint() {
        mPaint.color = Color.BLACK
        mPaint.style = Paint.Style.STROKE
        mPaint.textSize = 10f
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWidth = w.toFloat()
        mHeight = h.toFloat()
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.translate(mWidth / 2, mHeight / 2)
        val path = Path()
        path.lineTo(200f, 200f) //之前没操作，默认起点为坐标原点  ，参数为终点

        path.setLastPoint(200f, 100f)  //setLastPoint是重置上一次操作的最后一个点,即影响上一次的操作，也影响之后的操作

        path.lineTo(200f, 0f)     //起点为上一次操作的点,参数为终点

        canvas?.drawPath(path, mPaint)
    }
}
