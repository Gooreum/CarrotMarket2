package com.example.goo.carrotmarket.View.LoginRegister;

import android.support.v7.app.AppCompatActivity;

import com.example.goo.carrotmarket.API.ApiClient;
import com.example.goo.carrotmarket.API.ApiInterface;
import com.example.goo.carrotmarket.Model.Location;
import com.example.goo.carrotmarket.Model.UserInfo;
import com.example.goo.carrotmarket.Util.SessionManager;
import com.example.goo.carrotmarket.View.SelectingLocation.FindMyLocationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;

/**
 * Created by Goo on 2019-05-02.
 */

public class RegisterPresenter {

    private RegisterView view;


    public RegisterPresenter(RegisterView view) {
        this.view = view;
    }

    //검색 결과 받아오기
    void makeMember(String nick, String city1, String gu1, String dong1, String phone) {
        view.showProgress();

        //Request to Server

        ApiInterface apiInterface = ApiClient.getApiLocation().create(ApiInterface.class);
        Call<UserInfo> call = apiInterface.register(nick,city1,gu1,dong1,phone);

        call.enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                view.hideProgress();
                if (response.isSuccessful() && response.body() != null) {

                    view.onGetResult(response.message());
                }
            }

            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {
                view.hideProgress();
                view.failToGetLocation(t.getLocalizedMessage());

            }
        });
    }

}
