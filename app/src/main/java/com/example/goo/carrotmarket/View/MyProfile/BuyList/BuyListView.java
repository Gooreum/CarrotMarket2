package com.example.goo.carrotmarket.View.MyProfile.BuyList;

import com.example.goo.carrotmarket.Model.Product;

import java.util.List;

/**
 * Created by Goo on 2019-05-03.
 */

public interface BuyListView {

    void showProgress();

    void hideProgress();

    void onGetResult(List<Product> productList);

    void onGetResultResume(List<Product> productList);

    void onErrorLoading(String message);
}
