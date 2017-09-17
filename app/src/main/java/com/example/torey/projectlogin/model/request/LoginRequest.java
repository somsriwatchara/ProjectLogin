package com.example.torey.projectlogin.model.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by TOREY on 9/17/2017.
 */

public class LoginRequest {
    @SerializedName("member_username")
    private String insertInputUsername;
    @SerializedName("member_password")
    private String insertInputPassword;
    @SerializedName("member_name")
    private String insertInputName;
    @SerializedName("member_line")
    private String insertInputLine;
    @SerializedName("member_tal")
    private String insertInputTel;
    @SerializedName("member_image")
    private String insertInputImage;
    @SerializedName("member_facebook")
    private String insertInputFacebook;
    @SerializedName("member_ig")
    private String insertInputIG;
    @SerializedName("member_page")
    private String insertInputPage;
    @SerializedName("member_province")
    private String insertInputProvince;

    public static LoginRequest create(){
        return new LoginRequest();
    }

    public String getInsertInputUsername() {
        return insertInputUsername;
    }

    public LoginRequest withInsertInputUsername(String insertInputUsername) {
        this.insertInputUsername = insertInputUsername;
        return this;
    }

    public String getInsertInputPassword() {
        return insertInputPassword;
    }

    public LoginRequest withInsertInputPassword(String insertInputPassword) {
        this.insertInputPassword = insertInputPassword;
        return this;
    }

    public String getInsertInputName() {
        return insertInputName;
    }

    public LoginRequest withInsertInputName(String insertInputName) {
        this.insertInputName = insertInputName;
        return this;
    }

    public String getInsertInputLine() {
        return insertInputLine;
    }

    public LoginRequest withInsertInputLine(String insertInputLine) {
        this.insertInputLine = insertInputLine;
        return this;
    }

    public String getInsertInputTel() {
        return insertInputTel;
    }

    public LoginRequest withInsertInputTel(String insertInputTel) {
        this.insertInputTel = insertInputTel;
        return this;
    }

    public String getInsertInputImage() {
        return insertInputImage;
    }

    public LoginRequest withInsertInputImage(String insertInputImage) {
        this.insertInputImage = insertInputImage;
        return this;
    }

    public String getInsertInputFacebook() {
        return insertInputFacebook;
    }

    public LoginRequest withInsertInputFacebook(String insertInputFacebook) {
        this.insertInputFacebook = insertInputFacebook;
        return this;
    }

    public String getInsertInputIG() {
        return insertInputIG;
    }

    public LoginRequest withInsertInputIG(String insertInputIG) {
        this.insertInputIG = insertInputIG;
        return this;
    }

    public String getInsertInputPage() {
        return insertInputPage;
    }

    public LoginRequest withInsertInputPage(String insertInputPage) {
        this.insertInputPage = insertInputPage;
        return this;
    }

    public String getInsertInputProvince() {
        return insertInputProvince;
    }

    public LoginRequest withInsertInputProvince(String insertInputProvince) {
        this.insertInputProvince = insertInputProvince;
        return this;
    }


}
