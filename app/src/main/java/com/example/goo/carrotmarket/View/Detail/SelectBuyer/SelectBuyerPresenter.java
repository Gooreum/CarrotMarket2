package com.example.goo.carrotmarket.View.Detail.SelectBuyer;

import android.support.annotation.NonNull;

import com.example.goo.carrotmarket.API.ApiClient;
import com.example.goo.carrotmarket.API.ApiInterface;
import com.example.goo.carrotmarket.Model.Hoogi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Goo on 2019-06-03.
 */

public class SelectBuyerPresenter {

    SelectBuyerView view;

    SelectBuyerPresenter(SelectBuyerView view) {
        this.view = view;
    }

    void getProduct(String profile_image, String title) {
        view.onGetProductResult(profile_image, title);
    }

    void lookForBuyerFromChatList(int product_id) {
        view.moveToChatList(product_id);
    }


    //홈화면에서 상품 받아오기
    void getBuyerList(String nick, int product_id) {

        //Request to Server

        ApiInterface apiInterface = ApiClient.getApiLocation().create(ApiInterface.class);
        Call<List<Hoogi>> call = apiInterface.getBuyer(nick, product_id);

        call.enqueue(new Callback<List<Hoogi>>() {
            @Override
            public void onResponse(@NonNull Call<List<Hoogi>> call, @NonNull Response<List<Hoogi>> response) {

                if (response.isSuccessful() && response.body() != null) {

                    view.onGetBuyerResult(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Hoogi>> call, @NonNull Throwable t) {

                view.onErrorLoading(t.getLocalizedMessage());

            }
        });
    }

}
