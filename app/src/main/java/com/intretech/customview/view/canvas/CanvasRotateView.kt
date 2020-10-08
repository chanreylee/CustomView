package com.intretech.customview.view.canvas

/**
 * @PackageName:com.intretech.customview.view.canvas
 * @DESC:
 * @Author:  YQ16685 Chanrey Lee
 * @Date 2020/10/7 - 11:07
 **/

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
 * @Date 2020/10/7 - 10:22
 **/
class CanvasRotateView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {
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
        mPaint.style = Paint.Style.FILL       //画笔模式为填充
        mPaint.strokeWidth = 10f              //画笔宽度
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        this.mWidth = w.toFloat()
        this.mHeight = h.toFloat()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.translate(mWidth/2, mHeight/2)       // 将坐标系原点移动到画布正中心

        val rectF1 = RectF(0f, -400f, 400f, 0f) // 矩形区域
//        canvas?.drawRect(rectF1, mPaint)
//
//        //旋转是选择顺时针旋转
////        canvas?.rotate(180f)           // 旋转180度 <-- 默认旋转中心为原点
////        canvas?.rotate(180f, 200f, 0f)           // 旋转180度 <-- 默认旋转中心为原点
//        canvas?.rotate(180f)           // 旋转180度 <-- 默认旋转中心为原点
//        canvas?.rotate(20f)           //
//
//        mPaint.color = Color.BLUE
//        canvas?.drawRect(rectF1, mPaint)
        
        mPaint.color = Color.BLACK            //画笔颜色
        mPaint.style = Paint.Style.STROKE      //画笔模式为填充
        mPaint.strokeWidth = 5f

        canvas?.drawCircle(0f, 0f, 400f, mPaint)
        canvas?.drawCircle(0f, 0f, 320f, mPaint)

        for (i in 0..360 step 10) {
            canvas?.drawLine(0f, 320f, 0f, 400f, mPaint)
            canvas?.rotate(10f)
        }
    }
}
