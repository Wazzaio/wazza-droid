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

import android.util.Log;

import com.loopj.android.http.JsonHttpResponseHandler;
import io.wazza.android.sdk.Wazza;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

public class ItemService {

    private PersistenceService persist;

    //server endpoints
    private static final String ENDPOINT_ITEM_LIST = "items/";
    private static final String ENDPOINT_ITEM_DETAILED_LIST = "items/details/";
    private static final String ENDPOINT_DETAILS = "item/";
    private static final String ENDPOINT_RECOMMENDATION = "rec/user/items/";


    private String constructURL(String URL) {
        return URL + "/";
    }

    public void getRecommendedItems(int limit) {
        String url = constructURL(ENDPOINT_RECOMMENDATION) + "/" + limit;
        RestClient.get(url, RestClient.constructRequestHeader(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray results) {
                persist.storeRecommendedItems(results);
            }

            @Override
            public void onFailure(int statusCode,
                                  org.apache.http.Header[] headers,
                                  java.lang.Throwable e,
                                  org.json.JSONObject errorResponse) {
                Log.e("WazzaSDK", "oops.. something went wrong");
            }
        });

    }

    public void fetchItems() {
        RestClient.get(constructURL(ENDPOINT_ITEM_DETAILED_LIST), RestClient.constructRequestHeader(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray results) {
                persist.addItem(results);
                persist.commitItems();
            }

            @Override
            public void onFailure(int statusCode,
                                  org.apache.http.Header[] headers,
                                  java.lang.Throwable e,
                                  org.json.JSONObject errorResponse) {
                Log.e("WazzaSDK", "oops.. something went wrong");
            }
        });
    }

    public ItemService(PersistenceService persist) {
        this.persist = persist;
    }
}
