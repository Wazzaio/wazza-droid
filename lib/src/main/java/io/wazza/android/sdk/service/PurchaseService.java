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

import org.json.*;
import com.loopj.android.http.*;
import org.apache.http.Header;
import io.wazza.android.sdk.domain.Item;
import io.wazza.android.sdk.domain.Purchase;

import org.json.JSONArray;

public class PurchaseService {

    private static final String ENDPOINT_PURCHASE = "purchase";

    public void makePurchase(Item item) {

        Purchase purchase = new Purchase();
        purchase.setFromItem(item);

        RequestParams params = RestClient.constructRequestHeader();
        params.put("payload", purchase);

        RestClient.post(ENDPOINT_PURCHASE, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
            }
        });
    }
}
