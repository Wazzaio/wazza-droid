package com.wazza.android.sdk.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;

import com.google.gson.Gson;
import com.wazza.android.sdk.domain.Application;
import com.wazza.android.sdk.domain.Device;
import com.wazza.android.sdk.domain.Item;
import com.wazza.android.sdk.domain.User;
import com.wazza.android.sdk.service.domain.ApplicationService;
import com.wazza.android.sdk.service.domain.DeviceService;
import com.wazza.android.sdk.service.domain.LocationService;
import com.wazza.android.sdk.service.domain.UserService;

import java.util.List;

public class PersistenceService {

    private static Context context;

    private Application app;
    private Device device;
    private User user;
    private Location location;
    //private Communication comm;

    private ApplicationService appService;
    private DeviceService deviceService;
    private UserService userService;
    private LocationService locationService;
    //private CommunicationService commService;

    String PREFS_NAME = "WazzaSDK";
    String APP_KEY = "application";
    String DEVICE_KEY = "device";
    String USER_KEY = "user";
    String LOCATION_KEY = "location";
    //String COMM_KEY = "communication";
    String ITEMS_KEY = "items";

    SharedPreferences preferencesReader = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

    public void storeItem(List<Item> itemList) {
        SharedPreferences.Editor editor = preferencesReader.edit();
        Gson gson = new Gson();
        editor.putString(ITEMS_KEY, gson.toJson(itemList));
        editor.commit();
    }


    /*
     * Initialization methods
     */
    private void init() {

        // Read the shared preference value and verify if it's the first time the app gets used.
        if (preferencesReader.contains(APP_KEY)) {
            //restore everything

            // Create a new object from the serialized data with the same state
            // not strictly necessary though
            app = Application.create(preferencesReader.getString(APP_KEY, null));
            device = Device.create(preferencesReader.getString(DEVICE_KEY, null));
            user = User.create(preferencesReader.getString(USER_KEY, null));
            //TODO: location: change to Location API from google (instead of own service). use LOCATION_KEY

        } else {
            //create everything
            appService = new ApplicationService(context);
            deviceService = new DeviceService(context);
            userService = new UserService(context);
            locationService = new LocationService(context);

            // Serialize the object and import it into SharedPreferences
            SharedPreferences.Editor editor = preferencesReader.edit();
            editor.putString(APP_KEY, appService.getApplication().serialize());
            editor.putString(DEVICE_KEY, deviceService.getDevice().serialize());
            editor.putString(USER_KEY, userService.getUser().serialize());
            //editor.putString(COMM_KEY, commService.getCommunication().serialize());
            editor.commit();

        }

    }

    public PersistenceService(Context context) {
        this.context = context;
        init();
    }

}
