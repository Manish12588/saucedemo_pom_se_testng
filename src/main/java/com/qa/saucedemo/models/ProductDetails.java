package com.qa.saucedemo.models;

public class ProductDetails {

    private final String productName;
    private final String description;
    private final String price;
    private final String quantity;

    public ProductDetails(String productName,
                          String description,
                          String price,
                          String quantity) {
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public String getQuantity() {
        return quantity;
    }
}
