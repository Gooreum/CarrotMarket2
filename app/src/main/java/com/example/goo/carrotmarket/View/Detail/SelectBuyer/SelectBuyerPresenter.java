package com.example.goo.carrotmarket.View.Detail.SelectBuyer;

/**
 * Created by Goo on 2019-06-03.
 */

public class SelectBuyerPresenter {

    SelectBuyerView view;

    SelectBuyerPresenter(SelectBuyerView view) {
        this.view = view;
    }

    void getProduct(String profile_image, String title) {
        view.onGetProductResult(profile_image, title);
    }

    void lookForBuyerFromChatList(int product_id) {
        view.moveToChatList(product_id);
    }


}
