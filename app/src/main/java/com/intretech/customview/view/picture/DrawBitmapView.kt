package com.intretech.customview.view.picture

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.intretech.customview.R

/**
 * @PackageName:com.intretech.customview.view.picture
 * @DESC:
 * @Author:  YQ16685 Chanrey Lee
 * @Date 2020/10/7 - 13:53
 **/
class DrawBitmapView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

    private val bitmap: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.bitmap)

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
    

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
//        canvas?.drawBitmap(bitmap, Matrix(), Paint())     // 图片左上角位置默认为坐标原点。

        canvas?.drawBitmap(bitmap, 200f, 500f, Paint())    //绘制时指定了图片左上角的坐标(距离坐标原点的距离)

//        canvas?.translate(mWidth/2, mHeight/2)
//        val src = Rect(0, 0, bitmap.width/2, bitmap.height/2) // 指定图片绘制区域(左上角的四分之一)
//        val dst = Rect(0, 0, 200, 400)    // 指定图片在屏幕上显示的区域
//        canvas?.drawBitmap(bitmap, src, dst, null)       // 绘制图片
    }

}
