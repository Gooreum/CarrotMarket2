package com.example.goo.carrotmarket.View.Home.Search;

import com.example.goo.carrotmarket.Model.Product;
import com.example.goo.carrotmarket.Model.UserInfo;

import java.util.List;

/**
 * Created by Goo on 2019-05-07.
 */

public interface SearchView {


    void showProgress();
    void hideProgress();
    void onGetResult(List<Product> products);
    void onGetResultUserInfo(List<UserInfo> products);
    void onErrorLoading(String message);
}
