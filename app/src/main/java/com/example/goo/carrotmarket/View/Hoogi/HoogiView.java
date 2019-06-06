package com.example.goo.carrotmarket.View.Hoogi;

/**
 * Created by Goo on 2019-06-06.
 */

public interface HoogiView {

    void showProgress();
    void hideProgress();
    void setValues(String title, String seller, String buyer, String nick);
    void onGetResult(String message );
    void onErrorLoading(String message);

}
