/*
 * wazza-droid
 * https://github.com/Wazzaio/wazza-droid
 * Copyright (C) 2013-2015  Duarte Barbosa, João Vazão Vasques
 *
 *    This program is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation, either version 3 of the License, or
 *    (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package io.wazza.android.sdk.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;

import com.google.gson.Gson;
import io.wazza.android.sdk.domain.Application;
import io.wazza.android.sdk.domain.Device;
import io.wazza.android.sdk.domain.Item;
import io.wazza.android.sdk.domain.User;
import io.wazza.android.sdk.service.domain.ApplicationService;
import io.wazza.android.sdk.service.domain.DeviceService;
import io.wazza.android.sdk.service.domain.LocationService;
import io.wazza.android.sdk.service.domain.UserService;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Hashtable;

public class PersistenceService {

    private static Context context;

    private Application app;

    private Device device;
    private User user;
    private Location location;

    private String PREFS_NAME = "WazzaSDK";
    private String APP_KEY = "application";
    private String DEVICE_KEY = "device";
    private String USER_KEY = "user";
    private String LOCATION_KEY = "location";
    private String ITEMS_KEY = "items";

    SharedPreferences preferencesReader;

    public Device getDevice() {
        return device;
    }

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
        preferencesReader = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        init();
    }

}
