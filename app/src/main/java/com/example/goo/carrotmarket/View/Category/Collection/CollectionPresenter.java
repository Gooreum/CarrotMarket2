package com.example.goo.carrotmarket.View.Category.Collection;

import android.support.annotation.NonNull;

import com.example.goo.carrotmarket.API.ApiClient;
import com.example.goo.carrotmarket.API.ApiInterface;
import com.example.goo.carrotmarket.Model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Goo on 2019-05-09.
 */

public class CollectionPresenter {

    CollectionView view;

    CollectionPresenter(CollectionView view) {
        this.view = view;
    }

    //홈화면에서 상품 받아오기
    void getProductCollection(String nick) {
        view.showProgress();

        //Request to Server

        ApiInterface apiInterface = ApiClient.getApiLocation().create(ApiInterface.class);
        Call<List<Product>> call = apiInterface.getProductCollection(nick);

        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(@NonNull Call<List<Product>> call, @NonNull Response<List<Product>> response) {
                view.hideProgress();
                if (response.isSuccessful() && response.body() != null) {

                    view.onGetResult(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull  Call<List<Product>> call, @NonNull  Throwable t) {
                view.hideProgress();
                view.onErrorLoading(t.getLocalizedMessage());

            }
        });
    }
}
