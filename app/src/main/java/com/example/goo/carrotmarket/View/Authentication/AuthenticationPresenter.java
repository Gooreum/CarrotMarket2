package com.example.goo.carrotmarket.View.Authentication;

import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.goo.carrotmarket.API.ApiClient;
import com.example.goo.carrotmarket.API.ApiInterface;
import com.example.goo.carrotmarket.Model.Product;
import com.example.goo.carrotmarket.Model.UserInfo;
import com.example.goo.carrotmarket.View.Detail.DetailView;
import com.facebook.accountkit.Account;
import com.facebook.accountkit.AccountKitCallback;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Goo on 2019-05-02.
 */

public class AuthenticationPresenter {

    private AuthenticationView view;

    public AuthenticationPresenter(AuthenticationView view) {
        this.view = view;
    }

    //회원정보 가져오기
    void getData(String phone) {
        view.showProgress();

        //Request to Server

        ApiInterface apiInterface = ApiClient.getApiLocation().create(ApiInterface.class);
        Call<List<UserInfo>> call = apiInterface.checkUserIs(phone);

        call.enqueue(new Callback<List<UserInfo>>() {
            @Override
            public void onResponse(@NonNull Call<List<UserInfo>> call, @NonNull Response<List<UserInfo>> response) {
                view.hideProgress();
                if (response.isSuccessful() && response.body() != null) {
                    //view.onMessage(response.message().toString());
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


}
