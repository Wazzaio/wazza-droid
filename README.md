wazza-droid
===========

Android SDK for Wazza

How to use:
1) Add the library to your project settings;
2) Add missing permissions
3) Add "import com.wazza.android.sdk.Wazza;" to your relevant class
4) Call "Wazza.init(getBaseContext());" first thing on your code.

Permissions:
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
    <uses-permission android:name="android.permission.ACCESS_LOCATION_EXTRA_COMMANDS" />
    <uses-permission android:name="android.permission.MOCK_LOCATION" />
    <uses-permission android:name="android.permission.GET_ACCOUNTS" />
    <uses-permission android:name="android.permission.READ_PHONE_STATE" />
    
