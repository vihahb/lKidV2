package com.snq.ikidz.teacher.sdk.Utils;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.snq.ikidz.teacher.R;
import com.snq.ikidz.teacher.sdk.callback.DatePickerListener;
import com.snq.ikidz.teacher.sdk.callback.TimePickerListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TimeUtils {

    private static TimeUtils instance;

    public synchronized static TimeUtils getInstance() {
        if (instance == null)
            instance = new TimeUtils();
        return instance;
    }

    public static String getCurrentTimeFormat(String initDateFormat) {
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        Date date = new Date(calendar.getTimeInMillis());
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat(initDateFormat);
        return sdf.format(date);
    }

    public static String getCurrentTimeFormatHigh1(String initDateFormat) {
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR) + 1);
        Date date = new Date(calendar.getTimeInMillis());
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat(initDateFormat);
        return sdf.format(date);
    }

    public static String getFirstDayMonthFormat(String initDateFormat) {
        Calendar c = Calendar.getInstance();   // this takes current date
        c.set(Calendar.DAY_OF_MONTH, 1);
        Date date = new Date(c.getTimeInMillis());
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat(initDateFormat);
        return sdf.format(date);
    }

    public static String getLastDayMonthFormat(String initDateFormat) {
        Calendar c = Calendar.getInstance();   // this takes current date
        c.set(Calendar.DATE, c.getActualMaximum(Calendar.DATE));
        Date date = new Date(c.getTimeInMillis());
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat(initDateFormat);
        return sdf.format(date);
    }

    public static String formatDate(String date, String initDateFormat, String endDateFormat) {

        Date initDate = null;
        try {
            initDate = new SimpleDateFormat(initDateFormat).parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            initDate = null;
        }
        if (initDate != null) {
            SimpleDateFormat formatter = new SimpleDateFormat(endDateFormat);
            String parsedDate = formatter.format(initDate);

            return parsedDate;
        } else {
            return "";
        }
    }

    public long convertDateTimeToLong(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date mDate = null;

        try {
            mDate = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (mDate != null)
            return mDate.getTime() / 1000;
        else
            return 0;
    }

    public long convertTimeToLong(String time) {
        String[] mTime = time.split(":");

        int hour = Integer.parseInt(mTime[0]);
        int minute = Integer.parseInt(mTime[1]);

        Calendar calendar = Calendar.getInstance();
        calendar.set(2017, 1, 1, hour, minute);

        return (calendar.getTimeInMillis() / 1000);
    }

    public String getTimeFormMilisecond(long time) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm", Locale.getDefault());
        Date date = new Date(time);
        return format.format(date);
    }

    public String convertTimeInMilisecondToDate(long time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date(time);
        return dateFormat.format(date);
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

    public void showTimePickerDialog(Context context, final TimePickerListener timePickerListener) {
        final Calendar calendar = Calendar.getInstance();
        new TimePickerDialog(context, R.style.TimePicker, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(calendar.getTimeInMillis());
                calendar.set(Calendar.HOUR_OF_DAY, i);
                calendar.set(Calendar.MINUTE, i1);
                timePickerListener.onTimeSelected(getTimeFormMilisecond(calendar.getTimeInMillis()), convertTimeInMilisecondToDate(calendar.getTimeInMillis()));
            }
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false).show();
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
}