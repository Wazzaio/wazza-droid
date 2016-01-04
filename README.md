wazza-droid
===========

Android SDK for Wazza

How to use:
    1. Add the library to your project settings;
    2. Add missing permissions
    3. Add "import com.wazza.android.sdk.Wazza;" to your relevant class
    4. Call "Wazza.init(getBaseContext());" first thing on your code.

Permissions:
* android.permission.ACCESS_FINE_LOCATION
* android.permission.ACCESS_COARSE_LOCATION
* android.permission.ACCESS_LOCATION_EXTRA_COMMANDS
* android.permission.MOCK_LOCATION
* android.permission.GET_ACCOUNTS
* android.permission.READ_PHONE_STATE
    
----
Caution:
 * On device querying, the "osName" property will read instead a UUID of said phone. There's no point in retrieving, it's an Android OS.


## License
All Wazza code is licensed under [GNU GPL v3](/LICENSE) or later. Copyright belongs to the [contributors listed on GitHub](https://github.com/Wazzaio/wazza-droid/graphs/contributors).
