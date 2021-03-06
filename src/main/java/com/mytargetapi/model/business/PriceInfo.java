package com.mytargetapi.model.business;

/**
 * This class represents the object containing price and currency of the
 * product.
 */
public class PriceInfo {
    private String currency;
    private Float price;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public PriceInfo() {
    }

    public PriceInfo(String currency, Float price) {
        super();
        this.currency = currency;
        this.price = price;
    }
}
