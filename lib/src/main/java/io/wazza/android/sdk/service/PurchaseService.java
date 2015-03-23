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
