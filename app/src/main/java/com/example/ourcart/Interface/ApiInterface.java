package com.example.ourcart.Interface;

import com.example.ourcart.Model.Productimages;
import com.example.ourcart.Model.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("products/")
    Call<Result> getdatas();

    @GET("products-pagination/")
    Call<Result>getproducts(@Query("page")int page);

    @GET("single-prod-images/")
    Call<List<Productimages>>getimages(@Query("product_id")String product_id);

   @GET("product-detail/")
    Call<Result>getDetails(@Query("id")String id);
}
