package com.example.ourcart.Model;

import com.google.gson.annotations.SerializedName;

public class Datas {
   @SerializedName("prod_id")
   private String prod_id;
    @SerializedName("title")
    private String title;
    @SerializedName("image")
    private String image;
    @SerializedName("selling_price")
    private Integer selling_price;
    @SerializedName("warranty")
    private String warranty;

    public Datas(String prod_id, String title, String image, Integer selling_price, String warranty) {
        this.prod_id = prod_id;
        this.title = title;
        this.image = image;
        this.selling_price = selling_price;
        this.warranty = warranty;
    }

    public String getProd_id() {
        return prod_id;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public Integer getSelling_price() {
        return selling_price;
    }

    public String getWarranty() {
        return warranty;
    }
}
