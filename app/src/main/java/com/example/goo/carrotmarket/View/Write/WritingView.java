package com.example.goo.carrotmarket.View.Write;

import com.example.goo.carrotmarket.Model.Location;
import com.example.goo.carrotmarket.Model.Product;

import java.util.List;

/**
 * Created by Goo on 2019-04-26.
 */

public interface WritingView {

    void showProgress();
    void hideProgress();
    void onErrorLoading(String message);
    void onGetResult(String message);
    void onGetResultId(List<Product> product);
}
