package com.example.goo.carrotmarket.API;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Goo on 2019-05-22.
 */

public class ApiClientNodeJs {
    public static final String BASE_URL_NODEJS = "http://54.180.32.57:3000/";
    public static Retrofit instance;
    public static HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY);

    public static OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
    public static Retrofit getApiLocation() {

        if (instance == null) {
            instance = new Retrofit.Builder()
                    .client(client)
                    .baseUrl(BASE_URL_NODEJS)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }

        return instance;
    }
}
