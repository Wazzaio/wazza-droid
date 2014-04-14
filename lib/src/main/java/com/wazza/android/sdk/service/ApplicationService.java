package com.wazza.android.sdk.service;

import android.content.Context;

import com.wazza.android.sdk.domain.Application;

public class ApplicationService {
	
	private Context context;
	private Application application;
	
	public void setApplicationName() {
	    int stringId = context.getApplicationInfo().labelRes;
	    application.setApplicationName(context.getString(stringId));
	}

	public String getApplicationName() {
	    return application.getApplicationName();
	}

	public ApplicationService(Context context) {
		this.context = context;
		application = new Application();
		setApplicationName();
	}
}
