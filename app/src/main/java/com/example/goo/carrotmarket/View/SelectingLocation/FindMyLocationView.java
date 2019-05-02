package com.example.goo.carrotmarket.View.SelectingLocation;

import android.widget.Button;

import com.example.goo.carrotmarket.Model.Location;

import java.util.List;

/**
 * Created by Goo on 2019-04-14.
 */

public interface FindMyLocationView {

    void showProgress();
    void hideProgress();
    void failToGetLocation(String message);
    void onGetResult(List<Location> location);
    void onDialog(String location);
}
