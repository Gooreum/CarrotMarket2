package com.example.goo.carrotmarket.View.Seller.SellerProfile;

import android.support.annotation.NonNull;

import com.example.goo.carrotmarket.API.ApiClient;
import com.example.goo.carrotmarket.API.ApiInterface;
import com.example.goo.carrotmarket.Model.UserInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Goo on 2019-05-06.
 */

public class SellerProfilePresenter {

    SellerProfileView view;

    SellerProfilePresenter(SellerProfileView view) {
        this.view = view;
    }

    void getSellerProfile(String seller) {
        view.showProgress();

        //Request to Server

        ApiInterface apiInterface = ApiClient.getApiLocation().create(ApiInterface.class);
        Call<List<UserInfo>> call = apiInterface.getSellerProfile(seller);

        call.enqueue(new Callback<List<UserInfo>>() {
            @Override
            public void onResponse(@NonNull Call<List<UserInfo>> call, @NonNull Response<List<UserInfo>> response) {
                view.hideProgress();
                if (response.isSuccessful() && response.body() != null) {

                    view.onGetResult(response.body());

                }
            }

            @Override
            public void onFailure(@NonNull Call<List<UserInfo>> call, @NonNull Throwable t) {
                view.hideProgress();
                view.onErrorLoading(t.getLocalizedMessage());

            }
        });
    }

    //모아보기 상태 확인하기
    void getCollectingState(String seller, String follwer) {
        view.showProgress();

        //Request to Server

        ApiInterface apiInterface = ApiClient.getApiLocation().create(ApiInterface.class);
        Call<List<UserInfo>> call = apiInterface.checkCollectingState(seller,follwer);

        call.enqueue(new Callback<List<UserInfo>>() {
            @Override
            public void onResponse(@NonNull Call<List<UserInfo>> call, @NonNull Response<List<UserInfo>> response) {
                view.hideProgress();
                if (response.isSuccessful() && response.body() != null) {

                    view.onGetCollectingState(response.body());

                }
            }

            @Override
            public void onFailure(@NonNull Call<List<UserInfo>> call, @NonNull Throwable t) {
                view.hideProgress();
                view.onErrorLoading(t.getLocalizedMessage());

            }
        });
    }

    //모아보기 하기 및 취소하기
    void changeCollectState(String seller, String follwer, int state) {
        view.showProgress();

        //Request to Server

        ApiInterface apiInterface = ApiClient.getApiLocation().create(ApiInterface.class);
        Call<UserInfo> call = apiInterface.collect(seller,follwer,state);

        call.enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(@NonNull Call<UserInfo> call, @NonNull Response<UserInfo> response) {
                view.hideProgress();
                if (response.isSuccessful() && response.body() != null) {

                    view.onGetCollectResult(response.message());

                }
            }

            @Override
            public void onFailure(@NonNull Call<UserInfo> call, @NonNull Throwable t) {
                view.hideProgress();
                view.onErrorLoading(t.getLocalizedMessage());

            }
        });
    }

}
