package com.example.torey.projectlogin.model;


import android.os.Parcel;
import android.os.Parcelable;

public class UserDetail implements Parcelable {
    private String member_id;
    private String member_username;
    private String member_password;
    private String member_name;
    private String member_line;
    private String member_tel;
    private String member_img;
    private String online_status;
    private String member_facebook;
    private String member_ig;
    private String member_page;
    private String member_province;
    private String member_admin;

    protected UserDetail(Parcel in) {
        member_id = in.readString();
        member_username = in.readString();
        member_password = in.readString();
        member_name = in.readString();
        member_line = in.readString();
        member_tel = in.readString();
        member_img = in.readString();
        online_status = in.readString();
        member_facebook = in.readString();
        member_ig = in.readString();
        member_page = in.readString();
        member_province = in.readString();
        member_admin = in.readString();
    }

    public static final Creator<UserDetail> CREATOR = new Creator<UserDetail>() {
        @Override
        public UserDetail createFromParcel(Parcel in) {
            return new UserDetail(in);
        }

        @Override
        public UserDetail[] newArray(int size) {
            return new UserDetail[size];
        }
    };

    public String getMember_id() {
        return member_id;
    }

    public String getMember_username() {
        return member_username;
    }

    public String getMember_password() {
        return member_password;
    }

    public String getMember_name() {
        return member_name;
    }

    public String getMember_line() {
        return member_line;
    }

    public String getMember_tel() {
        return member_tel;
    }

    public String getMember_img() {
        return member_img;
    }

    public String getOnline_status() {
        return online_status;
    }

    public String getMember_facebook() {
        return member_facebook;
    }

    public String getMember_ig() {
        return member_ig;
    }

    public String getMember_page() {
        return member_page;
    }

    public String getMember_province() {
        return member_province;
    }

    public String getMember_admin() {
        return member_admin;
    }




    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(member_id);
        dest.writeString(member_username);
        dest.writeString(member_password);
        dest.writeString(member_name);
        dest.writeString(member_line);
        dest.writeString(member_tel);
        dest.writeString(member_img);
        dest.writeString(online_status);
        dest.writeString(member_facebook);
        dest.writeString(member_ig);
        dest.writeString(member_page);
        dest.writeString(member_province);
        dest.writeString(member_admin);
    }
}
