package com.example.goo.carrotmarket.View.Hoogi.SeeMyHoogi;

import android.support.annotation.NonNull;

import com.example.goo.carrotmarket.API.ApiClient;
import com.example.goo.carrotmarket.API.ApiInterface;
import com.example.goo.carrotmarket.Model.Hoogi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Goo on 2019-06-08.
 */

public class SeeMyHoogiPresenter {

    SeeMyHoogiView view;

    SeeMyHoogiPresenter(SeeMyHoogiView view) {
        this.view = view;
    }


    //후기 가져오기
    void getHoogi(String buyer , String seller, int product_id ) {
        //view.showProgress();

        //Request to Server

        ApiInterface apiInterface = ApiClient.getApiLocation().create(ApiInterface.class);
        Call<List<Hoogi>> call = apiInterface.getHoogi(buyer, seller, product_id);

        call.enqueue(new Callback<List<Hoogi>>() {
            @Override
            public void onResponse(@NonNull Call<List<Hoogi>> call, @NonNull Response<List<Hoogi>> response) {

                if (response.isSuccessful() && response.body() != null) {

                    view.onGetResult(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Hoogi>> call, @NonNull Throwable t) {

                view.onErrorLoading(t.getLocalizedMessage());

            }
        });
    }
}
