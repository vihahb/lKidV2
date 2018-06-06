package com.sproject.ikidz;

import android.app.Application;
import android.content.Context;
import android.util.Log;

/**
 * Created by User: Vi-PC
 * Author: Vũ Hà Vi
 * Date: 6/6/2018
 * Time: 10:11 PM
 * Email: vihahb@gmail.com
 */
public class iKidApplications extends Application{

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static void log(String title, String content) {
        Log.e(title, content);
    }
}
