package com.wazza.android.sdk.service;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.wazza.android.sdk.domain.Item;
import com.wazza.android.sdk.domain.Purchase;

import org.json.JSONArray;

public class PurchaseService {

    private static final String ENDPOINT_PURCHASE = "purchase";

    public void makePurchase(Item item) {

        Purchase purchase = new Purchase();
        purchase.setFromItem(item);

        RequestParams params = Util.constructRequestHeader();
        params.put("payload", purchase);

        RestClient.post(ENDPOINT_PURCHASE, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(JSONArray jsonArray) {
                //tba
            }
        });
    }
}
