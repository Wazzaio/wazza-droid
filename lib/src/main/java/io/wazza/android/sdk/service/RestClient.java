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

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import io.wazza.android.sdk.Wazza;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class RestClient {
    public static String key;

    //private static final String BASE_URL = "http://www.wazza.com/api";
    private static final String BASE_URL = "http://192.168.1.74:9000/api/";

    //should we also support sync calls?
    private static AsyncHttpClient client = new AsyncHttpClient();

    public static AsyncHttpClient getClient() {
        return client;
    }


    protected static RequestParams constructRequestHeader() {
        RequestParams params = new RequestParams();
        params.put("SDK-TOKEN", key);
        //todo: add security headers

        return params;
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
