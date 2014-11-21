package io.wazza.android.sdk;

import android.content.Context;
import android.util.Log;

import io.wazza.android.sdk.service.ItemService;
import io.wazza.android.sdk.service.PersistenceService;
import io.wazza.android.sdk.service.PurchaseService;
import io.wazza.android.sdk.service.SessionService;
import io.wazza.android.sdk.service.Util;

public class Wazza {

    private static Wazza self;

    public final String key;
    private final PersistenceService persist;
    private final PurchaseService purchase;
    private final ItemService item;

    public static String username;

    private void sessionOpen() {
        SessionService.initSession(persist); //or resume if it's still inside of the timeframe
        flush();

        item.fetchItems();
    }

    public void sessionClose() {
        flush();
        SessionService.closeSession();
    }

    public static void flush() {

    }

    //track special event/attribute. tbd.
    public static void track() {

    }

    //initializer and constructor
    public static Wazza init(Context context, String secret) {
        //poor's man singleton
        if (self == null)
            self = new Wazza(context, secret);

        self.sessionOpen();

        return self;
    }

    public static Wazza destroy(Context context, String secret) {
        //poor's man singleton
        if (self == null)
            self = new Wazza(context, secret);

        self.sessionClose();

        return self;
    }

    private Wazza(Context context, String secret) {
        Log.v("WazzaSDK", "Started at: " + Util.getCurrentTime());

        this.key = secret;
        this.persist = new PersistenceService(context);
        //this.username = persist.getUser();
        this.item = new ItemService(persist);
        this.purchase = new PurchaseService();
    }

}
