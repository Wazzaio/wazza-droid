package com.wazza.android.sdk.service;

import android.content.Context;

public class ApplicationService {
	
	Context context;

	public static String getApplicationName(Context context) {
	    int stringId = context.getApplicationInfo().labelRes;
	    return context.getString(stringId);
	}

	public ApplicationService(Context context) {
		this.context = context;
	}
}
