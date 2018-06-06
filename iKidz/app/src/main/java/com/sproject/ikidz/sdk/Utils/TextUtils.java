package com.sproject.ikidz.sdk.Utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * Created by User: Vi-PC
 * Author: Vũ Hà Vi
 * Date: 6/6/2018
 * Time: 9:59 PM
 * Email: vihahb@gmail.com
 */
public class TextUtils {

    public static boolean isEmpty(String text) {
        return text == null || text.trim().length() == 0;
    }

    public static boolean checkPass(String password) {
        return !(password.length() < 6 || password.length() > 20 || password.contains(" "));
    }

    /**
     * Bỏ dấu tiếng việt
     *
     * @param s tiếng Việt có dấu
     * @return tiếng Việt không dấu
     */
    public static String unicodeToKoDau(String s) {
        if (android.text.TextUtils.isEmpty(s)) {
            return "";
        }

        String nfdNormalizedString = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");

        return pattern.matcher(nfdNormalizedString).replaceAll("").replaceAll("\u0111", "d").replaceAll("\u0110", "D");
    }

    public static String unicodeToKoDauLowerCase(String text) {
        if (android.text.TextUtils.isEmpty(text)) {
            return "";
        }

        String nfdNormalizedString = Normalizer.normalize(text.toLowerCase(), Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");

        return pattern.matcher(nfdNormalizedString).replaceAll("").replaceAll("\u0111", "d").replaceAll("\u0110", "D");
    }

    public static String fomatMoney(float value) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator('.');
        symbols.setGroupingSeparator('.');
        DecimalFormat fomat = new DecimalFormat("###,###,###,###", symbols);
        return fomat.format(value);
    }

    public static String fomatMoney(String value) {
        String clearString = value.replaceAll("[\\,,\\.]", "");
        float valueNumber = parserValueMonneyFomat(clearString);
        return fomatMoney(valueNumber);
    }

    public static float parserValueMonneyFomat(String value) {
        DecimalFormat format = new DecimalFormat("###,###,###,###");
        try {
            Number number = format.parse(value);
            return number.floatValue();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static String fomatFloatValue(float value) {
        DecimalFormat format = null;
        if (value == (int) value)
            format = new DecimalFormat("#");
        else
            format = new DecimalFormat("#.#");
        return format.format(value);
    }

    public static String getTypeFile(String filePatch) {
        String[] spilt = filePatch.split("\\.");
        String sss = spilt[spilt.length - 1];
        return "." + spilt[spilt.length - 1];
    }

    public static String comparingTime(long time_get) {
        if (time_get > 0){
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
            SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm");

            Date create_time = new Date(time_get);

            TimeUtil.TimeBetween tw = TimeUtil.getInstance().betweenTime(time_get, System.currentTimeMillis());

            String txt = null;
            long value = 0;
            if (tw.getDay() > 7) {
                txt = String.format("%s lúc %s", sdf.format(create_time), sdf1.format(create_time));
            } else if (tw.getDay() == 7) {
                txt = "1 tuần trước";
            } else if ((value = tw.getDay()) >= 1) {
                txt = value + " ngày trước";
            } else if ((value = tw.getHours()) > 0) {
                txt = value + " giờ trước";
            } else if ((value = tw.getMinute()) > 0) {
                txt = value + " phút trước";
            } else {
                txt = "vừa xong";
            }
            return txt;
        } else {
            return "";
        }
    }
}
