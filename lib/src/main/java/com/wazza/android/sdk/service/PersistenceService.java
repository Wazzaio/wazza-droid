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

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Hashtable;

public class PersistenceService {

    private static Context context;

    private Application app;
    private Device device;
    private User user;
    private Location location;

    String PREFS_NAME = "WazzaSDK";
    String APP_KEY = "application";
    String DEVICE_KEY = "device";
    String USER_KEY = "user";
    String LOCATION_KEY = "location";
    String ITEMS_KEY = "items";

    SharedPreferences preferencesReader = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

    /*
     * User related
     */

    public String getUser(){
        return user.getUsername();
    }

    /*
     * Item related
     */
    private Hashtable<String, Item> itemHashtable = new Hashtable<>();
    private ArrayList<String> recommendedItemList;

    public void storeRecommendedItems(JSONArray itemArray) {
        try {
            for (int i = 0; i < itemArray.length(); i++) {
                //TODO: strip json bling
                recommendedItemList.add(itemArray.getJSONObject(i).toString());
            }
        } catch (Exception e) {
            //tba
            e.printStackTrace();
        }
    }

    public void addItem(JSONArray itemArray) {
        try {
            for (int i = 0; i < itemArray.length(); i++) {
                Item item = Item.create(itemArray.getJSONObject(i).toString());
                itemHashtable.put(item.getId(), item);
            }
        } catch (Exception e) {
            //tba
            e.printStackTrace();
        }

    }

    public void commitItems() {
        SharedPreferences.Editor editor = preferencesReader.edit();
        Gson gson = new Gson();
        editor.putString(ITEMS_KEY, gson.toJson(itemHashtable));
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
            //TODO: location: change to Location API from google (instead of own service). use LOCATION_KEY [1]
            //TODO: create services for each of new (saved) objects if necessary

        } else {
            //create everything
            ApplicationService appService = new ApplicationService(context);
            DeviceService deviceService = new DeviceService(context);
            UserService userService = new UserService(context);
            LocationService locationService = new LocationService(context); // [1]

            app = appService.getApplication();
            device = deviceService.getDevice();
            user = userService.getUser();

            // Serialize the object and import it into SharedPreferences
            SharedPreferences.Editor editor = preferencesReader.edit();
            editor.putString(APP_KEY, app.serialize());
            editor.putString(DEVICE_KEY, device.serialize());
            editor.putString(USER_KEY, user.serialize());
            editor.commit();

        }

    }

    public PersistenceService(Context context) {
        this.context = context;
        init();
    }

}
