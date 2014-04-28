package com.wazza.android.sdk.service;

import java.util.Calendar;
import java.util.Date;

public class Session {

    //server endpoints
    private static final String ENDPOINT_AUTH = "auth";
    private static final String ENDPOINT_SESSION_UPDATE = "session";

    private final Date sessionStart;


    public static long getSessionLenght(Date originalTime){
        return Calendar.getInstance().getTime().getTime() - originalTime.getTime();
    }

    public void initSession(){

    }

    //TODO:
    public void resumeSession(){

    }

    public void closeSession(){

    }

    public Session(){
        this.sessionStart = Calendar.getInstance().getTime();
    }
}
