package com.intretech.customview.view.path

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View


/**
 * @PackageName:com.intretech.customview.view.path
 * @DESC:
 * @Author:  YQ16685 Chanrey Lee
 * @Date 2020/10/14 - 14:27
 **/
class PathBoundView (context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

    private val mPaint = Paint()

    private var mCenterX = 0f
    private var mCenterY = 0f


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mCenterX = (w /2).toFloat()
        mCenterY = (h /2).toFloat()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.translate(mCenterX, mCenterY)

        val rect1 = RectF() // 存放测量结果的矩形


        val path = Path() // 创建Path并添加一些内容

        path.lineTo(100f, -50f)
        path.lineTo(100f, 50f)
        path.close()
        path.addCircle(-100f, 0f, 100f, Path.Direction.CW)

        path.computeBounds(rect1, true) // 测量Path


        canvas.drawPath(path, mPaint) // 绘制Path


        mPaint.style = Paint.Style.STROKE
        mPaint.color = Color.RED
        
        canvas.drawRect(rect1, mPaint) // 绘制边界

    }

    private fun initPaint() {
        mPaint.color = Color.BLACK
        mPaint.style = Paint.Style.FILL
        mPaint.textSize = 60f
    }

    init {
        initPaint()
    }
}
