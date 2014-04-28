package com.wazza.android.sdk.service.domain;

import android.content.Context;

import com.wazza.android.sdk.domain.Application;

public class ApplicationService {
	
	private Context context;

    private Application application;

    public Application getApplication() {
        return application;
    }

    public void setApplicationName() {
        /*
        final PackageManager pm = getApplicationContext().getPackageManager();
        ApplicationInfo ai;
        try {
            ai = pm.getApplicationInfo( this.getPackageName(), 0);
        } catch (final NameNotFoundException e) {
            ai = null;
        }
        final String applicationName = (String) (ai != null ? pm.getApplicationLabel(ai) : "(unknown)");
        */
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

    public ApplicationService(Context context, Application application) {
        this.context = context;
        this.application = application;
    }
}
