package com.snq.teacher.view.base;

import android.app.Activity;

/**
 * Author: Vũ Hà Vi
 * Date: 06/06/2018
 * Email: vihahb@gmail.com
 */

public interface IBasicActivity {
    void showLongToast(String message);

    void showProgressBar(boolean isTouchOutside, boolean isCancel, String message);

    void closeProgressBar();

    Activity getActivity();
}
