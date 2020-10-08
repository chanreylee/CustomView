package com.intretech.customview.view.picture

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.graphics.drawable.PictureDrawable
import android.util.AttributeSet
import android.view.View

/**
 * @PackageName:com.intretech.customview.view.picture
 * @DESC:
 * @Author:  YQ16685 Chanrey Lee
 * @Date 2020/10/7 - 13:52
 **/
class DrawPictureView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {


    private val mPicture = Picture()

    init {
        recording()
    }

    private fun recording() {
        val canvas = mPicture.beginRecording(500, 500)     // 开始录制 (接收返回值Canvas)
        val paint = Paint()      // 创建一个画笔
        paint.color = Color.BLUE
        paint.style = Paint.Style.FILL

        /*
         *在Canvas中具体操作
         */
        canvas.translate(250f, 250f)   // 位移
        canvas.drawCircle(0f, 0f, 100f, paint)     // 绘制一个圆
        mPicture.endRecording()
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
//        mPicture.draw(canvas!!)      //这种方法在比较低版本的系统上绘制后可能会影响Canvas状态，所以这种方法一般不会使用。

//        canvas?.drawPicture(mPicture)
//          canvas?.drawPicture(mPicture, RectF(0f, 0f, mPicture.width.toFloat(),200f))      //缩放

        // 包装成为Drawable
        val drawable = PictureDrawable(mPicture)
        drawable.setBounds(0, 0, 250, mPicture.height)   // 设置绘制区域 -- 注意此处所绘制的实际内容不会缩放
        drawable.draw(canvas!!)
    }
}
