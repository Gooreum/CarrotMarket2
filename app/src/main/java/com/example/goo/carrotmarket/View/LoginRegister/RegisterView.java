package com.example.goo.carrotmarket.View.LoginRegister;

/**
 * Created by Goo on 2019-05-02.
 */

public interface RegisterView {
    void showProgress();
    void hideProgress();
    void failToGetLocation(String message);
    void onGetResult(String message);

}
