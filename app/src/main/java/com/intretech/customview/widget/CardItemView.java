package com.intretech.customview.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.intretech.customview.R;

/**
 * @PackageName:com.intretech.customview.widget
 * @DESC:
 * @author: YQ16685 Chanrey Lee
 * @date 2022/1/19 - 18:46
 **/
public class CardItemView extends ConstraintLayout {

    private static final int CHILD_HEIGHT = 60;

    private Context context;
    private ImageView ivIcon;
    private TextView tvTitle;
    private TextView tvAuxiliary;
    private View viewDivider;


     private void init(Context context) {
         this.context = context;
     }

    public CardItemView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public CardItemView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
        @SuppressLint({"CustomViewStyleable", "Recycle"})
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CardItemView);

        int itemIcon = ta.getResourceId(R.styleable.CardItemView_icon, R.drawable.dot_solid_transparency);
        String title = ta.getString(R.styleable.CardItemView_itemTitle);
        String auxiliary = ta.getString(R.styleable.CardItemView_auxiliary);
        int auxiliaryColor = ta.getColor(R.styleable.CardItemView_auxiliaryColor, getResources().getColor(R.color.gray_9B9B9B));
        boolean isDivider = ta.getBoolean(R.styleable.CardItemView_isDivider, false);

        View view = LayoutInflater.from(context).inflate(R.layout.item_card_view, this, true);
        ivIcon = view.findViewById(R.id.iv_item_card_icon);
        tvTitle = view.findViewById(R.id.tv_item_card_title);
        tvAuxiliary = view.findViewById(R.id.tv_item_card_auxiliary);
        viewDivider = view.findViewById(R.id.view_divider_item_card);

        ivIcon.setImageResource(itemIcon);
        tvTitle.setText(title);

        tvAuxiliary.setText(auxiliary);
        tvAuxiliary.setTextColor(auxiliaryColor);

        viewDivider.setVisibility(isDivider ? VISIBLE: INVISIBLE);

        ta.recycle();
    }

    public CardItemView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void setIvIcon(Drawable drawable) {
         ivIcon.setImageDrawable(drawable);
    }

    public void setIcon(@DrawableRes int drawable) {
         ivIcon.setImageResource(drawable);
    }

    public CardItemView setTitle(CharSequence text) {
        tvTitle.setText(text);
        return this;
    }

    public CardItemView setTitle(@StringRes int id) {
        return  setTitle(getResources().getString(id));
    }

    public void setAuxiliary(String auxiliary) {
         tvAuxiliary.setText(auxiliary);
    }

    public void setAuxiliaryColor(@ColorRes int color) {
         tvAuxiliary.setTextColor(context.getResources().getColor(color));
    }

    public void setAuxiliaryVisible(Boolean visible) {
         if (visible) {
             tvAuxiliary.setVisibility(VISIBLE);
         } else  {
             tvAuxiliary.setVisibility(INVISIBLE);
         }
    }

    public void setAuxiliaryGone(Boolean gone) {
         if (gone) {
             tvAuxiliary.setVisibility(GONE);
         } else {
             tvAuxiliary.setVisibility(VISIBLE);
         }
    }

    public void setBottomDivider(boolean visible) {
        viewDivider.setVisibility(visible ? VISIBLE: INVISIBLE);
    }
}
