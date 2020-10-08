package com.intretech.customview.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

/**
 * @PackageName:com.intretech.customview.view
 * @DESC:
 * @Author:  YQ16685 Chanrey Lee
 * @Date 2020/9/30 - 15:22
 **/
class DrawRectView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet){


    /**
     * 创建画笔
     */
    private var mPaint = Paint()

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

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        //第一种
//        canvas?.drawRect(100f, 100f, 800f, 400f, mPaint)

        //第二种
        val rect = Rect(100, 100, 800, 400)
        canvas?.drawRect(rect, mPaint)

        //第三种
//        val rectF = RectF(100f, 100f, 800f, 400f)
//        canvas?.drawRect(rectF, mPaint)

        /**
         * 为什么会有Rect和RectF两种？两者有什么区别吗？

         *答案当然是存在区别的，两者最大的区别就是精度不同，Rect是int(整形)的，而RectF是float(单精度浮点型)的。
         *除了精度不同，两种提供的方法也稍微存在差别，在这里我们暂时无需关注，想了解更多参见官方文档 Rect 和 RectF
         */
    }
}
