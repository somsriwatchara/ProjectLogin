package com.example.torey.projectlogin.service;

import com.example.torey.projectlogin.model.GenericStatus;
import com.example.torey.projectlogin.model.HeroList;
import com.example.torey.projectlogin.model.Login;
import com.example.torey.projectlogin.model.UserDetailList;
import com.example.torey.projectlogin.model.request.LoginRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by TOREY on 8/20/2017.
 */

public interface LoginService {
    @FormUrlEncoded
    @POST("login.php")
    Call<Login> getLoginData(@Field("username") String usernameString,
                             @Field("password") String passwordString);

    @FormUrlEncoded
    @POST("update.php")
    Call<UserDetailList> editMemberName(@Field("member_id") String memberId,
                                       @Field("member_name") String updateName,
                                       @Field("member_facebook") String updateFacebook,
                                       @Field("member_ig") String updateIG,
                                       @Field("member_line") String updateLine,
                                       @Field("member_page") String updatePage,
                                       @Field("member_province") String updateProvince,
                                       @Field("member_tel") String updateTel);

    @FormUrlEncoded
    @POST("product_delete.php")
    Call<HeroList> getDeleteProduct(@Field("product_id") String product_id);

    @FormUrlEncoded
    @POST("delete.php")
    Call<GenericStatus> getDeleteUser(@Field("member_id") String member_id);

    @POST("insert_json.php")
    Call<GenericStatus> signUp(@Body LoginRequest loginRequest);

    @FormUrlEncoded
    @POST("select_user.php")
    Call<UserDetailList> getUser(@Field("member_id") String member_id);

    @FormUrlEncoded
    @POST("product_insert.php")
    Call<GenericStatus> insertProduct(@Field("product_name") String insertNameProduct,
                                      @Field("product_image") String insertImageProduct,
                                      @Field("product_price") String insertPriceProduct,
                                      @Field("product_description") String insertDescriptionProduct,
                                      @Field("product_how_to") String insertHowToUseProduct);

}
