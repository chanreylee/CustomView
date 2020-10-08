package com.intretech.customview.view.picture

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.os.Handler
import android.os.Message
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.intretech.customview.R

/**
 * @PackageName:com.intretech.customview.view.picture
 * @DESC:
 * @Author:  YQ16685 Chanrey Lee
 * @Date 2020/10/7 - 16:19
 **/
class CheckView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

    private val TAG = CheckView::class.java.simpleName

    private val ANIM_NULL = 0 //动画状态-没有

    private val ANIM_CHECK = 1 //动画状态-开启

    private val ANIM_UNCHECK = 2 //动画状态-结束

    private var mWidth = 0f
    private var mHeight = 0f

    var mHandler: Handler? = null


    val mPaint = Paint()

    var bitmap: Bitmap? = null

    private var animCurrentPage = -1 // 当前页码

    private val animMaxPage = 13 // 总页数

    private var animDuration = 500 // 动画时长

    private var animState = ANIM_NULL // 动画状态

    var isCheck = false // 是否只选中状态


    /**
     *  初始化画笔
     */
    private fun initPaint() {
        mPaint.color = Color.GREEN;            //画笔颜色
        mPaint.style = Paint.Style.FILL       //画笔模式为填充
        mPaint.isAntiAlias = true;
        mPaint.strokeWidth = 5f              //画笔宽度
    }

    init {
        initPaint()
        bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.checkmark)
        setHandle()
    }

    fun setHandle() {
        mHandler = @SuppressLint("HandlerLeak")
        object :Handler() {
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)

                if (animCurrentPage < animMaxPage && animCurrentPage >= 0) {
                    invalidate()
                    if (animState == ANIM_NULL)
                        return
                    if (animState == ANIM_CHECK) {
                        animCurrentPage++
                    } else if (animState == ANIM_UNCHECK) {
                        animCurrentPage--
                    }

                    if (animCurrentPage != animMaxPage) {
                        invalidate()
                    }
                    this.sendEmptyMessageDelayed(0, (animDuration / animMaxPage).toLong())
                    Log.w(TAG, "Count = $animCurrentPage")

                } else {
                    if (isCheck) {
                        animCurrentPage = animMaxPage - 1
                    } else {
                        animCurrentPage = -1
                    }
                    invalidate()
                    animState = ANIM_NULL
                }
            }
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWidth = w.toFloat()
        mHeight = h.toFloat()
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.translate(mWidth/2, mHeight/2)

        canvas?.drawCircle(0f, 0f, 240f, mPaint)

        val sideLength = bitmap?.height

        val src = Rect(sideLength!! * animCurrentPage, 0, sideLength*(animCurrentPage+1), sideLength)
        val dst = Rect(-200, -200, 200, 200)

        canvas?.drawBitmap(bitmap!!, src, dst, null)
    }

    fun check() {
        if (animState != ANIM_NULL || isCheck) {
            return
        }
        animState = ANIM_CHECK
        animCurrentPage = 0
        mHandler?.sendEmptyMessageDelayed(0, (animDuration / animMaxPage).toLong())
        isCheck = true
    }

    fun unCheck() {
        if (animState != ANIM_NULL || !isCheck) {
            return
        }
        animState = ANIM_UNCHECK
        animCurrentPage = animMaxPage - 1
        mHandler?.sendEmptyMessageDelayed(0, (animDuration / animMaxPage).toLong())
        isCheck = false
    }

    /**
     * 设置动画时长
     * @param animDuration
     */
    fun setAnimDuration(animDuration: Int) {
        if (animDuration <= 0) return
        this.animDuration = animDuration
    }

    /**
     * 设置背景圆形颜色
     * @param color
     */
    override fun setBackgroundColor(color: Int) {
        mPaint.color = color
    }

}
