package com.intretech.customview.widget;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.intretech.customview.R;
import com.intretech.customview.utils.PixelUtil;


public class TitleBarView extends RelativeLayout {
    TextView rightOptionTv;
    private TextView rightOption2Tv;
    private TextView titleTv;
    private ImageView backImage;
    private ConstraintLayout rootLayout;
    private ImageView iconOptionRight, iconOptionLeft;
    public BackListener backListener;
    public interface BackListener {
        void onBackListener();
    }

    public void setOnBackListener(BackListener backListener) {
        this.backListener = backListener;
    }

    public TitleBarView(Context context) {
        super(context);
    }

    public TitleBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        @SuppressLint("CustomViewStyleable")
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.titleView);
        String title = ta.getString(R.styleable.titleView_title);
        int titleColor = ta.getColor(R.styleable.titleView_titleColor, getResources().getColor(R.color.black));
        int titleSize = ta.getInt(R.styleable.titleView_titleSize, 20);
        String rightText = ta.getString(R.styleable.titleView_rightText);
        int rightTextColor = ta.getColor(R.styleable.titleView_rightTextColor, getResources().getColor(R.color.black));
        String right2Text = ta.getString(R.styleable.titleView_right2Text);
        int right2TextColor = ta.getColor(R.styleable.titleView_right2TextColor, getResources().getColor(R.color.black));
        int backIcon = ta.getResourceId(R.styleable.titleView_backIcon, R.mipmap.icon_back_black);
        int backgroundColor = ta.getResourceId(R.styleable.titleView_background, R.color.white);
        int ivOption1 = ta.getResourceId(R.styleable.titleView_optionIconRight, R.drawable.icon_back_log_more);
        int ivOption2 = ta.getResourceId(R.styleable.titleView_optionIconLeft, R.drawable.icon_back_log_more);
        boolean showOptionIconRight = ta.getBoolean(R.styleable.titleView_showOptionIconRight, false);
        boolean showOptionIconLeft = ta.getBoolean(R.styleable.titleView_showOptionIconLeft, false);
        View view = LayoutInflater.from(context).inflate(R.layout.view_titlebar, this, true);

        backImage = view.findViewById(R.id.iv_back);
        titleTv = view.findViewById(R.id.tv_title);
        rightOptionTv = view.findViewById(R.id.tv_right_option);
        rightOption2Tv = view.findViewById(R.id.tv_right_option2);
        rootLayout = view.findViewById(R.id.include_title_layout);
        iconOptionRight = view.findViewById(R.id.iv_right_option1);
        iconOptionLeft = view.findViewById(R.id.iv_right_option2);

        backImage.setImageResource(backIcon);
        backImage.setOnClickListener(v -> {
            if (backListener == null) {
                ((Activity) context).finish();
            } else {
                backListener.onBackListener();
            }
        });

        titleTv.setText(title);
        titleTv.setTextColor(titleColor);
        titleTv.setTextSize(titleSize);

        rightOptionTv.setText(rightText);
        rightOptionTv.setTextColor(rightTextColor);
        //多语言国际化，右上角的文本可能过长，导致标题被覆盖，所以要动态设置标题长度
        if (!TextUtils.isEmpty(rightText) && !TextUtils.isEmpty(title)){
            if (title.length() > 10 && rightText.length() > 8) {
                titleTv.setTextSize(16);
                rightOptionTv.setTextSize(10);
                ViewGroup.LayoutParams layoutParams = titleTv.getLayoutParams();
                layoutParams.width = PixelUtil.dp2px(100);
                titleTv.setLayoutParams(layoutParams);
                titleTv.setEllipsize(TextUtils.TruncateAt.END);
            } else{
                titleTv.setTextSize(18);
                rightOptionTv.setTextSize(14);
            }
        }

        rightOption2Tv.setText(right2Text);
        rightOption2Tv.setTextColor(right2TextColor);

        iconOptionRight.setImageResource(ivOption1);
        iconOptionLeft.setImageResource(ivOption2);
        iconOptionRight.setVisibility(showOptionIconRight ? VISIBLE : GONE);
        iconOptionLeft.setVisibility(showOptionIconLeft ? VISIBLE : GONE);

        rootLayout.setBackgroundColor(getResources().getColor(backgroundColor));

        ta.recycle();
    }

    public void setRightTextClick(OnClickListener onClick) {
        rightOptionTv.setOnClickListener(onClick);
    }

    public void setRightText2Click(OnClickListener onClick) {
        rightOption2Tv.setOnClickListener(onClick);
    }

    public void setRightIconClick(OnClickListener onClick) {
        iconOptionRight.setOnClickListener(onClick);
    }

    public void setRightIcon2Click(OnClickListener onClick) {
        iconOptionLeft.setOnClickListener(onClick);
    }

    public void setTitle(String title) {
        titleTv.setText(title);
    }

    public void setRightOptionTv(String text) {
        rightOptionTv.setText(text);
    }

    public void setRightOption2Tv(String text) {
        rightOption2Tv.setText(text);
    }

    public void setRightOption2TvVisibility(boolean isShow) {
        rightOption2Tv.setVisibility(isShow ? VISIBLE : GONE);
    }

    public void setBackImg(int img) {
        backImage.setImageResource(img);
    }

    public void setBackgroud(int color) {
        rootLayout.setBackgroundColor(color);
    }

    public void setTitleColor(int color) {
        titleTv.setTextColor(getResources().getColor(color));
    }


    public TextView getRightOptionTv() {
        return rightOptionTv;
    }

    public TextView getTitleTv() {
        return titleTv;
    }

    public ImageView getBackImage() {
        return backImage;
    }

}
