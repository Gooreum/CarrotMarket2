package com.example.goo.carrotmarket.Activity.SelectingLocation;

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

}
