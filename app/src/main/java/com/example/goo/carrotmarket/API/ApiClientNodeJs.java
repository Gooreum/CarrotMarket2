package com.example.goo.carrotmarket.API;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Goo on 2019-05-22.
 */

public class ApiClientNodeJs {
    public static final String BASE_URL_NODEJS = "http://54.180.32.57:3000/";
    public static Retrofit instance;

    public static Retrofit getApiLocation() {

        if (instance == null) {
            instance = new Retrofit.Builder()
                    .baseUrl(BASE_URL_NODEJS)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }

        return instance;
    }
}
