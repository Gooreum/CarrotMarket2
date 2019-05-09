package com.example.goo.carrotmarket.View.Authentication;

import com.example.goo.carrotmarket.Model.Product;
import com.example.goo.carrotmarket.Model.UserInfo;

import java.util.List;

/**
 * Created by Goo on 2019-05-02.
 */

public interface AuthenticationView {

    void showProgress();
    void hideProgress();
    void onErrorLoading(String message);
    void onGetResult(List<UserInfo> user);

}
