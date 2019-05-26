package com.example.goo.carrotmarket.View.Home.Filter;

import com.example.goo.carrotmarket.Model.Category;
import com.example.goo.carrotmarket.Model.Product;

import java.util.List;

/**
 * Created by Goo on 2019-05-16.
 */

public interface FilterView {

    void showProgress();
    void hideProgress();
    void onErrorLoading(String message);

    void onGetResultCategory(List<Category> products);
    void onGetResultCategory(String text);

}
