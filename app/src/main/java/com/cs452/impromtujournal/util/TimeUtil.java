package com.cs452.impromtujournal.util;

import android.icu.text.SimpleDateFormat;
import android.icu.util.TimeZone;

import java.util.Date;
import java.util.Locale;

public class TimeUtil {
    public static String nowIsoTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return dateFormat.format(new Date());
    }
}
