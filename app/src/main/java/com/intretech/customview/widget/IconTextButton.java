package com.intretech.customview.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.intretech.customview.R;

/**
 * 图标文字按钮
 *
 * @author: YQ16685 Chanrey Lee
 * @date 2022/6/15 - 15:52
 **/
public class IconTextButton extends ConstraintLayout {
    private ImageView ivIcon;
    private TextView tvText;

    public IconTextButton(@NonNull Context context) {
        super(context);
    }

    public IconTextButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initTaStyle(context, attrs);
    }

    public IconTextButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initTaStyle(context, attrs);
    }

    public IconTextButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initTaStyle(context, attrs);
    }

    @SuppressLint({"Recycle", "CustomViewStyleable"})
    private void initTaStyle(@NonNull Context context, @Nullable AttributeSet attrs){
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.iconTextButtonStyle);
        int taIcon  = ta.getResourceId(R.styleable.iconTextButtonStyle_itbIcon, R.drawable.dot_solid_transparency);
        String taText = ta.getString(R.styleable.iconTextButtonStyle_itbText);

        View view = LayoutInflater.from(context).inflate(R.layout.layout_icon_text_button, this, true);
        ivIcon = view.findViewById(R.id.itb_icon);
        tvText = view.findViewById(R.id.itb_text);

        ivIcon.setImageResource(taIcon);
        tvText.setText(taText);
        tvText.setTextColor(Color.WHITE);

        ta.recycle();
    }

    public void setIcon(@DrawableRes int iconId) {
        if (null != ivIcon) {
            ivIcon.setImageResource(iconId);
        }
    }

    public void setText(@StringRes int resId) {
        if (null != tvText) {
            tvText.setText(resId);
        }
    }

    public void setText(CharSequence text) {
        if (null != tvText) {
            tvText.setText(text);
        }
    }
}
