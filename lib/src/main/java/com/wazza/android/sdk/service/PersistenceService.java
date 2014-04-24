package com.wazza.android.sdk.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;

import com.wazza.android.sdk.domain.Application;
import com.wazza.android.sdk.domain.Communication;
import com.wazza.android.sdk.domain.Currency;
import com.wazza.android.sdk.domain.Device;
import com.wazza.android.sdk.domain.Item;
import com.wazza.android.sdk.domain.User;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PersistenceService {

    private static Context context;

    private Application app;
    private Device device;
    private User user;
    private Location location;
    private Communication comm;
    private PersistenceService persist;


    private ApplicationService appService;
    private DeviceService deviceService;
    private UserService userService;
    private LocationService locationService;
    private CommunicationService commService;
    private PersistenceService persistService;


    String PREFS_NAME = "WazzaSDK";
    String APP_KEY = "application";
    String DEVICE_KEY = "device";
    String USER_KEY = "user";
    String LOCATION_KEY = "location";
    String COMM_KEY = "communication";

    public void init(){

        SharedPreferences preferencesReader = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        // Read the shared preference value and verify if it's the first time the app gets used.
        if(preferencesReader.contains(APP_KEY)){
            //restore everything

            // Create a new object from the serialized data with the same state
            app = Application.create(preferencesReader.getString(APP_KEY, null));
            device = Device.create(preferencesReader.getString(DEVICE_KEY, null));
            user = User.create(preferencesReader.getString(USER_KEY, null));
            //location: change to Location API from google (instead of own service). use LOCATION_KEY
            comm = Communication.create(preferencesReader.getString(COMM_KEY, null));

        }
        else {
            //create everything
            appService = new ApplicationService(context);
            deviceService = new DeviceService(context);
            userService = new UserService(context);
            locationService = new LocationService(context);
            commService = new CommunicationService(context, this);

            // Serialize the object and import it into SharedPreferences
            SharedPreferences.Editor editor = preferencesReader.edit();
            editor.putString(APP_KEY, appService.getApplication().serialize());
            editor.putString(DEVICE_KEY, deviceService.getDevice().serialize());
            editor.putString(USER_KEY, userService.getUser().serialize());
            //editor.putString(COMM_KEY, commService.getCommunication().serialize());
            editor.commit();

        }

    }

    public PersistenceService(Context context){
        this.context = context;
    }


    //deprecated.
   /*
    List<Item> itemList = new ArrayList<>();

    public Item createItemFromJson(JSONObject jsonObject) {

        try {
            Item item = new Item();

            item.setName(jsonObject.getString("name"));
            item.setId(jsonObject.getString("metadata"));
            item.setDescription(jsonObject.getString("description"));

            Currency currency = new Currency();
            currency.setType(jsonObject.getInt("typeOf"));
            currency.setValue(jsonObject.getDouble("value"));
            currency.setCurrency(jsonObject.getString("currency"));

            item.setCurrency(currency);

            itemList.add(item);

            return item;
        } catch (Exception e) {

        }

        return null;
    }
*/
}
