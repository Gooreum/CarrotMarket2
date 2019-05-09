package com.example.goo.carrotmarket.View.Seller.SellerProducts;

import com.example.goo.carrotmarket.Model.Location;
import com.example.goo.carrotmarket.Model.Product;

import java.util.List;

/**
 * Created by Goo on 2019-05-05.
 */

public interface SellerView {


    void showProgress();
    void hideProgress();
    void onGetResult(List<Product> products);
    void onErrorLoading(String message);
}
