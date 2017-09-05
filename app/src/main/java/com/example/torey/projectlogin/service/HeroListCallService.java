package com.example.torey.projectlogin.service;

import com.example.torey.projectlogin.model.HeroList;
import com.example.torey.projectlogin.model.Login;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface HeroListCallService {
    @GET("product_select.php")
    Call<HeroList> getHeroList();

}
