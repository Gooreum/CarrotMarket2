package com.example.goo.carrotmarket.API;

import com.example.goo.carrotmarket.Model.Location;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Goo on 2019-04-14.
 */

public interface ApiInterface {

    @GET("location.php")
    Call<List<Location>> getLocation(@Query("key") CharSequence keyword);

}
