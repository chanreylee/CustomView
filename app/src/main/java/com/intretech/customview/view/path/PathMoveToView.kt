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
class PathMoveToView (context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

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

        path.moveTo(200f, 100f)  //  移动下一次操作的起点位置,不影响之前的操作,影响之后的

        path.lineTo(200f, 0f)     //起点为上一次操作的点,参数为终点

        canvas?.drawPath(path, mPaint)
    }
}
