package io.wazza.android.sdk;

import android.content.Context;
import android.util.Log;

import io.wazza.android.sdk.service.ItemService;
import io.wazza.android.sdk.service.PersistenceService;
import io.wazza.android.sdk.service.PurchaseService;
import io.wazza.android.sdk.service.RestClient;
import io.wazza.android.sdk.service.SessionService;
import io.wazza.android.sdk.service.Util;

public class Wazza {

    private static Wazza self;
    private static Context context; // better safe than sorry.

    public final String key;
    private final PersistenceService persist;
    private final PurchaseService purchase;
    //private final ItemService item;

    /*
    * private methods of the SDK below
    * */

    private void sessionOpen() {
        SessionService.resumeSession(context, persist); // resume if it's still inside of the timeframe
    }

    private void sessionClose() {
        SessionService.closeSession();
    }

    /*
    * public methods of the SDK below
    * */

    // request an on-the-fly flush of all the stored data
    public static void flush() {
    //TODO
    }

    // handles a purchase of an IAP
    public static void purchase(String g){
        Log.v("WazzaSDK", "Wazza SDK purchase");
        // fail silently if init() has not been called
        if (self == null) {
            Log.v("WazzaSDK", "You should init the SDK first");
            return;
        }
    }

    // track special event/attribute. tbd.
    public static void track() {

    }

    // initializer
    public static void init(Context context, String secret) {
        Log.v("WazzaSDK", "Wazza SDK init");
        //poor's man singleton
        if (self == null)
            self = new Wazza(context, secret);

        flush();
        self.sessionOpen();
        //item.fetchItems();
    }

    // destructor
    public static void destroy() {
        Log.v("WazzaSDK", "Wazza SDK destroy");
        // fail silently if init() has not been called
        if (self == null) {
            Log.v("WazzaSDK", "You should init the SDK first");
            return;
        }

        flush();
        self.sessionClose();
    }

    // constructor
    private Wazza(Context context, String secret) {
        Log.v("WazzaSDK", "Started at: " + Util.getCurrentTime());

        this.context = context;
        RestClient.key = this.key = secret;
        this.persist = new PersistenceService(context);
        //this.item = new ItemService(persist);
        this.purchase = new PurchaseService();

    }

}
