package com.wazza.android.sdk;

import android.content.Context;

import com.wazza.android.sdk.service.ApplicationService;
import com.wazza.android.sdk.service.CommunicationService;
import com.wazza.android.sdk.service.DeviceService;
import com.wazza.android.sdk.service.LocationService;
import com.wazza.android.sdk.service.PersistenceService;
import com.wazza.android.sdk.service.UserService;
import com.wazza.android.sdk.service.Util;

import java.util.Calendar;
import java.util.Date;

public class Wazza{
	
	private static Wazza self;

    private final Date sessionStart;
    private final String secret;

	private ApplicationService app;
	private DeviceService device;
	private UserService user;
	private LocationService location;
    private CommunicationService comm;
    private PersistenceService persist;

	//debug
	public String getDevice(){
		return app.getApplicationName() + "\n" + device.getAndroidVersion() + "\n" + user.getUsername() + "\n" + location.getLocation();
	}

	//initializer and constructor
	public static Wazza init(Context context, String secret){
		//poor's man singleton
		if(self == null)
			self = new Wazza(context, secret);

        //all the required setup
        //bring session up
        //retrieve items
        self.comm.fetchItems();

		return self;
	}
	
	private Wazza(Context context, String secret){
		System.out.println(Util.getCurrentTime());
        this.secret = secret;
        this.sessionStart = Calendar.getInstance().getTime();

		app = new ApplicationService(context);
		device = new DeviceService(context);
		user = new UserService(context);
		location = new LocationService(context);
        comm = new CommunicationService(context, persist);

        Util.appName = app.getApplicationName();
        Util.username = user.getUsername();
	}

}
