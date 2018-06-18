package com.sproject.ikidz.sdk.Utils;

import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;

import com.sproject.ikidz.sdk.callback.DatePickerListener;

import java.util.Calendar;

public class TimeUtils {

    private static TimeUtils instance;

    public synchronized static TimeUtils getInstance() {
        if (instance == null)
            instance = new TimeUtils();
        return instance;
    }


    public void showDatePickerDialog(Context context, final DatePickerListener listener) {
        Calendar calendar = Calendar.getInstance();

        new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                listener.onSelected(year, month, dayOfMonth);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }
}
