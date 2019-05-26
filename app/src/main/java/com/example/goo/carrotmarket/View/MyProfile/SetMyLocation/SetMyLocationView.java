package com.example.goo.carrotmarket.View.MyProfile.SetMyLocation;


/**
 * Created by Goo on 2019-05-12.
 */

public interface SetMyLocationView {



    void onErrorLoading(String message);

    void onGetResult(String message);

    void onGetResultFromSelection(String message);

    void warning();

    void onGetUserInfo();


}
