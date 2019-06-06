package com.example.goo.carrotmarket.View.Detail.SelectBuyer;

import com.example.goo.carrotmarket.Model.Hoogi;

import java.util.List;

/**
 * Created by Goo on 2019-06-03.
 */

public interface SelectBuyerView {


    void onErrorLoading(String message);

    void onGetProductResult(String product_image, String product_title);

    void onGetBuyerResult(List<Hoogi> chat);

    void moveToChatList(int product_id);


}
