package com.example.ourcart.Model;

import com.google.gson.annotations.SerializedName;

public class Productimages {

    @SerializedName("id")
    private int id;
    @SerializedName("product_name")
    private String product_name;
    @SerializedName("image")
    private String image;

    public Productimages(int id, String product_name, String image) {
        this.id = id;
        this.product_name = product_name;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getImage() {
        return image;
    }
}
