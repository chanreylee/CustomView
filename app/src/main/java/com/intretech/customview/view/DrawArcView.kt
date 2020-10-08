package com.intretech.customview.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

/**
 * @PackageName:com.intretech.customview.view
 * @DESC:
 * @Author:  YQ16685 Chanrey Lee
 * @Date 2020/9/30 - 18:42
 **/
class DrawArcView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

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

        val rectF = RectF(100f, 100f, 800f, 400f)

        //绘制背景矩形
//        mPaint.color = Color.GRAY
//        canvas?.drawRect(rectF, mPaint)
//
//        //绘制圆弧
//        mPaint.color = Color.BLUE
//        canvas?.drawArc(rectF, 0f, 90f, false, mPaint)

        //-------------------------------------
        val rectF2 = RectF(100f, 300f, 800f, 600f)
        // 绘制背景矩形
//        mPaint.setColor(Color.GRAY);
//        canvas?.drawRect(rectF2,mPaint);

        // 绘制圆弧
//        mPaint.setColor(Color.BLUE);
        
        /**
         *  startAngle  // 开始角度
         *  sweepAngle  // 扫过角度
         *  useCenter   // 是否使用中心
         */
//        canvas?.drawArc(rectF2,0f,90f,true,mPaint);
        //-------------------------------------

        val rectF3 = RectF(100f, 100f, 600f, 600f)
        // // 绘制背景矩形
//        mPaint.setColor(Color.GRAY);
//        canvas?.drawRect(rectF3,mPaint);

      //  // 绘制圆弧
//        mPaint.setColor(Color.BLUE);
//        canvas?.drawArc(rectF3,0f,90f,false,mPaint);
        //-------------------------------------

        val rectF4 = RectF(100f, 100f, 600f, 600f)
//         绘制背景矩形
        mPaint.color = Color.GRAY
        canvas?.drawRect(rectF4, mPaint)

         //绘制圆弧
        mPaint.color = Color.BLUE
        canvas?.drawArc(rectF4, 0f, 90f, true, mPaint)
        
    }
}
