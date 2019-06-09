package com.example.goo.carrotmarket.View.MyProfile.AuthenticateMyLocation;

/**
 * Created by Goo on 2019-06-09.
 */

public class AuthenticateMyLocationPresenter {

    AuthenticateMyLocationView view;

    AuthenticateMyLocationPresenter(AuthenticateMyLocationView view) {
        this.view = view;
    }

    public void showMyAuthenticateState() {
        view.showProgress();
        view.onGetResult();
    }
}
