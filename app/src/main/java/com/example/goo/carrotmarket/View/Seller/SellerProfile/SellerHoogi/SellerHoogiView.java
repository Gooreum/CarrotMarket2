package com.example.goo.carrotmarket.View.Seller.SellerProfile.SellerHoogi;

import com.example.goo.carrotmarket.Model.Hoogi;

import java.util.List;

/**
 * Created by Goo on 2019-06-09.
 */

public interface SellerHoogiView {

    void showProgress();

    void hideProgress();

    void onGetResult(List<Hoogi> hoogiList);

    void onErrorLoading(String message);
}
