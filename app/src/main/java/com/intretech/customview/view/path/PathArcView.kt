package com.intretech.customview.view.path

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

/**
 * @PackageName:com.intretech.customview.view.path
 * @DESC:
 * @Author:  YQ16685 Chanrey Lee
 * @Date 2020/10/12 - 14:26
 **/
class PathArcView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

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
        canvas?.translate(mWidth/2, mHeight/2)
        canvas?.scale(1f, -1f)
        draw01(canvas)  //
    }

    private fun draw01(canvas: Canvas?) {
        val path = Path()
        path.lineTo(100f, 100f)

        val oval = RectF(0f, 0f, 300f, 300f)

//        path.addArc(oval, 0f, 270f)
//        path.arcTo(oval, 0f, 270f, true) //和上面一句作用等价

        path.arcTo(oval, 0f, 270f)
//        path.arcTo(oval, 0f, 270f, false)  //和上面一句作用等价
        canvas?.drawPath(path, mPaint)
    }
}
