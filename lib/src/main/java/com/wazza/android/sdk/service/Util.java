package com.wazza.android.sdk.service;

import android.text.format.Time;

import com.loopj.android.http.RequestParams;
import com.wazza.android.sdk.Wazza;

import java.util.Calendar;
import java.util.Date;

public class Util {


    protected static RequestParams constructRequestHeader(){
        RequestParams params = new RequestParams();
        params.put("AppName", Wazza.appName);
        //todo: add security headers

        return params;
    }

	public static String getCurrentTime(){
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
