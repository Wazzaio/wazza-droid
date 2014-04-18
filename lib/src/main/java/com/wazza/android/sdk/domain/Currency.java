package com.wazza.android.sdk.domain;

/**
 * Created by duarte on 4/17/14.
 */
public class Currency {

    private int type;
    private double value;
    private String currency;


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }


}
