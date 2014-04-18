package com.wazza.android.sdk.service;

import com.wazza.android.sdk.domain.Currency;
import com.wazza.android.sdk.domain.Item;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by duarte on 4/18/14.
 */
public class PersistenceService {

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
}
