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
 * @DESC: 奇偶规则、反奇偶等
 * @Author:  YQ16685 Chanrey Lee
 * @Date 2020/10/14 - 10:48
 **/
class PathOdevity(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

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
        canvas.translate(mCenterX.toFloat(), mCenterY.toFloat())
        val path = Path()
        path.addRect(-200f, -200f, 200f, 200f,Path.Direction.CW)
//        path.addRect(-200f, -200f, 200f, 200f,Path.Direction.CCW)

        path.addRect(-400f, -400f, 400f, 400f, Path.Direction.CCW)
//        path.fillType = Path.FillType.EVEN_ODD  //奇偶规则
//        path.fillType = Path.FillType.INVERSE_EVEN_ODD  //反奇偶规则
//        path.fillType = Path.FillType.WINDING //非零环绕数规则
        path.fillType = Path.FillType.INVERSE_WINDING //反非零环绕数规则

        canvas.drawPath(path, mPaint)
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
