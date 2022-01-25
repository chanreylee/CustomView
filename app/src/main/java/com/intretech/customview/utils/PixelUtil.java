package com.intretech.customview.utils;

import android.content.res.Resources;
import android.util.TypedValue;

public class PixelUtil {
    public static int dp2px(float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics());
    }
}
