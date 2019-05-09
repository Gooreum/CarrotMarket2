package com.example.goo.carrotmarket.View.MyProfile;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.example.goo.carrotmarket.Model.UserInfo;

import java.util.List;

/**
 * Created by Goo on 2019-05-02.
 */

public interface MyProfileView {



    void moveActivity( Class activity);
    void setting();
  /*  void showProgress();
    void hideProgress();
    void onErrorLoading(String message);
    void onGetResult(List<UserInfo> UserInfo);*/
}
