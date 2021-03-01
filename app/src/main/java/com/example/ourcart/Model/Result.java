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

    public Result(Boolean error, String message, List<Datas> datas, Details details) {
        this.error = error;
        this.message = message;
        this.datas = datas;
        this.details = details;
    }

    public Boolean getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public List<Datas> getDatas() {
        return datas;
    }

    public Details getDetails() {
        return details;
    }
}
