package com.example.goo.carrotmarket.View.Seller.SellerProfile;

import com.example.goo.carrotmarket.Model.UserInfo;

import java.util.List;

/**
 * Created by Goo on 2019-05-06.
 */

public interface SellerProfileView {


    void showProgress();

    void hideProgress();

    void onGetResult(List<UserInfo> userinfo);

    void onGetCollectingState(List<UserInfo> userInfo);

    void onGetCollectResult(String message);

    void onErrorLoading(String message);



}
