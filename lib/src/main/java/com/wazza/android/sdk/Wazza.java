package com.wazza.android.sdk;

import android.content.Context;
import android.util.Log;

import com.wazza.android.sdk.service.ItemService;
import com.wazza.android.sdk.service.PersistenceService;
import com.wazza.android.sdk.service.PurchaseService;
import com.wazza.android.sdk.service.SessionService;
import com.wazza.android.sdk.service.Util;

public class Wazza {

    private static Wazza self;

    private final String secret;
    private final PersistenceService persist;
    private final PurchaseService purchase;
    private final ItemService item;

    public static String appName;
    public static String companyName;
    public static String username;

    public void sessionOpen() {
        SessionService.initSession(persist); //or resume if it's still inside of the timeframe
        flush();

        item.fetchItems();
    }

    public void sessionClose() {
        flush();
        SessionService.closeSession();
    }

    public void flush() {

    }

    public void purchase(String g){}

    //track special event/attribute. tbd.
    public void track() {

    }

    //initializer and constructor
    public static Wazza init(Context context, String secret, String appName, String companyName) {
        //poor's man singleton
        if (self == null)
            self = new Wazza(context, secret, appName, companyName);
        return self;
    }

    private Wazza(Context context, String secret, String appName, String companyName) {
        Log.v("WazzaSDK", "Started at: " + Util.getCurrentTime());

        this.secret = secret;
        this.appName = appName; //app.getApplicationName();
        this.companyName = companyName;
        this.persist = new PersistenceService(context);
        this.username = persist.getUser();
        this.item = new ItemService(persist);
        this.purchase = new PurchaseService();
    }

}
