package com.intretech.customview.view.charts

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin

/**
 * @PackageName:com.intretech.customview.view.charts
 * @DESC:
 * @Author:  YQ16685 Chanrey Lee
 * @Date 2020/10/12 - 18:29
 **/
class RadarView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

    private var count = 6 //数据个数

    private var angle = (Math.PI * 2 / count).toDouble()
    private var radius //网格最大半径
            = 0f
    private var centerX//中心X
            = 0f
    private var centerY //中心Y
            = 0f
    private var titles =
        arrayOf("a", "b", "c", "d", "e", "f")
    private val data =
        doubleArrayOf(100.0, 60.0, 60.0, 60.0, 100.0, 50.0, 10.0, 20.0) //各维度分值

    private val maxValue = data.maxOrNull()!!  //数据最大值

    private val radarPaint = Paint()    //雷达区画笔
    private val valuePaint = Paint()    //数据区画笔
    private val textPaint = Paint()     //文本画笔

    init {
        initPaint()
    }

    fun initPaint() {
        radarPaint.color = Color.BLACK
        radarPaint.style = Paint.Style.STROKE
        radarPaint.textSize = 10f

        textPaint.color = Color.BLUE
        textPaint.style = Paint.Style.FILL
        textPaint.textSize = 30f
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        radius = min(h, w) /2 * 0.8f
        centerX = w.toFloat() /2
        centerY = h.toFloat() /2
        postInvalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawPolygon(canvas)   //绘制正多边形
        drawLine(canvas)      //绘制中心点到每个维度的直线
        drawText(canvas)      //在最外围添加维度文本
        drawRegion(canvas)    //每个维度的点和及围成的区域填充
    }

    /**
     * 绘制正多边形
     */
    private fun drawPolygon(canvas: Canvas?) {
        val path = Path()
        var curR = 0f
        val r = radius / (count - 1)
        for (i in 1 until count) {   //环数
            curR = r * i
            path.reset()
            for (j in 0 until count) {   //维度
                if (j == 0) {
                    path.moveTo(centerX+curR, centerY)
                } else {
                    val x = (centerX + curR * cos(angle*j)).toFloat()
                    val y = (centerY + curR * sin(angle*j)).toFloat()
                    path.lineTo(x, y)
                }
            }
            path.close()
            canvas?.drawPath(path, radarPaint)
        }
    }

    /**
     *  中心到末端的直线,每个维度1条
     */
    private fun drawLine(canvas: Canvas?) {
        val path = Path()
        for(i in 0 until count) {
            path.reset()
            path.moveTo(centerX, centerY)
            val x = (centerX + radius * cos(angle*i)).toFloat()
            val y = (centerY + radius * sin(angle*i)).toFloat()
            path.lineTo(x, y)
            canvas?.drawPath(path, radarPaint)
        }
    }

    private fun drawText(canvas: Canvas?) {
        val fontMetrics = textPaint.fontMetrics
        val fontHeight = fontMetrics.descent - fontMetrics.ascent  //
        for (i in 0 until count) {
            val x = (centerX + (radius + fontHeight/2)* cos(angle*i)).toFloat()
            val y = (centerY + (radius + fontHeight/2)* sin(angle*i)).toFloat()
            if (angle*i >= 0f && angle * i <= Math.PI*2) {    //第4象限
                canvas?.drawText(titles[i], x,y,textPaint);
            } else if(angle*i>=3*Math.PI/2&&angle*i<=Math.PI*2){//第3象限
                canvas?.drawText(titles[i], x,y,textPaint);
            }else if(angle*i>Math.PI/2&&angle*i<=Math.PI){//第2象限
                val dis = textPaint.measureText(titles[i]);//文本长度
                canvas?.drawText(titles[i], x-dis,y,textPaint);
            }else if(angle*i>=Math.PI&&angle*i<3*Math.PI/2){//第1象限
                val dis = textPaint.measureText(titles[i]);//文本长度
                canvas?.drawText(titles[i], x-dis,y,textPaint);
            }
        }
    }

    private fun drawRegion(canvas: Canvas?) {
        valuePaint.style = Paint.Style.FILL_AND_STROKE
        valuePaint.color = Color.BLUE
        val path = Path()
        valuePaint.alpha = 255
        for (i in 0 until count) {
            val percent = data[i] / (maxValue?.toFloat() ?: 1f)
            val x = (centerX + radius * cos(angle * i) * percent).toFloat()
            val y = (centerY + radius * sin(angle * i) * percent).toFloat()
            if (i == 0) {
                path.moveTo(x, centerY)
            } else {
                path.lineTo(x, y)
            }
            //绘制小圆点
            canvas!!.drawCircle(x, y, 10f, valuePaint)
        }
        valuePaint.style = Paint.Style.STROKE
        canvas!!.drawPath(path, valuePaint)
        valuePaint.alpha = 127
        //绘制填充区域
        //绘制填充区域
        valuePaint.style = Paint.Style.FILL_AND_STROKE
//        path.close()
        canvas.drawPath(path, valuePaint)
    }
}
