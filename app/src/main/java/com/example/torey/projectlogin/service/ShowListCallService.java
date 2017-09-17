package com.example.torey.projectlogin.service;

import com.example.torey.projectlogin.model.UserDetailList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;


public interface ShowListCallService {
    @GET("select.php")
    Call<UserDetailList> getUserDetail();

}
