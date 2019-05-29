package com.example.goo.carrotmarket.View.Detail;

import com.example.goo.carrotmarket.Model.Chat;
import com.example.goo.carrotmarket.Model.Product;
import com.example.goo.carrotmarket.Model.UserInfo;

import java.util.List;

/**
 * Created by Goo on 2019-04-26.
 */

public interface DetailView {

    void showProgress();

    void hideProgress();

    void onErrorLoading(String message);

    void onGetResult(String message);

    void onGetResultDelete(String message);

    void onGetResult(List<Product> product);

    void onGetResultIsChatRoom(List<Chat> chat);

    void onGetResultSellerInfo(List<UserInfo> product);

    void onGetSellerProductsResult(List<Product> product);

    void onGetResultLikeState(List<Product> product);

    void onPostLike();

    void showMyProductStateSelling();

    void showMyProductStateReserving();

    void showMyProductStateComplete();

    void showSnackBar(String message);
}
