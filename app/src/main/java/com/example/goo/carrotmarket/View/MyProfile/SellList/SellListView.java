package com.example.goo.carrotmarket.View.MyProfile.SellList;

import com.example.goo.carrotmarket.Model.Product;

import java.util.List;

/**
 * Created by Goo on 2019-05-03.
 */

public interface SellListView {


    void showProgress();
    void hideProgress();
    void onErrorLoading(String message);
    void onGetResult(List<Product> products);

}
