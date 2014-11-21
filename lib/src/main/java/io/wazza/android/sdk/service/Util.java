package io.wazza.android.sdk.service;

import android.text.format.Time;

public class Util {

    public static String getCurrentTime() {
        Time now = new Time();
        now.setToNow();
        String seconds = now.format("%k:%M:%S");
        return seconds;
    }


    public static String capitalize(String s) {
        if (s == null || s.length() == 0)
            return "";

        char first = s.charAt(0);

        if (Character.isUpperCase(first))
            return s;
        else
            return Character.toUpperCase(first) + s.substring(1);
    }

}
