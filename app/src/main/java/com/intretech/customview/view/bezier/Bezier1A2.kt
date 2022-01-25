package com.intretech.customview.view.bezier

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

/**
 * @PackageName:com.intretech.customview.view.bezier
 * @DESC:
 * @Author: YQ16685 Chanrey Lee
 * @Date 2020/10/13 - 11:44
 */
class Bezier1A2(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {
    private val mPaint: Paint
    private var centerX = 0
    private var centerY = 0
    private val start: PointF
    private val end: PointF
    private val control: PointF

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        centerX = w / 2
        centerY = h / 2

        // 初始化数据点和控制点的位置
        start.x = centerX - 200.toFloat()
        start.y = centerY.toFloat()
        end.x = centerX + 200.toFloat()
        end.y = centerY.toFloat()
        control.x = centerX.toFloat()
        control.y = centerY - 100.toFloat()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        // 根据触摸位置更新控制点，并提示重绘
        control.x = event.x
        control.y = event.y
        invalidate()
        return true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // 绘制数据点和控制点
        mPaint.color = Color.GRAY
        mPaint.strokeWidth = 20f
        canvas.drawPoint(start.x, start.y, mPaint)
        canvas.drawPoint(end.x, end.y, mPaint)
        canvas.drawPoint(control.x, control.y, mPaint)

        // 绘制辅助线
        mPaint.strokeWidth = 4f
        canvas.drawLine(start.x, start.y, control.x, control.y, mPaint)
        canvas.drawLine(end.x, end.y, control.x, control.y, mPaint)

        // 绘制贝塞尔曲线
        mPaint.color = Color.RED
        mPaint.strokeWidth = 8f
        val path = Path()
        path.moveTo(start.x, start.y)
        path.quadTo(control.x, control.y, end.x, end.y)
        canvas.drawPath(path, mPaint)
    }

    init {
        mPaint = Paint()
        mPaint.color = Color.BLACK
        mPaint.strokeWidth = 8f
        mPaint.style = Paint.Style.STROKE
        mPaint.textSize = 60f
        start = PointF(0f, 0f)
        end = PointF(0f, 0f)
        control = PointF(0f, 0f)
    }
}
