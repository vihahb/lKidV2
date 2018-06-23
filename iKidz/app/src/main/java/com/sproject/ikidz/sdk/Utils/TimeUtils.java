package com.sproject.ikidz.sdk.Utils;

import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;

import com.sproject.ikidz.sdk.callback.DatePickerListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

    public String getTimeFormatDDMMYY(String time) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
//        Date date;
//        try {
//            date = dateFormat.parse(time);
//        } catch (ParseException e) {
//            e.printStackTrace();
//            date = null;
//        }
        SimpleDateFormat dt1 = new SimpleDateFormat("dd/mm/yyyy");
        return dt1.format(time);
    }

    public static String formatDate(String date, String initDateFormat, String endDateFormat) {

        Date initDate = null;
        try {
            initDate = new SimpleDateFormat(initDateFormat).parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            initDate = null;
        }
        if (initDate!=null) {
            SimpleDateFormat formatter = new SimpleDateFormat(endDateFormat);
            String parsedDate = formatter.format(initDate);

            return parsedDate;
        } else {
            return  "";
        }
    }
}