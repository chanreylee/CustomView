package com.intretech.customview.widget

import androidx.constraintlayout.widget.ConstraintLayout
import android.widget.TextView
import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import com.intretech.customview.R
import android.view.LayoutInflater
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.intretech.customview.widget.CardItemViewXX
import androidx.annotation.StringRes
import androidx.annotation.ColorRes

/**
 * @PackageName:com.intretech.customview.widget
 * @DESC:
 * @author: YQ16685 Chanrey Lee
 * @date 2022/1/19 - 18:46
 */
class CardItemViewXX : ConstraintLayout {
    private var ivIcon: ImageView? = null
    private var tvTitle: TextView? = null
    private var tvAuxiliary: TextView? = null
    private var viewDivider: View? = null
    private fun init(context: Context) {
    }

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context)
        @SuppressLint("CustomViewStyleable", "Recycle") val ta =
            context.obtainStyledAttributes(attrs, R.styleable.CardItemView)
        val itemIcon =
            ta.getResourceId(R.styleable.CardItemView_icon, R.drawable.dot_solid_transparency)
        val title = ta.getString(R.styleable.CardItemView_itemTitle)
        val auxiliary = ta.getString(R.styleable.CardItemView_auxiliary)
        val auxiliaryColor = ta.getColor(
            R.styleable.CardItemView_auxiliaryColor,
            resources.getColor(R.color.gray_9B9B9B)
        )
        val isDivider = ta.getBoolean(R.styleable.CardItemView_isDivider, false)
        val view = LayoutInflater.from(context).inflate(R.layout.item_card_view, this, true)
        ivIcon = view.findViewById(R.id.iv_item_card_icon)
        tvTitle = view.findViewById(R.id.tv_item_card_title)
        tvAuxiliary = view.findViewById(R.id.tv_item_card_auxiliary)
        viewDivider = view.findViewById(R.id.view_divider_item_card)
        ivIcon?.setImageResource(itemIcon)
        tvTitle?.setText(title)
        tvAuxiliary?.setText(auxiliary)
        tvAuxiliary?.setTextColor(auxiliaryColor)
        viewDivider?.setVisibility(if (isDivider) VISIBLE else INVISIBLE)
        ta.recycle()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context)
    }

    fun setIvIcon(drawable: Drawable?) {
        ivIcon!!.setImageDrawable(drawable)
    }

    fun setIcon(@DrawableRes drawable: Int) {
        ivIcon!!.setImageResource(drawable)
    }

    fun setTitle(text: CharSequence?): CardItemViewXX {
        tvTitle!!.text = text
        return this
    }

    fun setTitle(@StringRes id: Int): CardItemViewXX {
        return setTitle(resources.getString(id))
    }

    fun setAuxiliary(auxiliary: String?) {
        tvAuxiliary!!.text = auxiliary
    }

    fun setAuxiliaryColor(@ColorRes color: Int) {
        tvAuxiliary!!.setTextColor(context!!.resources.getColor(color))
    }

    fun setAuxiliaryVisible(visible: Boolean) {
        if (visible) {
            tvAuxiliary!!.visibility = VISIBLE
        } else {
            tvAuxiliary!!.visibility = INVISIBLE
        }
    }

    fun setAuxiliaryGone(gone: Boolean) {
        if (gone) {
            tvAuxiliary!!.visibility = GONE
        } else {
            tvAuxiliary!!.visibility = VISIBLE
        }
    }

    fun setBottomDivider(visible: Boolean) {
        viewDivider!!.visibility =
            if (visible) VISIBLE else INVISIBLE
    }

    companion object {
        private const val CHILD_HEIGHT = 60
    }
}
