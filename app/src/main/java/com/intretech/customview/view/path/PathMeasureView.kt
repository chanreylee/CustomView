package com.intretech.customview.view.path

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View


/**
 * @PackageName:com.intretech.customview.view.path
 * @DESC:
 * @Author:  YQ16685 Chanrey Lee
 * @Date 2020/10/14 - 15:28
 **/
class PathMeasureView(context: Context, attributeSet: AttributeSet) :View(context, attributeSet) {
    private val TAG = "PathMeasureView"
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

//        pathMeasure(canvas)     //测path边界长度
//        measure01(canvas)
        measure02(canvas)
    }


    private fun measure02(canvas: Canvas) {

        val path = Path() // 创建Path并添加了一个矩形

        path.addRect(-200f, -200f, 200f, 200f, Path.Direction.CW)

        val dst = Path() // 创建用于存储截取后内容的 Path

        dst.lineTo(-300f, -300f) // <--- 在 dst 中添加一条线段


        val measure = PathMeasure(path, false) // 将 Path 与 PathMeasure 关联

        /**
         * 如果 startWithMoveTo 为 true, 则被截取出来到Path片段保持原状，
         * 如果 startWithMoveTo 为 false，则会将截取出来的 Path 片段的起始点移动到 dst 的最后一个点，
         * 以保证 dst 的连续性。即：
         *   true 保证截取得到的 Path 片段不会发生形变
         *   false 保证存储截取片段的 Path(dst) 的连续性
         */
        measure.getSegment(200f, 600f, dst, false) // 截取一部分 并使用 moveTo 保持截取得到的 Path 第一个点的位置不变


        canvas.drawPath(dst,mPaint) // 绘制 Path

    }

    private fun measure01(canvas: Canvas){
        val path = Path() // 创建Path并添加了一个矩形

        path.addRect(-200f, -200f, 200f, 200f, Path.Direction.CW)

        val dst = Path() // 创建用于存储截取后内容的 Path


        val measure = PathMeasure(path, false) // 将 Path 与 PathMeasure 关联


// 截取一部分存入dst中，并使用 moveTo 保持截取得到的 Path 第一个点的位置不变

// 截取一部分存入dst中，并使用 moveTo 保持截取得到的 Path 第一个点的位置不变
        measure.getSegment(200f, 600f, dst, true)

        canvas.drawPath(dst,mPaint)
    }

    private fun pathMeasure(canvas: Canvas) {
        val path = Path()

        path.lineTo(0f, 200f)
        path.lineTo(200f, 200f)
        path.lineTo(200f, 0f)

        val measure1 = PathMeasure(path, false)
        val measure2 = PathMeasure(path, true)

        Log.e("TAG", "forceClosed=false---->" + measure1.length)
        Log.e("TAG", "forceClosed=true----->" + measure2.length)

        canvas.drawPath(path, mPaint)
    }

    private fun initPaint() {
        mPaint.color = Color.BLACK
        mPaint.style = Paint.Style.STROKE
        mPaint.textSize = 60f
    }

    init {
        initPaint()
    }
}

