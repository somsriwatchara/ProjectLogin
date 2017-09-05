package com.example.torey.projectlogin.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by TOREY on 8/20/2017.
 */

public class Login extends GenericStatus{
    @SerializedName("user_detail")
    private List<UserDetail> userDetails;

    public List<UserDetail> getUserDetails() {
        return userDetails;
    }


}
