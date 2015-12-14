package com.hongdingltd.hello.validation;

import com.hongdingltd.hello.validation.annotation.Price;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by jcchen on 15-12-1.
 */
public class Product {

    @NotEmpty
    private String productName;

    @Price
    private float price;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
