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
 * @DESC: 布尔操作
 * @Author:  YQ16685 Chanrey Lee
 * @Date 2020/10/14 - 13:51
 **/
class PathBoolOp(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

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
        val path1 = Path()
        val path2 = Path()
        val path3 = Path()
        val path4 = Path()

        path1.addCircle(0f, 0f, 200f, Path.Direction.CW)
        path2.addRect(0f, -200f, 200f,200f, Path.Direction.CW)
        path3.addCircle(0f, -100f, 100f, Path.Direction.CW)
        path4.addCircle(0f, 100f, 100f, Path.Direction.CCW)
//
        path1.op(path2, Path.Op.DIFFERENCE)
        path1.op(path3, Path.Op.UNION)
        path1.op(path4, Path.Op.DIFFERENCE)

        canvas.drawPath(path1, mPaint)
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
