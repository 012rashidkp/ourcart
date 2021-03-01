package com.example.ourcart.Model;

import com.google.gson.annotations.SerializedName;

public class Details {
    @SerializedName("prod_id")
    private String prod_id;
    @SerializedName("title")
    private String title;
    @SerializedName("image")
    private String image;
    @SerializedName("marked_price")
    private Integer marked_price;
    @SerializedName("selling_price")
    private Integer selling_price;
    @SerializedName("description")
    private String description;
    @SerializedName("warranty")
    private String warranty;
    @SerializedName("return_policy")
    private String return_policy;

    public Details(String prod_id, String title, String image, Integer marked_price, Integer selling_price, String description, String warranty, String return_policy) {
        this.prod_id = prod_id;
        this.title = title;
        this.image = image;
        this.marked_price = marked_price;
        this.selling_price = selling_price;
        this.description = description;
        this.warranty = warranty;
        this.return_policy = return_policy;
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

    public Integer getMarked_price() {
        return marked_price;
    }

    public Integer getSelling_price() {
        return selling_price;
    }

    public String getDescription() {
        return description;
    }

    public String getWarranty() {
        return warranty;
    }

    public String getReturn_policy() {
        return return_policy;
    }
}
