package com.intretech.customview.view.pie

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Toast
import com.intretech.customview.R
import kotlin.math.min

/**
 * @PackageName:com.intretech.customview.view
 * @DESC:
 * @Author:  YQ16685 Chanrey Lee
 * @Date 2020/10/7 - 6:58
 **/
class DrawPieView : View {
    
    constructor(context: Context): super(context)
    constructor(context: Context, attributeSet: AttributeSet?): super(context, attributeSet)

    private val TAG = DrawPieView::class.java.simpleName

    // 颜色表(注意: 此处定义颜色使用的是ARGB，带Alpha通道的)
    private val mColors = intArrayOf(
        Color.BLACK, Color.BLUE, Color.GRAY,
        Color.GREEN, Color.RED, Color.YELLOW,
        Color.CYAN, Color.DKGRAY, Color.MAGENTA
    )

    // 饼状图初始绘制角度
    private var mStartAngle = 0f

    // 数据
    private var mData: ArrayList<PieData>? = null

    // 宽高
    private var mWidth = 0  // 宽高
    private var mHeight = 0

    // 画笔
    private val mPaint: Paint = Paint()

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWidth = w
        mHeight = h
    }

    init {
        mPaint.style = Paint.Style.FILL       //画笔模式为填充
        mPaint.strokeWidth = 10f              //画笔宽度
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (null == mData)
            return
        var currentStartAngle = mStartAngle         // 当前起始角度
        canvas?.translate(mWidth/2f, mHeight/2f)     // 将画布坐标原点移动到中心位置
        val r = (min(mWidth, mHeight) / 2 * 0.8).toFloat()  // 饼状图半径
        val rectf = RectF(-r, -r, r, r)                         // 饼状图绘制区域

        for(i in mData!!.indices) {
            val pie = mData!![i]
            mPaint.color = pie.color  //如果颜色封装了，此处需要判空并添加默认值
            canvas?.drawArc(rectf, currentStartAngle, pie.angle, true, mPaint)
            currentStartAngle += pie.angle    //这个不能落掉，否则圆形出不来
        }
    }

    fun setStartAngle(mStartAngle: Int) {
        this.mStartAngle = mStartAngle.toFloat()
        invalidate()           // 刷新
    }

    fun setData(mData: ArrayList<PieData>) {
        this.mData = mData
        initData(this.mData!!)
        invalidate()             // 刷新
    }

    private fun initData(mData: ArrayList<PieData>) {
        if (null == mData || mData.size == 0)    // 数据有问题 直接返回
            return
        var sumValue = 0f
        for (i in mData.indices) {
            val pie = mData[i]
            sumValue = sumValue.plus(pie.value)   //计算数值和\

            val j = i % mColors.size         //设置颜色
            pie.color = mColors[j]
            Log.i(TAG, "$j")
        }

        var sumAngle = 0f
        for (i in mData.indices) {
            val pie = mData[i]
            val percentage = pie.value / sumValue          // 百分比
            val angle = percentage * 360                     // 对应的角度

            pie.percentage = percentage                             // 记录百分比
            pie.angle = angle                                      // 记录角度大小
            sumAngle += angle
            Log.i(TAG, "$sumAngle, $sumValue")
        }
    }
}
