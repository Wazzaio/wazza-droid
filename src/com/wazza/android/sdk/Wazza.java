package com.wazza.android.sdk;

import android.content.Context;

import com.wazza.android.sdk.service.ApplicationService;
import com.wazza.android.sdk.service.DeviceService;
import com.wazza.android.sdk.service.LocationService;
import com.wazza.android.sdk.service.UserService;
import com.wazza.android.sdk.service.Util;

public class Wazza{
	
	private static Wazza self;

	private ApplicationService app;
	private DeviceService device;
	private UserService user;
	private LocationService location;

	//debug
	public String getDevice(){
		return app.getApplicationName() + "\n" + device.getAndroidVersion() + "\n" + user.getUsername() + "\n" + location.getLocation();
	}
	
	//initializer and constructor
	public static Wazza init(Context context){
		//poor's man singleton
		if(self == null)
			self = new Wazza(context);
		return self;
	}
	
	private Wazza(Context context){
		System.out.println(Util.getCurrentTime());
		app = new ApplicationService(context);
		device = new DeviceService(context);
		user = new UserService(context);
		location = new LocationService(context);
	}

}
