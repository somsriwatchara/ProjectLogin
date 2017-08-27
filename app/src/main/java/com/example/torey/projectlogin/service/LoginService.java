package com.example.torey.projectlogin.service;

import com.example.torey.projectlogin.model.Login;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by TOREY on 8/20/2017.
 */

public interface LoginService {
    @FormUrlEncoded
    @POST("login_api.php")
    Call<Login> getLoginData(@Field("username") String usernameString,
                             @Field("password") String passwordString);
}
