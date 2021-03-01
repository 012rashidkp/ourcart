package com.example.ourcart.Model;

import android.media.Image;

public class SliderItem {

    private int id;
    private String ads_name;
    private int ads_image;

    public SliderItem(int id, String ads_name, int ads_image) {
        this.id = id;
        this.ads_name = ads_name;
        this.ads_image = ads_image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAds_name() {
        return ads_name;
    }

    public void setAds_name(String ads_name) {
        this.ads_name = ads_name;
    }

    public int getAds_image() {
        return ads_image;
    }

    public void setAds_image(int ads_image) {
        this.ads_image = ads_image;
    }
}
