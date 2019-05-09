package com.example.goo.carrotmarket.View.Home;

import com.example.goo.carrotmarket.Model.Category;
import com.example.goo.carrotmarket.Model.Location;
import com.example.goo.carrotmarket.Model.Product;

import java.util.List;

/**
 * Created by Goo on 2019-04-24.
 */

public interface HomeView {

    void showProgress();
    void hideProgress();
    void onErrorLoading(String message);
    void onGetResult(List<Product> products);
    void onGetResultCategory(List<Category> products);
    void onGetResultCategory(String text);
    void moveActivity( Class activity);
    void moveActivityForResult( Class activity);
}


