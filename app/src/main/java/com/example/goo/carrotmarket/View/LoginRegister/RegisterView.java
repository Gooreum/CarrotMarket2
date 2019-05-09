package com.example.goo.carrotmarket.View.LoginRegister;

import com.example.goo.carrotmarket.Model.Location;
import com.example.goo.carrotmarket.Model.UserInfo;

import java.util.List;

/**
 * Created by Goo on 2019-05-02.
 */

public interface RegisterView {
    void showProgress();
    void hideProgress();
    void failToGetLocation(String message);
    void onGetResult(String message);

}
