package com.wazza.android.sdk.service;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class RestClient {
    //private static final String BASE_URL = "http://api.usewazza.com/";

    private static final String BASE_URL = "http://localhost:9000/api/";

    //should we also support sync calls?
    private static AsyncHttpClient client = new AsyncHttpClient();

    public static AsyncHttpClient getClient() {
        return client;
    }

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        try {
            return URLEncoder.encode(BASE_URL + relativeUrl, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
