package com.wazza.android.sdk;

import android.content.Context;
import com.wazza.android.sdk.service.ApplicationService;
import com.wazza.android.sdk.service.DeviceService;
import com.wazza.android.sdk.service.UserService;
import com.wazza.android.sdk.service.Util;

public class Wazza{

	private Context context;
	
	private ApplicationService app = new ApplicationService(context);
	private DeviceService device = new DeviceService(context);
	private UserService user = new UserService(context);
	
	public static void setup(){
		System.out.println(Util.getCurrentTime());
		
	}
	
	public String getDevice(){
		return device.getDeviceModel();
	}
	
	
	public Wazza(Context context){
		this.context = context;
		setup();
	}


}
