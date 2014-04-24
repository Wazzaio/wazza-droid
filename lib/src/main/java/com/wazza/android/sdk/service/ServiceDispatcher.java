package com.wazza.android.sdk.service;


import android.content.Context;

public class ServiceDispatcher {

    private Context context;

    private ApplicationService app;
    private DeviceService device;
    private UserService user;
    private LocationService location;
    private CommunicationService comm;
    private PersistenceService persist;


    public ServiceDispatcher(Context context){
        this.context = context;

        persist = new PersistenceService(context);

    }
}
