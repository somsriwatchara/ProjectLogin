package com.example.torey.projectlogin.model;


import android.os.Parcel;
import android.os.Parcelable;

public class Hero implements Parcelable {
    private String product_id;
    private String product_name;
    private String product_image;
    private String product_price;
    private String product_description;
    private String product_how_to;

    protected Hero(Parcel in) {
        product_id = in.readString();
        product_name = in.readString();
        product_image = in.readString();
        product_price = in.readString();
        product_description = in.readString();
        product_how_to = in.readString();
    }

    public static final Creator<Hero> CREATOR = new Creator<Hero>() {
        @Override
        public Hero createFromParcel(Parcel in) {
            return new Hero(in);
        }

        @Override
        public Hero[] newArray(int size) {
            return new Hero[size];
        }
    };

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public void setProduct_image(String product_image) {
        this.product_image = product_image;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }

    public void setProduct_how_to(String product_how_to) {
        this.product_how_to = product_how_to;
    }



    public String getProduct_id() {
        return product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getProduct_image() {
        return product_image;
    }

    public String getProduct_price() {
        return product_price;
    }

    public String getProduct_description() {
        return product_description;
    }

    public String getProduct_how_to() {
        return product_how_to;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(product_id);
        dest.writeString(product_name);
        dest.writeString(product_image);
        dest.writeString(product_price);
        dest.writeString(product_description);
        dest.writeString(product_how_to);
    }
}
