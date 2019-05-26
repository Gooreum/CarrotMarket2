package com.example.goo.carrotmarket.View.MyProfile.SetMyLocation;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.goo.carrotmarket.API.ApiClient;
import com.example.goo.carrotmarket.API.ApiInterface;

import com.example.goo.carrotmarket.Model.UserInfo;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Goo on 2019-05-12.
 */

public class SetMyLocationPresenter {

    SetMyLocationView view;
    Context context;

    public SetMyLocationPresenter(SetMyLocationView view, Context context) {
        this.context = context;
        this.view = view;
    }

    public void updateMyLocation(String nick, String city, String gu, String dong, int state, int location_state) {

        //view.showProgress();

        //Request to Server

        ApiInterface apiInterface = ApiClient.getApiLocation().create(ApiInterface.class);
        Call<UserInfo> call = apiInterface.updateMyLocation(nick, city, gu, dong, state, location_state);

        call.enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(@NonNull Call<UserInfo> call, @NonNull Response<UserInfo> response) {
//                view.hideProgress();
                if (response.isSuccessful() && response.body() != null) {

                    //Toast.makeText(context, response.body().getMessage().toString(), Toast.LENGTH_SHORT).show();
                    view.onGetUserInfo();
                    view.onGetResult(response.body().getMessage());

                }

            }

            @Override
            public void onFailure(@NonNull Call<UserInfo> call, @NonNull Throwable t) {
                //view.hideProgress();
//                view.onErrorLoading(t.getLocalizedMessage());

            }
        });
    }

    public void updateMyLocationFromSelection(String nick, String city, String gu, String dong, int state, int location_state) {

        //view.showProgress();

        //Request to Server

        ApiInterface apiInterface = ApiClient.getApiLocation().create(ApiInterface.class);
        Call<UserInfo> call = apiInterface.updateMyLocation(nick, city, gu, dong, state, location_state);

        call.enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(@NonNull Call<UserInfo> call, @NonNull Response<UserInfo> response) {
//                view.hideProgress();
                if (response.isSuccessful() && response.body() != null) {

                    //Toast.makeText(context, response.body().getMessage().toString(), Toast.LENGTH_SHORT).show();
                    view.onGetUserInfo();
                    view.onGetResultFromSelection(response.body().getMessage());

                }

            }

            @Override
            public void onFailure(@NonNull Call<UserInfo> call, @NonNull Throwable t) {
                //view.hideProgress();
//                view.onErrorLoading(t.getLocalizedMessage());

            }
        });
    }

    void getUserInfo() {
        view.onGetUserInfo();
    }


}
