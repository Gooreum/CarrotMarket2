package com.example.goo.carrotmarket.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Goo on 2019-04-13.
 */

public class Location {


    @SerializedName("City") private String city;
    @SerializedName("Dong") private String dong;
    @SerializedName("Gu") private String gu;

    String location;

    public String getCity() {
        return city;
    }

    public String getDong() {
        return dong;
    }

    public String getGu() {
        return gu;
    }

    public String getLocation() {
        location = getCity() + " " +getGu()  + " " + getDong();
        return location;
    }


}
