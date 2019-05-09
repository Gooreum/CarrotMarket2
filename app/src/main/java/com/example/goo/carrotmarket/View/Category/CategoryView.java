package com.example.goo.carrotmarket.View.Category;

import com.example.goo.carrotmarket.Model.Product;

import java.util.List;

/**
 * Created by Goo on 2019-05-01.
 */

public interface CategoryView {


    void showProgress();
    void hideProgress();
    void onErrorLoading(String message);
    void onGetResult(List<Product> products);
}
