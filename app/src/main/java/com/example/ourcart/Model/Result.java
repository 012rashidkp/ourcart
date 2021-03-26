package com.example.ourcart.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {
@SerializedName("error")
private Boolean error;
@SerializedName("message")
private String message;
@SerializedName("datas")
private List<Datas>datas;
@SerializedName("data")
private Details details;
@SerializedName("banners")
private List<BannerItem>bannerItems;
@SerializedName("categories")
private List<Categories>categories;

    public Result(Boolean error, String message, List<Datas> datas, Details details, List<BannerItem> bannerItems, List<Categories> categories) {
        this.error = error;
        this.message = message;
        this.datas = datas;
        this.details = details;
        this.bannerItems = bannerItems;
        this.categories = categories;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Datas> getDatas() {
        return datas;
    }

    public void setDatas(List<Datas> datas) {
        this.datas = datas;
    }

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

    public List<BannerItem> getBannerItems() {
        return bannerItems;
    }

    public void setBannerItems(List<BannerItem> bannerItems) {
        this.bannerItems = bannerItems;
    }

    public List<Categories> getCategories() {
        return categories;
    }

    public void setCategories(List<Categories> categories) {
        this.categories = categories;
    }
}
