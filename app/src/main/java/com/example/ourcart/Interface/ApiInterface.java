package com.example.ourcart.Interface;

import com.example.ourcart.Model.AuthResponse;
import com.example.ourcart.Model.Productimages;
import com.example.ourcart.Model.Result;
import com.example.ourcart.Urls;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET(Urls.Banners_Ads)
    Call<Result> getbanners();
    @GET(Urls.Categories)
    Call<Result>getcategories();

    @GET(Urls.List_products)
    Call<Result> getdatas();

    @GET(Urls.Products_pagination)
    Call<Result>getproducts(@Query("page")int page);

    @GET("single-prod-images/")
    Call<List<Productimages>>getimages(@Query("product_id")String product_id);

   @GET(Urls.Product_details)
    Call<Result>getDetails(@Query("id")String id);
   @FormUrlEncoded
   @POST(Urls.User_Register)
    Call<AuthResponse>CreateUser(@Field("email") String email,@Field("username") String username,@Field("phone")
                                 String phone,@Field("city")String city,@Field("password")String password);

}
