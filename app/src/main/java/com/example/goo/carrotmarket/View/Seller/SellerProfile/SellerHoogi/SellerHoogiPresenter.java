package com.example.goo.carrotmarket.View.Seller.SellerProfile.SellerHoogi;

import android.support.annotation.NonNull;

import com.example.goo.carrotmarket.API.ApiClient;
import com.example.goo.carrotmarket.API.ApiInterface;
import com.example.goo.carrotmarket.Model.Hoogi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Goo on 2019-06-09.
 */

public class SellerHoogiPresenter {

    SellerHoogiView view;

    SellerHoogiPresenter(SellerHoogiView view) {
        this.view = view;
    }

    void getHoogi(String nick) {
        view.showProgress();

        //Request to Server

        ApiInterface apiInterface = ApiClient.getApiLocation().create(ApiInterface.class);
        Call<List<Hoogi>> call = apiInterface.getYourHoogi(nick);

        call.enqueue(new Callback<List<Hoogi>>() {
            @Override
            public void onResponse(@NonNull Call<List<Hoogi>> call, @NonNull Response<List<Hoogi>> response) {
                view.hideProgress();
                if (response.isSuccessful() && response.body() != null) {

                    view.onGetResult(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Hoogi>> call, @NonNull Throwable t) {
                view.hideProgress();
                view.onErrorLoading(t.getLocalizedMessage());

            }
        });
    }
}
