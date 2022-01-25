package com.intretech.customview.view.path

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

/**
 * @PackageName:com.intretech.customview.view.path
 * @DESC: 各类基本图形绘制和addTo使用
 * @Author:  YQ16685 Chanrey Lee
 * @Date 2020/10/12 - 13:26
 **/
class PathAddShapes(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

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
        val path = Path()

        /*
        第1类
          绘制矩形，顺时针,重置最后一个点后就不是了，包括时针方向的改变也是。
         */
        val rectangleF = RectF(-200f, -200f, 200f,200f)
//        path.addRect(rectangleF, Path.Direction.CW)
//        path.setLastPoint(-300f,300f);                // <-- 重置最后一个点的位置

        /**
         * 第2类,合并path
         */
        canvas?.scale(1f, -1f)  //画布翻转
        val src = Path()
        src.addCircle(0f, 0f, 100f, Path.Direction.CW)
        path.addPath(src)      //直接添加src
//        path.addPath(src, 0f, 200f)   //绘制src的原点点偏移，仅影响src绘制
//        path.addPath(src, Matrix())    //将src添加到当前path之前先使用Matrix进行变换。

        canvas?.drawPath(path, mPaint)
    }
}
