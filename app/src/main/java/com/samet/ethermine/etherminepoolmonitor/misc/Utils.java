package com.samet.ethermine.etherminepoolmonitor.misc;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by samet on 17.06.2017.
 */

public class Utils {

    private static Calendar calendar = Calendar.getInstance();
    private static DateFormat ethermineDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
    private static DateFormat appDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.ENGLISH);

    public static String simplifyDateString(String dateString) {
        Date date = null;
        try {
            date = ethermineDateFormat.parse(dateString.substring(0, dateString.length() - 5));
        } catch (ParseException e) {
            Log.e("ethermine pool monitor", "Failed to parse date", e);
        }
        return appDateFormat.format(date);
    }

    public static String longToDateString(long workerLastSubmitTime) {
        calendar.setTimeInMillis(workerLastSubmitTime * 1000L);
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.ENGLISH);
        return formatter.format(calendar.getTime());

    }
}
