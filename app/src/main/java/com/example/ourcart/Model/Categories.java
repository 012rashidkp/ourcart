package com.example.ourcart.Model;

import com.google.gson.annotations.SerializedName;

public class Categories {
    @SerializedName("cat_id")
    private String cat_id;
    @SerializedName("cat_name")
    private String cat_name;
    @SerializedName("cat_image")
    private String cat_image;

    public Categories(String cat_id, String cat_name, String cat_image) {
        this.cat_id = cat_id;
        this.cat_name = cat_name;
        this.cat_image = cat_image;
    }

    public String getCat_id() {
        return cat_id;
    }

    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public String getCat_image() {
        return cat_image;
    }

    public void setCat_image(String cat_image) {
        this.cat_image = cat_image;
    }
}
