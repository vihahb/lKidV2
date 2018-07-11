package com.snq.ikidz.teacher.sdk.Utils;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by User: Vi-PC
 * Author: Vũ Hà Vi
 * Date: 6/6/2018
 * Time: 10:01 PM
 * Email: vihahb@gmail.com
 */
public class TimeUtil {
    private static final TimeUtil instance = new TimeUtil();

    private TimeUtil() {
    }

    public static final TimeUtil getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        System.out.println();
    }

    public TimeBetween betweenDate(Date from, Date to) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(to);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        to = cal.getTime();
        cal.setTime(from);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        from = cal.getTime();
        return new TimeBetween(to.getTime() - from.getTime());
    }

    public TimeBetween betweenTime(long from, long to) {
        return new TimeBetween(to - from);
    }

    public static class TimeBetween {
        private long timeMillisecond;

        protected TimeBetween(long timeMillisecond) {
            this.timeMillisecond = timeMillisecond;
        }

        protected TimeBetween() {
        }

        public Long getMilliSecond() {
            return timeMillisecond;
        }

        public Long getMinute() {
            return java.util.concurrent.TimeUnit.MILLISECONDS.toMinutes(timeMillisecond);
        }

        public Long getSecond() {
            return java.util.concurrent.TimeUnit.MILLISECONDS.toSeconds(timeMillisecond);
        }

        public Long getHours() {
            return java.util.concurrent.TimeUnit.MILLISECONDS.toHours(timeMillisecond);
        }

        public Long getDay() {
            return java.util.concurrent.TimeUnit.MILLISECONDS.toDays(timeMillisecond);
        }

        public Long getWeek() {
            return java.util.concurrent.TimeUnit.MILLISECONDS.toDays(timeMillisecond) / 7;
        }


        @Override
        public String toString() {
            return "TimeBetween{" +
                    "timeMillisecond=" + timeMillisecond +
                    '}';
        }
    }

}
