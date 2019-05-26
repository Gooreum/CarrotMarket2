package com.example.goo.carrotmarket.View.Home.Filter;

import android.support.annotation.NonNull;

import com.example.goo.carrotmarket.API.ApiClient;
import com.example.goo.carrotmarket.API.ApiInterface;
import com.example.goo.carrotmarket.Model.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Goo on 2019-05-16.
 */

public class FilterPresenter {
    FilterView view;

    FilterPresenter(FilterView view) {
        this.view = view;
    }

    //카테고리 서버에 보내기
    void sendCategorytoServer(String nick, String category, String state) {
        view.showProgress();

        //Request to Server

        ApiInterface apiInterface = ApiClient.getApiLocation().create(ApiInterface.class);
        Call<Category> call = apiInterface.sendCategory(nick, category, state);

        call.enqueue(new Callback<Category>() {
            @Override
            public void onResponse(@NonNull Call<Category> call, @NonNull Response<Category> response) {
                view.hideProgress();
                if (response.isSuccessful() && response.body() != null) {

                    view.onGetResultCategory(response.body().toString());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Category> call, @NonNull Throwable t) {
                view.hideProgress();
                view.onErrorLoading(t.getLocalizedMessage());

            }
        });
    }

    //선택한 카테고리 결과 받아오기
    void getCategory(String nick) {
        view.showProgress();

        //Request to Server

        ApiInterface apiInterface = ApiClient.getApiLocation().create(ApiInterface.class);
        Call<List<Category>> call = apiInterface.bringCategory(nick);

        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(@NonNull  Call<List<Category>> call,@NonNull  Response<List<Category>> response) {
                view.hideProgress();
                if (response.isSuccessful() && response.body() != null) {

                    view.onGetResultCategory(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull  Call<List<Category>> call, @NonNull  Throwable t) {
                view.hideProgress();
                view.onErrorLoading(t.getLocalizedMessage());

            }
        });
    }
}
