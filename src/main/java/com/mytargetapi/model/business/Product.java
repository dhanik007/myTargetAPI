package com.mytargetapi.model.business;

/**
 * This class represents the json data object containing productID,productName
 * and product price information.
 */
public class Product {
    private Long productID;
    private String productName;
    private PriceInfo priceInfo;

    public Long getProductID() {
        return productID;
    }

    public void setProductID(long productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public PriceInfo getPriceInfo() {
        return priceInfo;
    }

    public void setPriceInfo(PriceInfo priceInfo) {
        this.priceInfo = priceInfo;
    }

    public Product() {
    }

    public Product(Long productID, String productName, PriceInfo priceInfo) {
        super();
        this.productID = productID;
        this.productName = productName;
        this.priceInfo = priceInfo;

    }
}
