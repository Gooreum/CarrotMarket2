package com.example.goo.carrotmarket.View.Category.Collection.CollectionManage;

import com.example.goo.carrotmarket.Model.Product;
import com.example.goo.carrotmarket.Model.UserInfo;

import java.util.List;

/**
 * Created by Goo on 2019-05-14.
 */

public interface CollectionManageView {


    void showProgress();

    void hideProgress();

    void onErrorLoading(String message);

    void onGetResult(List<UserInfo> userinfo);

    void onGetCollectResult(String message);

}
