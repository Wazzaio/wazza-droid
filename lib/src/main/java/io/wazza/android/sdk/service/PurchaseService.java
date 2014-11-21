package io.wazza.android.sdk.service;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
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
            public void onSuccess(JSONArray jsonArray) {
                //tba
            }
        });
    }
}
