package com.intretech.customview.view.txt

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 * @PackageName:com.intretech.customview.view.txt
 * @DESC:
 * @Author:  YQ16685 Chanrey Lee
 * @Date 2020/10/7 - 13:53
 **/
class DrawTextView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

    /**
     * 创建画笔
     */
    private val mPaint = Paint()

    init {
        initPaint()
    }

    /**
     * 画笔设置
     */
    fun initPaint() {
        mPaint.color = Color.BLACK
        mPaint.style = Paint.Style.FILL
        mPaint.textSize = 50f
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        //第一类
//        val str = "ABCDEFGHIJK"
//        canvas?.drawText(str, 200f, 500f, mPaint)      //未进行字符串截取的
//        canvas?.drawText(str, 1,3,200f, 500f, mPaint)      //  字符串截取的

        //第二类  方法过时，笨重，反人类，不学习使用
        val str2 = "SLOOP"

        //第三类
        
    }


}
