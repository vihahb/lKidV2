package com.sproject.ikidz.sdk.Utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;

import com.sproject.ikidz.iKidApplications;

/**
 * Created by vivu on 12/8/17
 * xtel.vn
 */

public class DisplayUtils {

    public static int dpToPx(int dp) {
        DisplayMetrics displayMetrics = iKidApplications.context.getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.scaledDensity / DisplayMetrics.DENSITY_MEDIUM));
    }

    public static int DpToPx(int dp, Context context) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp *scale + 0.5f);
    }

    public static int pxToDp(int px) {
        DisplayMetrics displayMetrics = iKidApplications.context.getResources().getDisplayMetrics();
        return Math.round(px / (displayMetrics.scaledDensity / DisplayMetrics.DENSITY_MEDIUM));
    }


    public static int convertDpToPixels(float dp, Context context) {
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
        return px;
    }

    public static int convertSpToPixels(float sp, Context context) {
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics());
        return px;
    }

    public static int convertDpToSp(float dp, Context context) {
        return (int) (convertDpToPixels(dp, context) / (float) convertSpToPixels(dp, context));
    }
}